package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;



import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import dao.Userlogindao;
import java.awt.SystemColor;

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		setTitle("UserLogin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 406);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(850,600);
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(104, 144, 64, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(243, 142, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PassWord");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(104, 223, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(243, 221, 168, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent user_Login) {
				
				
				
				
				
				
				/******get client details and compare with database**********/
				String user_Name=textField.getText();
				String user_Pass=textField_1.getText();
				Userlogindao userlogin=new Userlogindao();
				
				
				
				int login_Result1=0;
				
				

				if(user_Name.equalsIgnoreCase("")||user_Pass.equalsIgnoreCase(""))
				{
					
					JOptionPane.showMessageDialog(null, " User Name and Password should be Fill");
				}
				
				
				else
				{
					String  login_Result=userlogin.user_loginprocess(user_Name,user_Pass);
					if(login_Result!=null)
					{
						
						System.out.println("login sucessful");
						JOptionPane.showMessageDialog(null," User Login  sucess"+login_Result);
						WorkAllotForm fff=new WorkAllotForm(login_Result);
						fff.setVisible(true);
						
						
						
						/************show client process frame*********/
						//Clientprocess clientprocess=new Clientprocess(user_Name);
					//	clientprocess.main(null);
						
						dispose();
					}
					else
					{
						
						System.out.println("login fail");
						JOptionPane.showMessageDialog(null," User Login  failed");
					}
					
					
				}
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(241, 287, 82, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent calcel) {
				
				
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(331, 287, 82, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login Process");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(254, 57, 107, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setIcon((new ImageIcon("Images\\login_img.gif")));
		label.setBackground(Color.PINK);
		label.setBounds(423, 22, 401, 459);
		contentPane.add(label);
	}

}
