package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.Dbconnection;

import javax.swing.JScrollPane;

import dao.Dbconnection;

import java.awt.Color;
import java.awt.Font;

public class DispalyFinalDataset extends JFrame {

	private JPanel contentPane;
	//DefaultTableModel model = new DefaultTableModel();
	  DefaultTableModel model = new DefaultTableModel();
	  	
		
		JTable table;
	
		String s1;
		String s2;
		
		
		String s3;
		
		
	    
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DispalyFinalDataset frame = new DispalyFinalDataset();
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
	public DispalyFinalDataset() {
		setFont(new Font("Times New Roman", Font.BOLD, 14));
		setTitle(" A Effective Optimization of Tasks in Cloud Computing ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1073, 476);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
        model.addColumn(" File Path Details ");
        model.addColumn(" File Info");
        
        model.addColumn(" Date of Uplo");
        
       
        int count=0;
		
		ResultSet rs=null;
		
		Dbconnection dbconection=new Dbconnection();
		
		Connection con;
		try {
			con = dbconection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from file_info");
			rs=ps.executeQuery();
			while(rs.next())
			{
				
				
				
				s1=rs.getString(1);
				
				s2=rs.getString(2);
				 
				s3=rs.getString(5);
				
				
				
				model.addRow(new Object[]{s1,s2,s3});
					
				
			
				
				
			
				
				
}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 22, 1005, 380);
		contentPane.add(scrollPane);
		
		table_1 = new JTable(model);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table_1.setBackground(new Color(250, 235, 215));
		scrollPane.setViewportView(table_1);
	}
}
