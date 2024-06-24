package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import dao.Userregisterationdao;





import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import javax.swing.JCheckBox;



public class UserRegisteration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JCheckBox chckbxCompany;
	JCheckBox chckbxNonIt;
	JCheckBox chckbxUser;
	JCheckBox chckbxStudent;
	JCheckBox chckbxEmployee;
	JCheckBox checkBox_7;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegisteration frame = new UserRegisteration();
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
	public UserRegisteration() {
		setTitle("User Registeration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 433);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("Images\\register.jpg"));
		lblNewLabel.setBounds(10, 55, 291, 252);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(318, 47, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(392, 44, 271, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(318, 86, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(392, 84, 271, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("MailId");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(318, 129, 60, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(392, 127, 271, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Phone No");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_4.setBounds(318, 169, 60, 14);
		contentPane.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(390, 167, 273, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent user_Register) {
				
				System.out.println("usr register");
				String user_Name=textField.getText();
				String user_Password=textField_1.getText();
				String user_MailId=textField_2.getText();
				String user_phone_No=textField_3.getText();
				
				String regex = "^(.+)@(.+)$";
				
				String user_role = null;
				
				
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(user_MailId);
				
				
				
				String regex1 = "\\d{10}";
				
				
				
				
				
				if(chckbxCompany.isSelected())
					
				{
					
					//System.out.print("**select check is company ****");
					
					
					user_role="public";
					
					
				}
			
				
				if(chckbxNonIt.isSelected())
					
				{
					
					//System.out.print("**select check is company ****");
					user_role="private";
					
					
				}
				
				
				
				
			
				
				if(chckbxEmployee.isSelected())
					
				{
					
					//System.out.print("**select check is company ****");
					
					user_role="Hybrid";
					
				}
				
				
				
				
				if(user_Name.equalsIgnoreCase("")||user_Password.equalsIgnoreCase("")||user_MailId.equalsIgnoreCase("")||user_phone_No.equalsIgnoreCase(""))
				{
					
					JOptionPane.showMessageDialog(null, " All the Field should be Fill");
				}
				
				
				
				else if(user_phone_No.length()!=10)
					
				{
					
					JOptionPane.showMessageDialog(null, "PHONE NO SHOULD 10 DIGIT");
					
					
				}
				

				else if(!user_phone_No.matches(regex1))
					
				{
					
					JOptionPane.showMessageDialog(null, "PHONE NO SHOULD BE DIGIT");
					
					
				}
				
				
				
				
				else if(!matcher.matches())
					
				{
					
					JOptionPane.showMessageDialog(null, "Mail Id Should Be Proper Format");
					
					
				}
				
				else
				{
					
					
					
				
					
					Userregisterationdao userregisteration=new 	Userregisterationdao();
					int user_Result=userregisteration.user_Registeration(user_Name, user_Password, user_MailId,user_phone_No,user_role);
					//call db and register user information
					if(user_Result>=0)
					{
						System.out.println("user registeration sucess");
						JOptionPane.showMessageDialog(null," User Registration Successful ");
						UserLogin lll=new UserLogin();
						lll.setVisible(true);
					}
					else
					{
						
						System.out.println("user registeration sucess failure");
					}
					
					dispose();
					
					
					
					
					
					
					
				}
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(392, 297, 103, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent user_Canel) {
				
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_1.setBounds(542, 298, 103, 20);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("Register Process");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(429, 19, 133, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblUserType = new JLabel("Type");
		lblUserType.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblUserType.setBounds(318, 215, 60, 14);
		contentPane.add(lblUserType);
		
		chckbxCompany = new JCheckBox("Public");
		chckbxCompany.setFont(new Font("Tahoma", Font.BOLD, 7));
		chckbxCompany.setBounds(391, 213, 66, 23);
		contentPane.add(chckbxCompany);
		
		 chckbxNonIt = new JCheckBox("Private");
		chckbxNonIt.setFont(new Font("Tahoma", Font.BOLD, 8));
		chckbxNonIt.setBounds(463, 213, 66, 23);
		contentPane.add(chckbxNonIt);
		
	
		
		 chckbxEmployee = new JCheckBox("Hybrid");
		chckbxEmployee.setFont(new Font("Tahoma", Font.BOLD, 8));
		chckbxEmployee.setBounds(542, 213, 66, 23);
		contentPane.add(chckbxEmployee);
		
		
		textField_4 = new JTextField();
		textField_4.setBackground(Color.PINK);
		textField_4.setForeground(Color.PINK);
		textField_4.setBounds(305, 8, 397, 336);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
	
	
		
		
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
