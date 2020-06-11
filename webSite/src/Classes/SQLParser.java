package Classes;

import java.sql.Timestamp;

public final class SQLParser {

    public static String addUser(User user, Timestamp date) {
        return String.format(
                "INSERT INTO USERS (LOGIN, PASSWORD, PASSWORD_ACTIVATION_DATE) " +
                "VALUES ('%s', '%s', '%s')",
                user.getLogin(), user.getPassword(), date.toString());
    }

    public static String deactivatePassword(User user, Timestamp date) {
        return String.format(
                "INSERT INTO OLD_PASSWORDS (USER_LOGIN, PASSWORD, DEACTIVATION_DATE) " +
                "VALUES ('%s', '%s', '%s')",
                user.getLogin(), user.getPassword(), date.toString());
    }

    public static String blockUser(User user, Timestamp date) {
        return String.format(
                "INSERT INTO BLOCKADES (USER_LOGIN, CREATION_DATE) " +
                "VALUES ('%s', '%s')",
                user.getLogin(), date.toString());
    }

    public static String addAction(User user, String action, Timestamp date) {
        return String.format(
                "INSERT INTO ACTIONS (USER_LOGIN, NAME, DATE) " +
                "VALUES ('%s', '%s', '%s')",
                user.getLogin(), action, date.toString());
    }


    public static String isUser(User user) {
        return String.format("SELECT * " +
                "FROM USERS " +
                "WHERE LOGIN = '%s'", user.getLogin());
    }

    public static String isBlocked(User user) {
        return String.format("SELECT * " +
                "FROM BLOCKADES " +
                "WHERE USER_LOGIN = '%s'", user.getLogin());
    }

    public static String isPasswordUsed(User user, String password) {
        return String.format("SELECT * " +
                "FROM OLD_PASSWORDS " +
                "WHERE USER_LOGIN = '%s' AND PASSWORD = '%s'", user.getLogin(), password);
    }

    public static String getPassword(User user) {
        return String.format("SELECT PASSWORD " +
                "FROM USERS " +
                "WHERE LOGIN = '%s'", user.getLogin());
    }

    public static String getErrorCounter(User user) {
        return String.format("SELECT LOGIN_ERROR " +
                "FROM USERS " +
                "WHERE LOGIN = '%s'", user.getLogin());
    }

    public static String getActions(User user) {
        return String.format("SELECT TOP 50 NAME, DATE " +
                "FROM ACTIONS " +
                "WHERE USER_LOGIN = '%s' ORDER BY DATE DESC", user.getLogin());
    }

    public static String getPasswordActivationDate(User user) {
        return String.format("SELECT PASSWORD_ACTIVATION_DATE " +
                "FROM USERS " +
                "WHERE LOGIN = '%s'", user.getLogin());
    }

    public static String getBlockadeCreationDate(User user) {
        return String.format("SELECT CREATION_DATE " +
                "FROM BLOCKADES " +
                "WHERE USER_LOGIN = '%s'", user.getLogin());
    }



    public static String changePassword(User user, Timestamp date) {
        return String.format(
                "UPDATE USERS " +
                "SET PASSWORD = '%s',  PASSWORD_ACTIVATION_DATE = '%s'" +
                "WHERE LOGIN = '%s'", user.getPassword(), date.toString(), user.getLogin());
    }

    public static String changeErrorCounter(User user, int counter) {
        return String.format(
                "UPDATE USERS " +
                "SET LOGIN_ERROR = '%d'" +
                "WHERE LOGIN = '%s'", counter, user.getLogin());
    }


    public static String unblockUser(User user) {
        return String.format(
                "DELETE FROM BLOCKADES " +
                "WHERE USER_LOGIN = '%s'", user.getLogin());
    }
}

