package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

	public Dbconnection()
	{
		
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			
		}
	}

	public Connection getConnection() throws SQLException {
			System.out.println("connection sucessful");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskschedule","root","root");
	
		return con;
		
	}
}
