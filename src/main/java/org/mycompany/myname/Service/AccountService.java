package org.mycompany.myname.Service;

import org.mycompany.myname.accounts.UserProfile;
import org.mycompany.myname.executor.Executor;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final Map<String, UserProfile> sessionIdToProfile = new HashMap<>();
    private final Connection dbConnection;

    public AccountService() {
        dbConnection = connectDB();
    }

    private Connection connectDB() {
        String url = "jdbc:mysql://localhost:3306/lab6?useUnicode=true&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, "root", "root");
        } catch (Exception e) {
            System.out.println("MySQL JDBC error");
            e.printStackTrace();
            return null;
        }
    }

    public void addNewUser(UserProfile userProfile) {
        String log = userProfile.getLogin();
        log = new String(log.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String insertSQL = "INSERT INTO authorization VALUES ('" + log + "', '" + userProfile.getPass() +
                "' ,'" + userProfile.getEmail() + "');";
        try {
            Executor executor = new Executor(dbConnection);
            executor.execUpdate(insertSQL);
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public UserProfile getUserByLogin(String login) {
        login = new String(login.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String selectSQL = "SELECT * FROM authorization WHERE login='" + login + "';";
        try {
            Executor executor = new Executor(dbConnection);
            return executor.execQuery(selectSQL, rs -> {
                String log = null, pass = null, email = null;
                while (rs.next()) {
                    log = rs.getString("login");
                    pass = rs.getString("pass");
                    email = rs.getString("email");
                }
                dbConnection.close();
                return new UserProfile(log, pass, email);
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        //return loginToProfile.get(login);
    }

    public static UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public static void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public static void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
