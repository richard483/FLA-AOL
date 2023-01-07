package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

    public ResultSet getData(String query) {
        try {
            resultset = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultset;
    }
}
