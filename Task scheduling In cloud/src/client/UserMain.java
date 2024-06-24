package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;

public class UserMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain frame = new UserMain();
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
	public UserMain() {
		setTitle("A  Cloud  VM task scheduling algorithms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 475);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(900,600);
		
		JButton btnNewButton = new JButton("Client Registration");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserRegisteration un=new UserRegisteration();
				un.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton.setBounds(223, 132, 157, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Client Login");
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				UserLogin ul=new UserLogin();
				ul.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_1.setBounds(223, 228, 157, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton_2.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_2.setBounds(223, 346, 157, 36);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("A  Cloud VM task scheduling algorithms ");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel.setBounds(191, 68, 537, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("");
		
		label_1.setIcon((new ImageIcon("Images\\cc.jpg")));
		
		label_1.setBounds(471, 125, 376, 249);
		contentPane.add(label_1);
	}

}
