package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;










import dao.Dbconnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

public class MainScreen extends JFrame {

	private static JPanel contentPane;
	private JTextField txtLocalhost;
	private JTextField textField_1;
	Dbconnection db=new Dbconnection();
	java.sql.PreparedStatement ps=null;
	ResultSet rs=null;
	
//	 Image img = Toolkit.getDefaultToolkit().getImage("Images\\vmlogin.jpg");
	
	
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
	
		//String path = "Image1.jpg";
       // File file = new File("Images\\vmlogin.jpg");
       // BufferedImage image = ImageIO.read(file);
       // JLabel labelbc= new JLabel(new ImageIcon(image));
		
        //contentPane.add(labelbc);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	
	
	
	
	
	public MainScreen() {
		
		
		
		//setContentPane(new JLabel(new ImageIcon("Images\\vmlogin.jpg")));
		
		
		setTitle("HomeScreen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 643);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		
		
		
		setSize(800,600);
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
       // setContentPane(new JLabel(new ImageIcon("Images\\img1.png")));

		
		JLabel lblTruthfulGreedy = new JLabel("A  Cloud VM task scheduling algorithms");
		lblTruthfulGreedy.setForeground(Color.BLUE);
		lblTruthfulGreedy.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTruthfulGreedy.setBounds(270, 32, 641, 28);
		contentPane.add(lblTruthfulGreedy);
		
		JLabel lblDataCenter = new JLabel("Server Login");
		lblDataCenter.setBackground(Color.BLUE);
		lblDataCenter.setFont(new Font("Verdana", Font.BOLD, 16));
		lblDataCenter.setBounds(564, 246, 220, 28);
		contentPane.add(lblDataCenter);
		
		JLabel lblNewLabel = new JLabel("Server name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(502, 322, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("port Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(502, 378, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		txtLocalhost = new JTextField();
		txtLocalhost.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLocalhost.setText("Localhost");
		txtLocalhost.setBounds(626, 320, 116, 20);
		contentPane.add(txtLocalhost);
		txtLocalhost.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setText("4543");
		textField_1.setBounds(626, 376, 116, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		 final String ip;
		 final String port12;
		JButton btnLogin = new JButton("Connect");
		btnLogin.setBackground(Color.BLUE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ipaddress=txtLocalhost.getText().toString();
				String port=textField_1.getText().toString();
				if(ipaddress.equals(null) || port.equals(null))
				{
					JOptionPane.showMessageDialog(null, "Please Enter All the fields");
				}
				else
				{
					try {
						Connection con=db.getConnection();
						ps=con.prepareStatement("select * from serverdetails");
					    rs =ps.executeQuery();
					    while(rs.next())
					    {
						rs.getString(1);
					   rs.getString(2);
					   if( ipaddress .equalsIgnoreCase(rs.getString(1)) && port.equalsIgnoreCase(  rs.getString(2)))
					   {
						   JOptionPane.showMessageDialog(null, "Server Login Succcess !!!!");
						//   new ServerClass( Integer.parseInt(rs.getString(2)));
						  VMHomeScreen vf=new VMHomeScreen();
						   vf.setVisible(true);
						   
					   }
					    }
					   
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnLogin.setBounds(615, 437, 124, 23);
		contentPane.add(btnLogin);
		
		JLabel label = new JLabel("");
		label.setBackground(Color.PINK);
		
		label.setIcon((new ImageIcon("Images\\login_img.gif")));
		
		label.setBounds(20, 131, 472, 431);
		contentPane.add(label);
		
	}
}
