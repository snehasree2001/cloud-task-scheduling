package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;

import dao.Dbconnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.SystemColor;

public class VMFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMFrame frame = new VMFrame();
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
	public VMFrame() {
		setTitle("Virtual Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 436);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(850,400);

		JLabel lblNewLabel = new JLabel("Virtual Machine Configuration");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(162, 26, 183, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblVmName = new JLabel("VM name");
		lblVmName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblVmName.setBounds(76, 78, 72, 14);
		contentPane.add(lblVmName);
		
		textField = new JTextField();
		textField.setBounds(229, 76, 116, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblVmIpAddress = new JLabel("VM Ip address");
		lblVmIpAddress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblVmIpAddress.setBounds(76, 121, 111, 14);
		contentPane.add(lblVmIpAddress);
		
		textField_1 = new JTextField();
		textField_1.setBounds(229, 119, 116, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("VM Port number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(76, 169, 107, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(229, 167, 116, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(229, 210, 116, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblMemory = new JLabel("Maximum Memory");
		lblMemory.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblMemory.setBounds(76, 213, 111, 14);
		contentPane.add(lblMemory);
		
		JLabel lblNewLabel_2 = new JLabel("Request Threshold");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setBounds(76, 260, 129, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(229, 258, 116, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String name= 	 textField.getText().toString();
			String ip= textField_1.getText().toString();
			String port=	textField_2.getText().toString();
			String memory=	textField_3.getText().toString();
			String threshold=	textField_4.getText().toString();
			
			Dbconnection dbc=new Dbconnection();
			try {
				
				Connection con=dbc.getConnection();
				
				if(name.equals("") || ip.equals("") || port.equals("") || memory.equals("") || threshold.equals("") )
				{
					JOptionPane.showMessageDialog(null, "Please Enter All vm details !!!");
				}
				else
				{
				java.sql.PreparedStatement ps=con.prepareStatement("insert into vmdetails values('"+name+"','"+ip+"','"+port+"','"+memory+"','"+threshold+"')");
				int i=ps.executeUpdate();
				if(i>0)
				{
					JOptionPane.showMessageDialog(null, "VM is Configured ");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Sorry not configured");
				}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnSubmit.setBounds(65, 317, 155, 23);
		contentPane.add(btnSubmit);
		
		JButton btnLoginVmMachine = new JButton("Login VM Machine");
		btnLoginVmMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VMLogin fram=new VMLogin();
				fram.setVisible(true);
			}
		});
		btnLoginVmMachine.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnLoginVmMachine.setBounds(255, 318, 169, 23);
		contentPane.add(btnLoginVmMachine);
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		lblNewLabel_3.setIcon((new ImageIcon("Images\\cc.jpg")));
		lblNewLabel_3.setBounds(381, 58, 443, 249);
		contentPane.add(lblNewLabel_3);
	}
}
