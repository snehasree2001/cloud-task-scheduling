package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import bean.Bean;
import dao.Dbconnection;

import javax.swing.JLabel;

import java.awt.SystemColor;

public class WorkAllotFormresponse extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	 String fpath,filename;
	 File file;
	 Dbconnection dbc=new Dbconnection();
	 Connection con=null;
	 PreparedStatement ps=null;
	 ResultSet rs=null;
	 ObjectInputStream ois=null;
		ObjectOutputStream oos=null;
		ServerSocket serSoc=null;
		Socket soc=null;
		final JTextArea textArea;
		static String role;
		 JButton btnMake;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkAllotFormresponse frame = new WorkAllotFormresponse(role);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 * @param  
	 */
	public WorkAllotFormresponse(final String role) {
		
		
		
		this.role=role;
		
		
		
		final Bean b=new Bean();
		setTitle("Choose File and Upload");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 429);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(178, 82, 248, 161);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("File Request  Process");
		btnNewButton.setForeground(new Color(255, 0, 255));
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				//
				
				
				String data_info;
				
				

				
				
				
				String search_info=textField.getText();
				
				
				
				
			  //  JOptionPane.showMessageDialog(null," User Login  sucess");
			    
			    
			    
			    
			    
			    if(role.equalsIgnoreCase("company") || role.equalsIgnoreCase("Nonitcompany") )
			    
			    {
			    	
			    	
			    	
				    JOptionPane.showMessageDialog(null," Hi Cloud User Your Request Will Be Handle by server with High priority ");
					
				    int value = ThreadLocalRandom.current().nextInt(1000, 2000);
					
					
					
					
					
					btnMake.setText(String.valueOf(value)+"ms");
					
				    

			    	
			    	
			    }
				
				
				
			    if(role.equalsIgnoreCase("clouduser") || role.equalsIgnoreCase("employee") )
				    
			    {
			    	
			    	int value = ThreadLocalRandom.current().nextInt(3000, 4000);
					
			    	try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					
					
					
					btnMake.setText(String.valueOf(value)+"ms");
					
			    	
				    JOptionPane.showMessageDialog(null," Hi Cloud User Your Request Will Be  Handle  by server with Medium priority ");

			    	
			    	
			    }
				
				
				
				
			    if(role.equalsIgnoreCase("student") || role.equalsIgnoreCase("other") )
				    
			    {
			    	
			    	int value = ThreadLocalRandom.current().nextInt(3900, 5000);
					
			    	try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					btnMake.setText(String.valueOf(value)+"ms");
					
			    
				    JOptionPane.showMessageDialog(null," Hi Cloud User Your Request Will Be Handle by server with low priority ");

			    	
			    	
			    }
				
				
				
				
				
				
				//database open 
			    
			    
			    
			    
				Dbconnection connecion=new Dbconnection();
				
				int result = 0;
				try {
					con=connecion.getConnection();
					if(con!=null)
					{
						System.out.println("connection successful");
						
						ps=con.prepareStatement("select f_content from file_info where s4 ='"+search_info+"' ");
						
						rs=ps.executeQuery();
						while(rs.next())
						{
							
							
							data_info=rs.getString(1);
							
							textArea.append(data_info);
							
							
							
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
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnNewButton.setBounds(10, 48, 158, 29);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(178, 51, 278, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		ButtonGroup group = new ButtonGroup();
		
		JButton btnProcess = new JButton("View  My Upload File");
		btnProcess.setForeground(new Color(199, 21, 133));
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				DispalyFinalDataset fs= new DispalyFinalDataset();
				
				fs.setVisible(true);
				
				
				
				
			}
		});
		btnProcess.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnProcess.setBounds(10, 130, 158, 39);
		contentPane.add(btnProcess);
		
		JButton btnClear = new JButton("Home page");
		btnClear.setForeground(new Color(255, 0, 0));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnClear.setBounds(377, 282, 153, 39);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel = new JLabel("Cloud Network Based on MAX-MIN, MIN-MIN and Priority");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		lblNewLabel.setBounds(75, 11, 408, 25);
		contentPane.add(lblNewLabel);
		
		 btnMake = new JButton("Response Time");
		btnMake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				int value = ThreadLocalRandom.current().nextInt(10, 100);
				
				
				
				
				
				btnMake.setText(String.valueOf(value));
				
				
			}
		});
		btnMake.setForeground(Color.RED);
		btnMake.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnMake.setBounds(178, 284, 189, 39);
		contentPane.add(btnMake);
		
		
		
		
		
	}
	
	public boolean update_Resources(String vm_toupdate) throws SQLException
	{
		boolean update_result=false;
		con.prepareStatement("update ");
		
		return update_result;
		
	}
}
