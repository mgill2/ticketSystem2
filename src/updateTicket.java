import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;


public class updateTicket {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateTicket window = new updateTicket();
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
	public updateTicket() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUpdateTicket = new JLabel("Update Ticket");
		lblUpdateTicket.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUpdateTicket.setForeground(new Color(128, 0, 128));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				adminGUI.main();
			}
		});
		
		JLabel lblUpdateATicket = new JLabel("Update a Ticket");
		
		JButton btnUpdateTicket = new JButton("Update Ticket");
		btnUpdateTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String priority = null;
				String upDesc = null;
				String ticketNum = null;
				Connection connect = null;
				Statement statement = null; 
		       
				   //input of update
			       try 
					{
						ticketNum = JOptionPane.showInputDialog(null, "Please enter the ticket id you want to update!");
						int ticketID= Integer.parseInt(ticketNum);
						priority = JOptionPane.showInputDialog(null, "Please enter the new priority you would like /n (Emergency, High, Medium, Low");
						
						//upDesc = JOptionPane.showInputDialog(null, "Please enter the new description for ticket id" + ticketNum);
						
					}catch(Exception dt){System.out.println("Not a valid ticket id");}
			       
				
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				       connect = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				       //Select Statement
				    
				        statement = connect.createStatement();
				        String sql = "UPDATE mgillticket.ticket_priority SET ticket_priority= "+ priority + "  WHERE ticket_id =" + ticketNum;
				       // String sql2 = "UPDATE tickets.mgillticket SET ticket_description= "+ upDesc + "  WHERE ticket_id =" + ticketNum;
				        
						statement.executeUpdate(sql);
						//statement.executeUpdate(sql2);
						
				        statement.close();
						connect.close();
					} catch (Exception e2) {
						    	System.out.println(e2.getMessage());  
				}  //ends catch statement		
			 }
		});
		
		JLabel lblViewTickets = new JLabel("View Tickets by Group");
		
		JButton btnViewTickets = new JButton("View Tickets");
		btnViewTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ticketNum = null;
				Connection connect = null;
				Statement statement = null; 
				
				//input of update
			       try 
					{
						ticketNum = JOptionPane.showInputDialog(null, "Please enter the ticket id you want to view!");
						int ticketID= Integer.parseInt(ticketNum);
						
					}catch(Exception dt){System.out.println("Not a valid ticket id");}
			       
				
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				       connect = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				       //Select Statement
				    
				         statement = connect.createStatement();
				        String sql = "SELECT * FROM tickets.mgillticket WHERE ticket_id ="+ticketNum+" ORDER BY ticket_priority";
				        
				        ResultSet rs = statement.executeQuery(sql);
				      //JTable for information
					     
						  JFrame frame = new JFrame();
						  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					      JScrollPane sp = null;
					      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
					      jt.setBounds(200,200,200,300);
				          sp = new JScrollPane(jt);
				          frame.getContentPane().add(sp);
				          frame.setVisible(true); //repaint frame on screen
						
				                   
				        statement.close();
						connect.close();
					}catch (Exception e2) {System.out.println(e2.getMessage());}  
			}
		});
			
		JLabel lblSelectAllTickets = new JLabel("Select all Tickets");
		
		JButton btnSelectAllTickets = new JButton("Select all Tickets");
		btnSelectAllTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				        String sql = "SELECT * FROM tickets.mgillticket ORDER BY ticket_id";
				        
				        ResultSet rs = statement.executeQuery(sql);
				      //JTable for information
					     
						  JFrame frame = new JFrame();
						  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					      JScrollPane sp = null;
					      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
					      jt.setBounds(200,200,200,300);
				          sp = new JScrollPane(jt);
				          frame.getContentPane().add(sp);
				          frame.setVisible(true); //repaint frame on screen

				        statement.close();
						connect.close();
						
					}catch (Exception e2) {System.out.println(e2.getMessage());}  
			}//ends the action performed
		});//ends the action listener
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnBack)
					.addGap(91)
					.addComponent(lblUpdateTicket)
					.addContainerGap(154, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSelectAllTickets)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnSelectAllTickets)
								.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblUpdateATicket)
									.addComponent(btnUpdateTicket))
								.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblViewTickets)
									.addComponent(btnViewTickets))
								.addContainerGap(230, Short.MAX_VALUE)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBack))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(lblUpdateTicket)))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpdateATicket)
						.addComponent(lblViewTickets))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdateTicket)
						.addComponent(btnViewTickets))
					.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
					.addComponent(lblSelectAllTickets)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSelectAllTickets)
					.addGap(28))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
