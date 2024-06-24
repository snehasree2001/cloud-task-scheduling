package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import dao.Dbconnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.SystemColor;

public class VMWorkProcessFrame extends JFrame {

	private JTable table;
	DefaultTableModel model  = new DefaultTableModel();
	private JPanel contentPane;
	String servermessage;
	ObjectInputStream ois=null;
	ObjectOutputStream oos=null;
	ServerSocket serSoc=null;
	Socket soc=null;
	String workname;
	public String name;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					VMWorkProcessFrame frame = new VMWorkProcessFrame();
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VMWorkProcessFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 459);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(1105,600);

	  final JTextArea textArea = new JTextArea();
	  textArea.setBackground(SystemColor.info);
		textArea.setBounds(24, 76, 186, 219);
		contentPane.add(textArea);
		
		JLabel lblReceivedWorkLoad = new JLabel("Received Work Load");
		lblReceivedWorkLoad.setFont(new Font("Cambria", Font.BOLD, 12));
		lblReceivedWorkLoad.setBounds(27, 32, 127, 33);
		contentPane.add(lblReceivedWorkLoad);
		
		JButton btnNewButton = new JButton("View Load");
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dbconnection b=new Dbconnection();
				try {
					Connection con=b.getConnection();
					PreparedStatement ps=con.prepareStatement("Select workload from workload where vmname='"+name +"' ");
					
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						textArea.append( "Received load name is " +rs.getString(1)+"\n");
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton.setBounds(47, 335, 127, 33);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(246, 76, 289, 167);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		model.addColumn("Memory in(GB)");
		model.addColumn("Threshold");
		
	   
		JLabel lblAvailableResources = new JLabel("Available Resources");
		lblAvailableResources.setFont(new Font("Cambria", Font.BOLD, 12));
		lblAvailableResources.setBounds(265, 42, 186, 23);
		contentPane.add(lblAvailableResources);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(Color.BLUE);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 model.setRowCount(0);
				 
			}
		});
		btnRefresh.setFont(new Font("Cambria", Font.BOLD, 12));
		btnRefresh.setBounds(221, 337, 108, 29);
		contentPane.add(btnRefresh);
		
		JButton btnGetCurrentResource = new JButton("Get Current Resource");
		btnGetCurrentResource.setBackground(Color.BLUE);
		btnGetCurrentResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dbconnection b=new Dbconnection();
				try {
					Connection con=b.getConnection();
					
					PreparedStatement ps1=con.prepareStatement("Select * from vmdetails where vmname='"+name +"' ");
					ResultSet rs1=ps1.executeQuery();
					
					while(rs1.next())
					{
						if(rs1.getString(5).toString().equals("0"))
						{
					PreparedStatement ps4=con.prepareStatement("update vmdetails set threshold='"+10+"' where vmname='"+name +"' ");
					ps4.executeUpdate();
					JOptionPane.showMessageDialog(null, "Resources received from Mainserver");
						}
						model.addRow(new Object[]{rs1.getString(4),rs1.getString(5)});
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnGetCurrentResource.setFont(new Font("Cambria", Font.BOLD, 12));
		btnGetCurrentResource.setBounds(383, 335, 175, 29);
		contentPane.add(btnGetCurrentResource);
		
		JLabel label = new JLabel("");
		label.setIcon((new ImageIcon("Images\\cc.jpg")));
		label.setBackground(Color.PINK);
		label.setBounds(566, 0, 452, 431);
		contentPane.add(label);
		
		
	}
	public void turnonVm(String vname,String vport)
	{
		name=vname;
		setTitle(vname + " **Work Process** ");
	}
	public void turnonVm1(String vname,String vport)
	{
		setTitle(vname + " **Work Process** ");
		try
		{
	serSoc=new ServerSocket(4543);
	
	System.out.println("Virtual machine port Number============"+vport);

		while(true)
		{
	soc=serSoc.accept();

	ois=new ObjectInputStream(soc.getInputStream());
	
	servermessage=(String)ois.readObject();
	
	if(servermessage.equals("getload"))
	{
		workname=(String)ois.readObject();
	}
	
	
		}

		}catch(Exception e4)
		{
			e4.printStackTrace();
		}
	
	}
}
