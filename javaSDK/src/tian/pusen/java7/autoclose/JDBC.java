package tian.pusen.java7.autoclose;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	public void query() throws SQLException{
		String url = "";
		String user ="";
		String password = "";
		String sql = "";
		// 注意语法
		try(Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql) ){
		}
		
	}
}
