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

import dao.Dbconnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.SystemColor;

public class VMLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	String vm_Name,vm_Port;
	Dbconnection db=new Dbconnection();
	PreparedStatement ps=null;
	Connection con=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMLogin frame = new VMLogin();
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
	public VMLogin() {
		setTitle("VM Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(850,500);

		JLabel lblNewLabel = new JLabel("VM Name");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel.setBounds(117, 126, 92, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("VM Port Number");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_1.setBounds(117, 215, 110, 32);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(233, 115, 118, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(233, 221, 118, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vm_Name =	textField.getText().toString();
				vm_Port =textField_1.getText().toString();
				
				if(vm_Name==null )
				{
					JOptionPane.showMessageDialog(null, "Please Enter all fields!!! ");
				}
				else if( vm_Port==null)
				{
					JOptionPane.showMessageDialog(null, "Please Enter all fields!!! ");
				}
				else if(vm_Name==null && vm_Port==null)
				{
					JOptionPane.showMessageDialog(null, "Please Enter all fields!!! ");
				}
				else
				{
					try {
					boolean h=	Check_vm(vm_Name,vm_Port);
					if(h==true)
					{   
						JOptionPane.showMessageDialog(null, "Login Success!!! ");
						VMWorkProcessFrame vim=new VMWorkProcessFrame();
						vim.turnonVm(vm_Name,vm_Port);
						 vim.setVisible(true);
						 
					}else
					{
						JOptionPane.showMessageDialog(null, "Login Failed!!! ");
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(154, 296, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				textField.setText("");
				textField_1.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.setBounds(264, 295, 102, 24);
		contentPane.add(btnReset);
		
		JLabel lblNewLabel_2 = new JLabel("");
		
		lblNewLabel_2.setIcon((new ImageIcon("Images\\cc.jpg")));
		
		lblNewLabel_2.setBounds(376, 78, 448, 230);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("VIRTUAL MACHINE LOGIN");
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_3.setBounds(154, 39, 197, 14);
		contentPane.add(lblNewLabel_3);
	}
	public boolean Check_vm(String vname, String vport) throws SQLException
	{
		
		
		boolean verify=false;
		try {
			con=db.getConnection();
			ps=con.prepareStatement("select * from vmdetails where vmname='"+vname+"' and vmport='"+vport+"'");
		ResultSet rs=	ps.executeQuery();
		while(rs.next())
		{
			rs.getString(1);
			rs.getString(3);
			verify=true;
		}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			con.close();
			ps.close();
			
		}
		return verify;
	}
}
