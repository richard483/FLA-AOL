package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import models.Member;
import models.ProMember;
import models.User;

public class DBConnection {
    public Connection connection;
    public Statement statement;
    public ResultSet resultset;
    public PreparedStatement preparableStatement;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    ResultSet getData(String query) {
        try {
            resultset = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultset;
    }

    public User login(String username, String password) {
        try {
            resultset = statement.executeQuery(
                    "SELECT * FROM `user` WHERE `name` = '" + username + "' AND `password` = '" + password + "'");
            if (resultset.next()) {
                if (resultset.getString("role").equals("member")) {
                    return new Member(username, password, resultset.getString("role"));
                } else {
                    return new ProMember(username, password, resultset.getString("role"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean register(User user) {
        try {
            preparableStatement = connection
                    .prepareStatement("INSERT INTO `user` (`name`, `password`, `role`) VALUES (?, ?, ?)");
            preparableStatement.setString(1, user.getUsername());
            preparableStatement.setString(2, user.getPassword());
            preparableStatement.setString(3, user.getRole());
            preparableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
