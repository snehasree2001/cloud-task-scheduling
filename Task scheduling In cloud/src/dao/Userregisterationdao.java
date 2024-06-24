package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Userregisterationdao {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	

	public int  user_Registeration(String user_Name, String user_Password,String user_Mail, String user_phone_No,String role) {
		// TODO Auto-generated method stub
		
		//int server_Port=Integer.parseInt(server_Portno);
		
		/*****open db and update information*************/
		Dbconnection connecion=new Dbconnection();
		
		int result = 0;
		try {
			con=connecion.getConnection();
			if(con!=null)
			{
				System.out.println("connection sucessful");
				
				ps=con.prepareStatement("insert into clientinfo values(?,?,?,?)");
				
				ps.setString(1,user_Name);
				
				ps.setString(2,user_Password);
				
				ps.setString(3,user_Mail);
				
				ps.setString(4,user_phone_No);
				
				
				ps.executeUpdate();
				
				
				
				
				
			}
			else
			{
				
				System.out.println("connection Failure");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	

}
