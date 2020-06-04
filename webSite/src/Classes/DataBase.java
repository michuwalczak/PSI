package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DataBase {
    public static boolean configured = false;

    private static String url;
    private static String userName;
    private static String userPassword;
    private static String dataBaseDriver;

    public static void configure(String userName, String userPassword, String appPath){
        DataBase.userName = userName;
        DataBase.userPassword = userPassword;
        DataBase.url = String.format("jdbc:h2:%s\\bazaH2;MV_STORE=false", appPath);
        DataBase.dataBaseDriver = "org.h2.Driver";

        configured = true;
    }

    public static List<Map<String, Object>> select(String query){
        List<Map<String, Object>> results = null;
        Connection connection = null;
        Statement statement = null;

        try {
            connection = createConnection(dataBaseDriver, url, userName, userPassword);
            statement = connection.createStatement();
            statement.execute(query);
            results = map(statement.executeQuery(query));
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(statement);
        }

        return results;
    }

    public static void insert(String query){
        Connection connection = null;
        Statement statement = null;

        try {
            connection = createConnection(dataBaseDriver, url, userName, userPassword);
            statement = connection.createStatement();
            statement.execute(query);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(statement);
        }
    }

    public static void update(String query){
        Connection connection = null;
        Statement statement = null;

        try {
            connection = createConnection(dataBaseDriver, url, userName, userPassword);
            statement = connection.createStatement();
            statement.execute(query);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(statement);
        }
    }


    private static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    private static void close(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void close(Statement statement) {
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void rollback(Connection connection) {
        try {
            if (connection != null) connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static List<Map<String, Object>> map(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        try {
            if (resultSet != null) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numColumns = metaData.getColumnCount();

                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= numColumns; ++i) {
                        String name = metaData.getColumnName(i);
                        Object value = resultSet.getObject(i);
                        row.put(name, value);
                    }
                    results.add(row);
                }
            }
        } finally {
            close(resultSet);
        }
        return results;
    }

    public static class Users{
        public static final String LOGIN = "LOGIN";
        public static final String LOGIN_ERROR = "LOGIN_ERROR";
        public static final String PASSWORD = "PASSWORD";
        public static final String PASSWORD_ACTIVATION_DATE = "PASSWORD_ACTIVATION_DATE";
    }

    public static class OldPasswords{
        public static final String USER_LOGIN = "USER_LOGIN";
        public static final String PASSWORD = "PASSWORD";
        public static final String DEACTIVATION_DATE = "DEACTIVATION_DATE";
    }

    public static class Blockades{
        public static final String USER_LOGIN = "USER_LOGIN";
        public static final String CREATION_DATE = "CREATION_DATE";
    }

    public static class Actions{
        public static final String USER_LOGIN = "USER_LOGIN";
        public static final String NAME = "NAME";
        public static final String DATE = "DATE";
    }
}
