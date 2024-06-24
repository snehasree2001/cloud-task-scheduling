package main;

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

import javax.swing.JLabel;
import java.awt.SystemColor;

public class VMHomeScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMHomeScreen frame = new VMHomeScreen();
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
	public VMHomeScreen() {
		setTitle("VM Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 358);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(950,500);

		JButton btnVmRegistration = new JButton("VM Registration");
		btnVmRegistration.setBackground(Color.WHITE);
		btnVmRegistration.setForeground(Color.MAGENTA);
		btnVmRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VMFrame vf=new VMFrame();
				vf.setVisible(true);
			}
		});
		btnVmRegistration.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVmRegistration.setBounds(234, 182, 200, 23);
		contentPane.add(btnVmRegistration);
		
		JButton btnNewButton = new JButton("VM Login");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VMLogin vm=new VMLogin();
				vm.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(234, 231, 200, 23);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.RED);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(234, 292, 200, 23);
		contentPane.add(btnExit);
		
		JLabel label = new JLabel("");
		label.setIcon((new ImageIcon("Images\\cc.jpg")));
		label.setBounds(444, 80, 480, 314);
		contentPane.add(label);
	}
}
