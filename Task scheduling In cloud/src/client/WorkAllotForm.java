package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import bean.Bean;
import dao.Dbconnection;

import javax.swing.JLabel;

import java.awt.SystemColor;

public class WorkAllotForm extends JFrame {

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
		
		static String role;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkAllotForm frame = new WorkAllotForm(role);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 * @param role2 
	 */
	public WorkAllotForm(final String role) {
		
		
		 this.role=role;
		
		
		final Bean b=new Bean();
		
		
		setTitle("Choose File and Upload");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 429);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 192, 203));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(850,500);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(178, 82, 248, 191);
		contentPane.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton(" File Selection");
		btnNewButton.setForeground(new Color(255, 0, 255));
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JFileChooser chooser = new JFileChooser();
	                chooser.showOpenDialog(null);
	               file = chooser.getSelectedFile();
	                fpath= file.getPath();
	                filename = file.getName();
	                textField.setText(fpath);
	                System.out.println("You have selected: " + filename);

					 FileReader reader = null;
					try {
						reader = new FileReader(file);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                 BufferedReader br = new BufferedReader(reader);
	                 
	                 String str;
	                 try {
						while ((str = br.readLine()) != null) {
							 textArea.append(str+"\n");
						 }
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 47, 158, 29);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(178, 51, 248, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		ButtonGroup group = new ButtonGroup();
		
		JButton btnProcess = new JButton("particle swarm optimization");
		btnProcess.setForeground(new Color(199, 21, 133));
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//data
				
				//file upload process
				
				 Dbconnection connecion=new Dbconnection();
			        
			        try {
						con=connecion.getConnection();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						
						if(con!=null)
						{
							
							
							System.out.println("connection successful");
							
							
							
							
							ps=con.prepareStatement("insert into file_info values(?,?,?,?,?,?)");
							
							
							//System.out.println("File Content"+str);
							
							
							
							ps.setString(1,textField.getText());
							ps.setString(2, textArea.getText());
							ps.setString(3,role);
							
							ps.setString(4,filename);
							
							
							
							Date date = new Date();  
							SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
							 String strDate = formatter.format(date);  
							 System.out.println("Date Format with MM/dd/yyyy : "+strDate);  
							
							ps.setString(5,strDate);

							ps.setString(6,strDate);
							
							ps.executeUpdate();
							
							JOptionPane.showMessageDialog(null, " User File Upload In cloud Server succesfully ");

							//String f_name,final String f_content,String user_Name
							
							
							//Keygeneration key=new Keygeneration();
							//key.setVisible(true);
							
							
							//System.out.println("connection successful code ids"+usercode_Db);
							
							
							
							
						}
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
				}
					
				
				
				
				
				
				
				
			
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnProcess.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnProcess.setBounds(10, 111, 158, 39);
		contentPane.add(btnProcess);
		
		JButton btnClear = new JButton("Home page");
		btnClear.setForeground(new Color(255, 0, 0));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnClear.setBounds(311, 374, 123, 39);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel = new JLabel("A  Cloud VM task scheduling algorithms");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(153, 11, 408, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnMake = new JButton("Cloud  Res Process");
		btnMake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				WorkAllotFormresponse frame = new WorkAllotFormresponse(role);
				frame.setVisible(true);
				
			}
		});
		btnMake.setForeground(Color.RED);
		btnMake.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnMake.setBounds(178, 374, 123, 39);
		contentPane.add(btnMake);
		
		JLabel label = new JLabel("");
		label.setIcon((new ImageIcon("Images\\cc.jpg")));

		label.setBackground(Color.PINK);
		label.setBounds(436, 34, 388, 347);
		contentPane.add(label);
		
		JButton btnAntColonyOptimisation = new JButton("ant colony optimisation");
		btnAntColonyOptimisation.setForeground(new Color(199, 21, 133));
		btnAntColonyOptimisation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 7));
		btnAntColonyOptimisation.setBounds(10, 173, 158, 39);
		contentPane.add(btnAntColonyOptimisation);
		
		JButton btnFirstComeFirst = new JButton("first come first serve ");
		btnFirstComeFirst.setForeground(new Color(199, 21, 133));
		btnFirstComeFirst.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnFirstComeFirst.setBounds(10, 234, 158, 39);
		contentPane.add(btnFirstComeFirst);
		
		JButton btnShortestJobFirst = new JButton(" shortest job first scheduling");
		btnShortestJobFirst.setForeground(new Color(199, 21, 133));
		btnShortestJobFirst.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnShortestJobFirst.setBounds(10, 297, 158, 39);
		contentPane.add(btnShortestJobFirst);
		
		JButton btnRoundRobin = new JButton("Round Robin");
		btnRoundRobin.setForeground(new Color(199, 21, 133));
		btnRoundRobin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnRoundRobin.setBounds(10, 361, 158, 39);
		contentPane.add(btnRoundRobin);
		
		
	}
	
	


	public boolean update_Resources(String vm_toupdate) throws SQLException
	{
		boolean update_result=false;
		con.prepareStatement("update ");
		
		return update_result;
		
	}
}
