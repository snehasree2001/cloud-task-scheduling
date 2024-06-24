package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Dbconnection;

public class Userlogindao {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String username_Db;
	String userpass_Db;
	String result_Status;
	
	
	
	String role_info;
	
	
	
	
	
	
	
	
	
	
	//method to get user details and compare with db 
	public String user_loginprocess(String user_Name, String user_Pass)
	{
		
		
		
		//open database and compare  string
		Dbconnection connecion=new Dbconnection();
		
		int result = 0;
		try {
			con=connecion.getConnection();
			if(con!=null)
			{
				System.out.println("connection successful");
				
				ps=con.prepareStatement("select user_name ,user_password,user_mail from clientinfo where user_name ='"+user_Name+"' ");
				
				rs=ps.executeQuery();
				while(rs.next())
				{
					
					
					username_Db=rs.getString(1);
					userpass_Db=rs.getString(2);
					
					role_info=rs.getString(3);
		
					
				}
				
				if(username_Db.equalsIgnoreCase(user_Name)&&userpass_Db.equalsIgnoreCase(user_Pass))
				{
					
					
					System.out.println("sucess");
					
					result_Status=user_Name;
				}
				else
				{
					
					System.out.println("failure");
					result_Status=role_info;
					//result_Status=-1;
				}
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
		
		
		try {
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return role_info;
	}
}
