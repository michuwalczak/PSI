package Classes;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Dao {

    public static boolean isUser(User user){
        List<Map<String, Object>> results = DataBase.select(SQLParser.isUser(user));
        return results.size() != 0;
    }

    public static boolean isBlocked(User user){
        List<Map<String, Object>> results = DataBase.select(SQLParser.isBlocked(user));
        return results.size() != 0;
    }

    public static boolean isPasswordUsed(User user, String password){
        List<Map<String, Object>> results = DataBase.select(SQLParser.isPasswordUsed(user, password));
        return results.size() != 0;
    }


    public static String getPassword(User user) {
        List<Map<String, Object>> results = DataBase.select(SQLParser.getPassword(user));
        return (String)results.get(0).get(DataBase.Users.PASSWORD);
    }

    public static int getErrorCounter(User user) {
        List<Map<String, Object>> results = DataBase.select(SQLParser.getErrorCounter(user));
        return (int)results.get(0).get(DataBase.Users.LOGIN_ERROR);
    }

    public static Timestamp getPasswordActivationDate(User user) {
        List<Map<String, Object>> results = DataBase.select(SQLParser.getPasswordActivationDate(user));
        return (Timestamp) results.get(0).get(DataBase.Users.PASSWORD_ACTIVATION_DATE);
    }

    public static Timestamp getBlockadeCreationDate(User user) {
        List<Map<String, Object>> results = DataBase.select(SQLParser.getBlockadeCreationDate(user));
        return (Timestamp) results.get(0).get(DataBase.Blockades.CREATION_DATE);
    }

    public static Map<String, String> getActions(User user) {
        Map<String, String> actions = new HashMap<>();
        List<Map<String, Object>> results = DataBase.select(SQLParser.getActions(user));

        //convert results from database to Map<String, String>
        for(Map<String, Object> row: results)
            actions.put(row.get(DataBase.Actions.NAME).toString(), row.get(DataBase.Actions.DATE).toString());

        return actions;
    }


    public static void updateErrorCounter(User user, int counter) {
        DataBase.update(SQLParser.changeErrorCounter(user, counter));
    }

    public static void updatePassword(User user) {
        DataBase.insert(SQLParser.deactivatePassword(user, Date.get()));
        DataBase.update(SQLParser.changePassword(user, Date.get()));
    }


    public static void blockUser(User user) {
        DataBase.insert(SQLParser.blockUser(user, Date.get()));
    }

    public static void unblockUser(User user) {
        DataBase.insert(SQLParser.unblockUser(user));
    }

    public static void addUser(User user) {
        DataBase.insert(SQLParser.addUser(user, Date.get()));
    }

    public static void addAction(User user, String action) {
        DataBase.insert(SQLParser.addAction(user, action, Date.get()));
    }
}
