package gb.java2.server.server.authentication;

import java.sql.*;

public class DBAuthentication implements AuthenticationService {

    private Connection connection;
    private Statement stmt;
    private ResultSet rs;

    public DBAuthentication() {
        try {
            connectDB();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public synchronized boolean addRecordDB(String login, String password, String username) throws SQLException {
        stmt.execute(String.format("INSERT INTO users(login, password, username) VALUES " +
                "('%s', '%s', '%s')", login, password, username));
        return true;
    }

    @Override
    public synchronized String getUsernameByLoginAndPassword(String login, String password) throws SQLException {

        rs = stmt.executeQuery(String.format("SELECT password, username FROM users WHERE login = '%s'", login));

        String passwordDB = rs.getString("password");
        String usernameDB = rs.getString("username");

        if (passwordDB != null) {
            return (passwordDB.equals(password) ? usernameDB : null);
        }

        return null;
    }

    @Override
    public void startAuthentication() {
        System.out.println(">>>Start of the authentication");

    }

    @Override
    public void endAuthentication() {
        System.out.println(">>>End of the authentication");

    }

    private void connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/gb/java2/DB/authDB.db");
        stmt = connection.createStatement();
        System.out.println("Connection to the DataBase");

    }

    public void disconnectDB() {
        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Disconnection from the DataBase");
    }
}
