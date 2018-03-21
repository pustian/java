package tian.pusen.java7.autoclose;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSetSchema {
	public void setSchema() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:derby://localhost/java7book")) {
            connection.setSchema("DEMO_SCHEMA");
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM author")) {
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }
        }
    }
    public static void main(String[] args) {
    	JDBCSetSchema ss = new JDBCSetSchema();
        try {
            ss.setSchema();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
