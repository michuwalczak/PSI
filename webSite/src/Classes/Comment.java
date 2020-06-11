package Classes;

public class Comment {
    private String comment = "";

    public Comment(){ }

    public Comment(Security.ValidationResult result) { this.comment = getValidationInfo(result); }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    private static String getValidationInfo(Security.ValidationResult result){
        switch (result)
        {
            case UserFound:
                return "Użytkownik o podanym loginie już istnieje";
            case UserNotFound:
                return "Brak użytkownika o podanym loginie";
            case PasswordWrong:
                return "Błędne hasło";
            case PasswordExpired:
                return "Hasło wygasło, wymagana zmiana";
            case PasswordChangeNotPermitted:
                return "Hasło jest aktywne - brak możliwości zmiany";
            case PasswordUsed:
                return "Hasło było już użyte";
            case PasswordLengthError:
                return String.format("Hasło jest zbyt krótkie. Minimalna długośc znaków to: %d", Config.MIN_PASSWORD_LENGTH);
            case PasswordPatternError:
                return "Hasło powinno zawierać małe i duże litery";
            case AccountBlocked:
                return "Konto zostało zablokowane";
            case RegistrationValidated:
                return "Konto zostało dodane";
            case PasswordChangeValidated:
                return "Hasło zostało zmienione";
            case Error:
                return "Błąd procesu uwierzytalniania";
            default:
                return "";
        }
    }
}
