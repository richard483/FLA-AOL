package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import models.Admin;
import models.Member;
import models.ProMember;
import models.ShortLink;
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
                    return new Member(username, password);
                } else if (resultset.getString("role").equals("pro-member")) {
                    return new ProMember(username, password);
                } else {
                    return new Admin(username, password);
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

    public boolean addLink(String linkName, String link) {
        try {
            resultset = statement.executeQuery("SELECT * FROM `short-link` WHERE `link_name` = '" + linkName + "'");
            if (resultset.next()) {
                System.err.println("Link name already exists");
                return false;
            }

            preparableStatement = connection
                    .prepareStatement("INSERT INTO `short-link` (`link_name`, `long_link`) VALUES (?, ?)");
            preparableStatement.setString(1, linkName);
            preparableStatement.setString(2, link);
            preparableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ShortLink getLink(String linkName) {
        try {
            resultset = statement.executeQuery("SELECT * FROM `short-link` WHERE `link_name` = '" + linkName + "'");
            if (resultset.next()) {
                System.out.println(resultset.getString("long_link"));
                return new ShortLink(Integer.parseInt(resultset.getString("id").toString()),
                        resultset.getString("link_name"),
                        resultset.getString("long_link"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean removeLink(String linkName) {
        try {
            preparableStatement = connection.prepareStatement("DELETE FROM `short-link` WHERE `link_name` = ?");
            preparableStatement.setString(1, linkName);
            preparableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<ShortLink> getAllLinks() {
        ArrayList<ShortLink> links = new ArrayList<ShortLink>();
        try {
            resultset = statement.executeQuery("SELECT * FROM `short-link`");
            while (resultset.next()) {
                links.add(new ShortLink(Integer.parseInt(resultset.getString("id").toString()),
                        resultset.getString("link_name"),
                        resultset.getString("long_link")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return links;
    }
}
