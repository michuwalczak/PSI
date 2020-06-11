package Classes;

import java.sql.Timestamp;
import java.util.regex.Pattern;

public final class Security {
    public enum ValidationResult {
        Ok,
        UserFound,
        UserNotFound,
        AccountActive,
        AccountBlocked,
        PasswordCorrect,
        PasswordWrong,
        PasswordChangePermitted,
        PasswordChangeNotPermitted,
        PasswordExpired,
        PasswordUpToDate,
        PasswordNotUsed,
        PasswordUsed,
        PasswordPatternCorrect,
        PasswordPatternError,
        PasswordLengthError,
        RegistrationValidated,
        PasswordChangeValidated,
        Error
    }

    public static ValidationResult validateLogin(User user){
        ValidationResult result;

        //step 1: check if user exists in database
        result = validateUserExistence(user);

        //step 2: check if account is active
        if(result == ValidationResult.UserFound)
            result = validateUserAccount(user);

        //step 3: check entered password
        if(result == ValidationResult.AccountActive)
            result = validateEnteredPassword(user);

        //step 4: check if password is not expired
        if(result == ValidationResult.PasswordCorrect)
            result = validateIfPasswordExpired(user);

        return result;
    }

    public static ValidationResult validateRegistration(User user){
        ValidationResult result;

        //step 1: check if user exists in database
        result = validateUserExistence(user);

        //step 2: check password length
        if(result == ValidationResult.UserNotFound)
            result = validatePasswordLength(user.getPassword());

        //step 4: check final result
        if(result == ValidationResult.PasswordPatternCorrect)
            result = ValidationResult.RegistrationValidated;

        return result;
    }

    public static ValidationResult validatePasswordChange(User user, String oldPassword, String newPassword){
        ValidationResult result;

        //step 1: check password change availability
        result = validatePasswordChangeAvailability(user);

        //step 2: check entered old password
        if(result == ValidationResult.PasswordChangePermitted)
            result = validateEnteredPassword(user, oldPassword);

        //step 3: check if new password has already been used
        if(result == ValidationResult.PasswordCorrect)
            result = validatePasswordUsage(user, newPassword);

        //step 4: check new password length
        if(result == ValidationResult.PasswordNotUsed)
            result = validatePasswordLength(newPassword);

        //step 5: check final result
        if(result == ValidationResult.PasswordPatternCorrect)
            result = ValidationResult.PasswordChangeValidated;

        return result;
    }


    private static ValidationResult validateUserExistence(User user){
        if (Dao.isUser(user)) {
            return ValidationResult.UserFound;
        }
        else{
            return ValidationResult.UserNotFound;
        }
    }

    private static ValidationResult validateUserAccount(User user){
        if (Dao.isBlocked(user)) {
            Timestamp blockadeCreationDate = Dao.getBlockadeCreationDate(user);
            if (Date.isTimeUp(blockadeCreationDate, Config.BLOCKADE_TIME)) {
                Dao.unblockUser(user);
                Dao.updateErrorCounter(user, 0);
                Dao.addAction(user, "Konto odblokowane");
                return ValidationResult.AccountActive;
            }
            else {
                return ValidationResult.AccountBlocked;
            }
        }
        else{
            return ValidationResult.AccountActive;
        }
    }

    private static ValidationResult validateEnteredPassword(User user){
        if (Dao.getPassword(user).equals(user.getPassword())){
            return ValidationResult.PasswordCorrect;
        }
        else{
            int errCounter = Dao.getErrorCounter(user);
            Dao.updateErrorCounter(user, ++errCounter);
            if(errCounter >= Config.MAX_LOGIN_ERROR) {
                Dao.blockUser(user);
                Dao.addAction(user, "Konto zablokowane");
            }
            Dao.addAction(user, "Nieprawidłowe hasło");
            return ValidationResult.PasswordWrong;
        }
    }

    private static ValidationResult validateEnteredPassword(User user, String password){
        if (user.getPassword().equals(password)){
            return ValidationResult.PasswordCorrect;
        }
        else{
            Dao.addAction(user, "Nieprawidłowe hasło");
            return ValidationResult.PasswordWrong;
        }
    }

    private static ValidationResult validateIfPasswordExpired(User user){
        Timestamp passwordCreationDate = Dao.getPasswordActivationDate(user);

        if (Date.isTimeUp(passwordCreationDate, Config.PASSWORD_EXPIRE_TIME)){
            Dao.addAction(user, "Hasło wygasło");
            return ValidationResult.PasswordExpired;
        }
        else{
            return ValidationResult.PasswordUpToDate;
        }
    }

    private static ValidationResult validatePasswordChangeAvailability(User user){
        Timestamp passwordCreationDate = Dao.getPasswordActivationDate(user);

        if (Date.isTimeUp(passwordCreationDate, Config.MIN_PASSWORD_CHANGE_TIME)){
            return ValidationResult.PasswordChangePermitted;
        }
        else{
            return ValidationResult.PasswordChangeNotPermitted;
        }
    }

    private static ValidationResult validatePasswordUsage(User user, String password){
        if (Dao.isPasswordUsed(user, password) || user.getPassword().equals(password)){
            return ValidationResult.PasswordUsed;
        }
        else{
            return ValidationResult.PasswordNotUsed;
        }
    }

    private static ValidationResult validatePasswordLength(String password){
        if(password.length() >= Config.MIN_PASSWORD_LENGTH){
            Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]).+$");
            if(textPattern.matcher(password).matches()){
                return ValidationResult.PasswordPatternCorrect;
            }
            else{
                return ValidationResult.PasswordPatternError;
            }
        }
        else{
            return ValidationResult.PasswordLengthError;
        }
    }

}
