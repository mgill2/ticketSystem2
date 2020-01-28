import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class test {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		
		passwordField = new JPasswordField();
		
		JLabel lblPassword = new JLabel("Password");
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				passwordField.selectAll();
				
				if(textField.getText().equals("jpapa") && passwordField.getText().equals("thebest"))
				{
					
					frame.dispose();
					try
					{ 
					   adminGUI.main();
					   //adminGUI.main();
					}catch(Exception e1){e1.printStackTrace();}
				}
				else if(textField.getText().equals("usr") && passwordField.getText().equals("help"))
				{
					frame.dispose();
					
					try
					{
					   userLogin.main();
					}catch(Exception e2){e2.printStackTrace();}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Your username or password is incorrect please try again!", null, JOptionPane.PLAIN_MESSAGE, null);
					System.out.println("The username or password is incorrect please try again!");
				}
			}
		});
		
		JButton btnContactInfo = new JButton("Contact Info");
		btnContactInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane.showMessageDialog(null, "Name:  IIT Bank Ticket System \nLocation:  test"
						+ "\nTelephone: \nContact Person: James test (aka IT Guru)", null, JOptionPane.PLAIN_MESSAGE, null);
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(195)
							.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(123)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField, Alignment.TRAILING)
								.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(186)
							.addComponent(lblPassword))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnExit)
							.addPreferredGap(ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
							.addComponent(btnLogin))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnContactInfo)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnContactInfo)
					.addGap(34)
					.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnExit))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
