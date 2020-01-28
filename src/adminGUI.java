import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;
import java.awt.event.ActionEvent;


public class adminGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminGUI window = new adminGUI();
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
	public adminGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//color for title
		
		JLabel lblAdminLoginPage = new JLabel("Admin Login Page");
		lblAdminLoginPage.setForeground(Color.RED);
		lblAdminLoginPage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//connection names
		Connection conn = null;
		Statement statement = null;
		
		
		//start of select, delete, update, and closed ticket statements
		
		JButton btnSelectEmergency = new JButton("Select Emergency");
		btnSelectEmergency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connect = null;
				Statement statement = null;
						  
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      
				      // Setup the connection with the DB
				      connect = DriverManager.getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
				      
				      //Select Statement
				    
				      statement = connect.createStatement();
				      
				      String sql = "SELECT ticket_id, ticket_description, ticket_priority, ticket_time, ticket_closed "
				      		+ "FROM tickets.mgillticket WHERE ticket_priority = 'Emergency'";
						
					  ResultSet rs  = statement.executeQuery(sql);
				      System.out.println("Selecting the Emergency Tickets");
				      
				      //JTable for information
				     
					  JFrame frame = new JFrame();
					  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				      JScrollPane sp = null;
				      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
				      jt.setBounds(200,200,200,300);
			          sp = new JScrollPane(jt);
			          frame.getContentPane().add(sp);
			          frame.setVisible(true); //repaint frame on screen
				      
				    //Headings
			          /*
				      System.out.println("ID\t\tTicket Description\tTicket Priority\t\tTicket Time\t\tTicket Closed");
						System.out.println("--------\t-----------------\t--------------\t\t--------------\t\t---------------");
				     
				      while (rs.next())
						{
							//Getting the values
							String ticket_id = rs.getString("ticket_id");
							String ticket_description = rs.getString("ticket_description");
							String ticket_priority = rs.getString("ticket_priority");
							Timestamp ticket_time = rs.getTimestamp("ticket_time");
							Timestamp ticket_closed = rs.getTimestamp("ticket_closed");
														
							
							//Display the values
							System.out.println(ticket_id + "\t\t" +  ticket_description + "\t\t" + ticket_priority + "\t\t" + ticket_time  + "\t\t"
									+ ticket_closed);
							
							
						} rs.close();*/
					
						
				    //close connection/statement object  
				     statement.close();
				     connect.close();
				    } catch (Exception e) {
				    	System.out.println(e.getMessage());  
				    }  //ends catch statement
			}//ends action performed		
		});//ends action listener
		
		JButton btnNewButton = new JButton("Select High Priority");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect = null;
				Statement statement = null;
						  
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				      connect = DriverManager
				          .getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				      //Select Statement
				    
				      statement = connect.createStatement();
				      
				      String sql = "SELECT ticket_id, ticket_description, ticket_priority, ticket_time, ticket_closed "
				      		+ "FROM tickets.mgillticket WHERE ticket_priority = 'High Priority'";
						
					  ResultSet rs  = statement.executeQuery(sql);
				      System.out.println("Selecting the High Priority Tickets");
				      
				      JFrame frame = new JFrame();
					  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				      JScrollPane sp = null;
				      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
				      jt.setBounds(200,200,200,300);
			          sp = new JScrollPane(jt);
			          frame.getContentPane().add(sp);
			          frame.setVisible(true); //repaint frame on screen
				      
				      /*
				    //Headings
				      System.out.println("ID\t\tTicket Description\tTicket Priority\t\tTicket Time\t\tTicket Closed");
						System.out.println("--------\t-----------------\t--------------\t\t--------------\t\t---------------");
				      while (rs.next())
						{
							//Getting the values
							String ticket_id = rs.getString("ticket_id");
							String ticket_description = rs.getString("ticket_description");
							String ticket_priority = rs.getString("ticket_priority");
							Timestamp ticket_time = rs.getTimestamp("ticket_time");
							Timestamp ticket_closed = rs.getTimestamp("ticket_closed");
							
							//Display the values
							System.out.println(ticket_id + "\t\t" +  ticket_description + "\t\t" + ticket_priority + "\t\t" + ticket_time  + "\t\t"
									+ ticket_closed);
							
						}rs.close(); */ //closes the loop
				    
			          //close connection/statement object  
				     statement.close();
				     connect.close();
				    } catch (Exception e2) {
				    	System.out.println(e2.getMessage());  
				    }  //ends catch statement
			}//ends action performed		
		});//ends action listener
		
		JButton btnSelectMediumPriority = new JButton("Select Medium Priority");
		btnSelectMediumPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect = null;
				  Statement statement = null;
						  
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				      connect = DriverManager
				          .getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				      //Select Statement
				    
				      statement = connect.createStatement();
				      
				      String sql = "SELECT ticket_id, ticket_description, ticket_priority, ticket_time, ticket_closed "
				      		+ "FROM tickets.mgillticket WHERE ticket_priority = 'Medium Priority'";
						
						ResultSet rs  = statement.executeQuery(sql);
				      System.out.println("Selecting the Medium Priority Tickets");
				      
				      JFrame frame = new JFrame();
					  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				      JScrollPane sp = null;
				      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
				      jt.setBounds(200,200,200,300);
			          sp = new JScrollPane(jt);
			          frame.getContentPane().add(sp);
			          frame.setVisible(true); //repaint frame on screen
				      
				    
				      //Headings
				      /*
				      System.out.println("ID\t\tTicket Descrition\tTicket Priority\t\tTicket Time\t\tTicket Closed");
						System.out.println("--------\t-----------------\t--------------\t\t--------------\t\t---------------");
				      while (rs.next())
						{
							//Getting the values
							String ticket_id = rs.getString("ticket_id");
							String ticket_description = rs.getString("ticket_description");
							String ticket_priority = rs.getString("ticket_priority");
							Timestamp ticket_time = rs.getTimestamp("ticket_time");
							Timestamp ticket_closed = rs.getTimestamp("ticket_closed");
							
							//Display the values
							System.out.println(ticket_id + "\t\t" +  ticket_description + "\t\t" + ticket_priority + "\t\t" + ticket_time  + "\t\t"
									+ ticket_closed);
							
						}rs.close();*/ //closes the loop
						
				    //close connection/statement object  
				     statement.close();
				     connect.close();
				    } catch (Exception e2) {
				    	System.out.println(e2.getMessage());  
				    }  //ends catch statement
			}//ends action performed
		});//ends action listener
		
		JButton btnSelectLowPriority = new JButton("Select Low Priority");
		btnSelectLowPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect = null;
				  Statement statement = null;
						  
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				      connect = DriverManager
				          .getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				      //select statement
				    
				      statement = connect.createStatement();
				      
				      String sql = "SELECT ticket_id, ticket_description, ticket_priority, ticket_time, ticket_closed "
				      		+ "FROM tickets.mgillticket WHERE ticket_priority = 'Low Priority'";
						
						ResultSet rs  = statement.executeQuery(sql);
				      System.out.println("Selecting the Low Priority Tickets");
				      
				      JFrame frame = new JFrame();
					  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				      JScrollPane sp = null;
				      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
				      jt.setBounds(200,200,200,300);
			          sp = new JScrollPane(jt);
			          frame.getContentPane().add(sp);
			          frame.setVisible(true); //repaint frame on screen
				      
				      
				    //Headings
				      /*
						System.out.println("ID\t\tTicket Descrition\tTicket Priority\t\tTicket Time\t\tTicket Closed");
						System.out.println("--------\t-----------------\t--------------\t\t--------------\t\t---------------");
				      while (rs.next())
						{
							//Getting the values
							String ticket_id = rs.getString("ticket_id");
							String ticket_description = rs.getString("ticket_description");
							String ticket_priority = rs.getString("ticket_priority");
							Timestamp ticket_time = rs.getTimestamp("ticket_time");
							Timestamp ticket_closed = rs.getTimestamp("ticket_closed");
							
							//Display the values
							System.out.println(ticket_id + "\t\t" +  ticket_description + "\t\t\t" + ticket_priority + "\t\t" + ticket_time + 
									"\t\t" + ticket_closed);
							
						}rs.close(); */ //closes the loop
				      
				    //close connection/statement object  
				     statement.close();
				     connect.close();
				    } catch (Exception e2) {
				    	System.out.println(e2.getMessage());  
				    }  //ends catch statement
			}//ends action performed
		});//ends action listener
		
		JButton btnCloseTicket = new JButton("Select Closed Ticket");
		btnCloseTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection connect = null;
				Statement statement = null;
						  
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				      connect = DriverManager
				          .getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				      //select statement
				    
				      statement = connect.createStatement();
				      
				      String sql = "SELECT ticket_id, ticket_description, ticket_priority, ticket_time, ticket_closed"
				      		+ " FROM tickets.mgillticket WHERE ticket_closed != 'null'";
						
						ResultSet rs  = statement.executeQuery(sql);
				      System.out.println("Selecting the Closed Tickets");
				      
				      JFrame frame = new JFrame();
					  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				      JScrollPane sp = null;
				      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
				      jt.setBounds(200,200,200,300);
			          sp = new JScrollPane(jt);
			          frame.getContentPane().add(sp);
			          frame.setVisible(true); //repaint frame on screen
				      
				      
				    //Headings
				      /*
						System.out.println("ID\t\tTicket Descrition\tTicket Priority\t\tTicket Time\t\tTicket Closed");
						System.out.println("--------\t-----------------\t-------------\t\t-------------------\t-------------------");
				      while (rs.next())
						{
							//Getting the values
							String ticket_id = rs.getString("ticket_id");
							String ticket_description = rs.getString("ticket_description");
							String ticket_priority = rs.getString("ticket_priority");
							Timestamp ticket_time = rs.getTimestamp("ticket_time");
							Timestamp ticket_closed = rs.getTimestamp("ticket_closed");
							
							//Display the values
							System.out.println(ticket_id + "\t\t" +  ticket_description + "\t\t" + ticket_priority + 
									"\t\t" + ticket_time + "\t\t" + ticket_closed);
							
						}rs.close(); */ //closes the loop
				      
				      
				    //close connection/statement object  
				     statement.close();
				     connect.close();
				    } catch (Exception e2) {
				    	System.out.println(e2.getMessage());  
				    }  //ends catch statement
			}//ends action performed
		});//ends action listener
		
		//delete, update, and close tickets (selecting closed tickets)
		
		JButton btnDeleteTicket = new JButton("Delete Ticket");
		btnDeleteTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				frame.dispose();
				deleteTicket.main();
			}
		});
		
		JButton btnUpdateTicket = new JButton("Update Ticket");
		btnUpdateTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				updateTicket.main();
			}
		});
		
		JButton btnCloseTicket_1 = new JButton("Close Ticket");
		btnCloseTicket_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				closingGUI.main();
			}
		});
		
		
		
		
			
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(143)
							.addComponent(lblAdminLoginPage))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSelectEmergency)
								.addComponent(btnSelectMediumPriority)
								.addComponent(btnNewButton)
								.addComponent(btnSelectLowPriority))
							.addGap(77)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCloseTicket_1)
								.addComponent(btnUpdateTicket)
								.addComponent(btnDeleteTicket)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCloseTicket)))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdminLoginPage)
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectEmergency)
						.addComponent(btnDeleteTicket))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSelectMediumPriority)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSelectLowPriority))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(btnUpdateTicket)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCloseTicket_1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCloseTicket)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		/*
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);
	    */
	}

	
}
