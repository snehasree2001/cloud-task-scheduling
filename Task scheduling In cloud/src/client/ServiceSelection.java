package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Dbconnection;

public class ServiceSelection 
{
	Dbconnection db=new Dbconnection();
	PreparedStatement ps=null;
	Connection con;
	int memory;
	int threshold;
	ResultSet rs;
	
public void cheaper_Service(String vm) throws SQLException
{
	try
	{
	con=db.getConnection();
	ps=con.prepareStatement("Select memory,threshold from vmdetails where vmname='"+vm +"' ");
    rs=ps.executeQuery();
	while(rs.next())
	{
	memory =	Integer.parseInt(rs.getString(1));
	threshold =	Integer.parseInt(rs.getString(2));
	memory=memory-1;
	threshold=threshold-1;
	ps=con.prepareStatement("update vmdetails set memory='"+memory+"' and threshold='"+threshold+"' where vmname='"+vm+"'");
	ps.executeUpdate();
	}
	}catch(Exception e){
		
	}finally
	{
		con.close();
		ps.close();
		rs.close();
	}
}

public void costlier_Service(String vm) throws SQLException
{
	try
	{
	con=db.getConnection();
	ps=con.prepareStatement("Select memory,threshold from vmdetails where vmname='"+vm +"' ");
    rs=ps.executeQuery();
	while(rs.next())
	{
	memory =	Integer.parseInt(rs.getString(1));
	threshold =	Integer.parseInt(rs.getString(2));
	memory=memory-1;
	threshold=threshold-1;
	ps=con.prepareStatement("update vmdetails set memory='"+memory+"' and threshold='"+threshold+"' where vmname='"+vm+"'");
	ps.executeUpdate();
	}
	}catch(Exception e){
		
	}finally
	{
		con.close();
		ps.close();
		rs.close();
	}
}

public void optimal_Service(String vm) throws SQLException
{
	try
	{
	con=db.getConnection();
	ps=con.prepareStatement("Select memory,threshold from vmdetails where vmname='"+vm +"' ");
    rs=ps.executeQuery();
	while(rs.next())
	{
	memory =	Integer.parseInt(rs.getString(1));
	threshold =	Integer.parseInt(rs.getString(2));
	memory=memory-1;
	threshold=threshold-1;
	ps=con.prepareStatement("update vmdetails set memory='"+memory+"' and threshold='"+threshold+"' where vmname='"+vm+"'");
	ps.executeUpdate();
	}
	}catch(Exception e){
		
	}finally
	{
		con.close();
		ps.close();
		rs.close();
	}
}

}
