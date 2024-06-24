package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import bean.Bean;

import dao.Dbconnection;

public class ServerClass implements Runnable{
	int serverPort; 
	ServerSocket serversoc;
	Socket socket;
	int memory;
	int threshold;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Bean ff=new Bean();
	 Dbconnection dbc=new Dbconnection();
	 Connection con=null;
	 PreparedStatement ps=null;
	 ResultSet rs=null;
	public ServerClass(int serverPort)
	{
		this.serverPort=serverPort;
		Thread t=new Thread(this);
		t.start();
	}
	public void run()
	{
		startServer();
		processClientRequest();
	}
	public void startServer()
	{
		try 
		{
			serversoc=new ServerSocket(serverPort);
			System.out.println("Server has been binded at the Port:"+serverPort+"\n");
		} 
		catch (IOException e) 
		{
			System.out.println("Some Process binds at the Port:"+serverPort+" already. Use Different Port or Kill the process at the port");
			System.exit(0);
		}
	}
	public void processClientRequest()
	{
		long countRequest=0; 
		try 
		{
			while(true)
			{
				socket=serversoc.accept();
				System.out.println("Accepted a request("+(++countRequest)+") from Client");
				//ois.read();
			//	JOptionPane.showMessageDialog(null, "Acknowledgement received");
			//	RequestWorkerThread rwt=new RequestWorkerThread(socket);
			//	Thread thread = new Thread(rwt);
			//	thread.start();
				ois=new ObjectInputStream(socket.getInputStream());

				String msg = null;
				try {
					msg = (String)ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				oos=new ObjectOutputStream(socket.getOutputStream());

				// Node Initialization //

				if(msg.equals("Cheaper Service"))
				{
					try {
						con=dbc.getConnection();
						ps=con.prepareStatement("select * from vmdetails where threshold=(select min(threshold) from vmdetails)  ");
						rs=ps.executeQuery();
						while(rs.next())
						{
						String vim_name=	rs.getString(1);
						String vim_ip=	rs.getString(2);
						int vim_port=Integer.parseInt(rs.getString(3))	;
							rs.getString(4);
							rs.getString(5);
							System.out.println("Allotted vm is"+rs.getString(1));
							JOptionPane.showMessageDialog(null, "Allotted vm is"+rs.getString(1));
							String filename = null;
							try {
								filename = (String)ois.readObject();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("received Work is "+filename);
							ps=con.prepareStatement(" insert into workload  values('"+rs.getString(1)+"','"+filename+"')");
							int y=ps.executeUpdate();
							if(y>0)
							{
								System.out.println("inserted into load table");

								try
								{
								
								ps=con.prepareStatement("Select memory,threshold from vmdetails where vmname='"+rs.getString(1)+"' ");
							    rs=ps.executeQuery();
								while(rs.next())
								{
								memory =	Integer.parseInt(rs.getString(1));
								threshold =	Integer.parseInt(rs.getString(2));
								int memoryNew=memory-1;
								int thresholdNew=threshold-1;
								System.out.println(memoryNew+""  +  thresholdNew+"");
								ps=con.prepareStatement("update vmdetails set memory='"+memoryNew+""+"' and threshold='"+thresholdNew+""+"' where vmname='"+rs.getString(1)+"'");
								ps.executeUpdate();
								}
								}catch(Exception e){
									
								}finally
								{
									//con.close();
									//ps.close();
									//rs.close();
								}
							}
							else
							{
								System.out.println(" not inserted into load table");
							}
							//Socket soc1=new Socket(vim_ip,vim_port);
							System.out.println("Vm ip  "+vim_ip  +"vim_port" + vim_port);
							//ObjectOutputStream oos1=new ObjectOutputStream(soc1.getOutputStream());
							oos.writeObject("getload");
							oos.writeObject(filename);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally
					{
						
					}
				}
				else if(msg.equals("Costlier Service"))
				{
					try {
						con=dbc.getConnection();
						ps=con.prepareStatement("select * from vmdetails where threshold=(select max(threshold) from vmdetails)  ");
						rs=ps.executeQuery();
						while(rs.next())
						{
							String vim_name=	rs.getString(1);
							String vim_ip=	rs.getString(2);
							int vim_port=Integer.parseInt(rs.getString(3));
							      rs.getString(4);
							      rs.getString(5);
							System.out.println("Allotted vm is"+rs.getString(1));
							JOptionPane.showMessageDialog(null, "Allotted vm is"+rs.getString(1));
							String filename1 = null;
							try {
								filename1 = (String)ois.readObject();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("received Work is "+filename1);
							ps=con.prepareStatement("insert into workload values('"+vim_name+"','"+filename1+"')");
							int y=ps.executeUpdate();
							if(y>0)
							{
								System.out.println("inserted into load table");
								try
								{
								
								ps=con.prepareStatement("Select memory,threshold from vmdetails where vmname='"+rs.getString(1)+"' ");
							    rs=ps.executeQuery();
								while(rs.next())
								{
								memory =	Integer.parseInt(rs.getString(1));
								threshold =	Integer.parseInt(rs.getString(2));
								int memoryNew=memory-1;
								int thresholdNew=threshold-1;
								System.out.println(memoryNew+""  +  thresholdNew+"");
								ps=con.prepareStatement("update vmdetails set memory='"+memoryNew+""+"' and threshold='"+thresholdNew+""+"' where vmname='"+rs.getString(1)+"'");
								ps.executeUpdate();
								}
								}catch(Exception e){
									
								}finally
								{
									//con.close();
									//ps.close();
									//rs.close();
								}
							}
							else
							{
								System.out.println(" not inserted into load table");
							}
							//Socket soc1=new Socket(vim_ip,vim_port);
							//ObjectOutputStream oos1=new ObjectOutputStream(soc1.getOutputStream());
							oos.writeObject("getload");
							oos.writeObject(filename1);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally
					{
											
					}
				}
				else if(msg.equals("Optimal Service"))
				{
					try {
						con=dbc.getConnection();
						ps=con.prepareStatement("select * from vmdetails where threshold=(select max(threshold) from vmdetails)  ");
						rs=ps.executeQuery();
						while(rs.next())
						{
						String vim_name=	rs.getString(1);
						String vim_ip=	rs.getString(2);
						int vim_port=Integer.parseInt(rs.getString(3))	;
							rs.getString(4);
							rs.getString(5);
							System.out.println("Allotted vm is"+rs.getString(1));
							JOptionPane.showMessageDialog(null, "Allotted vm is"+rs.getString(1));
							String filename2 = null;
							try {
								filename2 = (String)ois.readObject();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("received Work is "+filename2);
							ps=con.prepareStatement("insert into workload  values('"+vim_name+"','"+filename2+"')");
							int y=ps.executeUpdate();
							if(y>0)
							{
								System.out.println("inserted into load table");
								try
								{
									
									System.out.println("I am rs string "+rs.getString(1));
								
								ps=con.prepareStatement("Select memory,threshold from vmdetails where vmname='"+rs.getString(1)+"' ");
							    rs=ps.executeQuery();
								while(rs.next())
								{
								memory =	Integer.parseInt(rs.getString(1));
								threshold =	Integer.parseInt(rs.getString(2));
								int memoryNew=memory-1;
								int thresholdNew=threshold-1;
								
								
								
								System.out.println(memoryNew+""  +  thresholdNew+"");
								ps=con.prepareStatement("update vmdetails set memory='"+memoryNew+""+"' and threshold='"+thresholdNew+""+"' where vmname='"+rs.getString(1)+"'");
								ps.executeUpdate();
								}
								}catch(Exception e){
									
								}finally
								{
									//con.close();
									//ps.close();
									//rs.close();
								}
							}
							else
							{
								System.out.println(" not inserted into load table");
							}
							//Socket soc1=new Socket(vim_ip,vim_port);
							//ObjectOutputStream oos1=new ObjectOutputStream(soc1.getOutputStream());
							oos.writeObject("getload");
							oos.writeObject(filename2);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally
					{
						
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Message not equals");
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
