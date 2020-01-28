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
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;


public class deleteTicket {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteTicket window = new deleteTicket();
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
	public deleteTicket() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDeleteTicket = new JLabel("Delete Ticket");
		lblDeleteTicket.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeleteTicket.setForeground(Color.GREEN);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				adminGUI.main();
			}
		});
		
		JLabel lblDeleteATicket = new JLabel("Delete a Ticket");
		
		JButton btnDeleteTicket = new JButton("Delete Ticket");
		btnDeleteTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String delete = null;
				Connection connect = null;
				Statement statement = null;
						  
				try 
				{
					delete = JOptionPane.showInputDialog(null, "Please enter the ticket id you want to delete!");
					int deleteTicket = Integer.parseInt(delete);
				}catch(Exception dt){System.out.println("Not a valid ticket id");}
				
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				      connect = DriverManager.getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				      //Select Statement
				    
				      statement = connect.createStatement();
				      
				      String sql = "DELETE FROM tickets.mgillticket WHERE ticket_id =" + delete;
				      statement.executeUpdate(sql);
				      System.out.println("Executing the delete on " + delete);
				      
				      JOptionPane.showMessageDialog(null, "Ticket number deleted was :"+ delete);
				      
				      statement.close();
					  connect.close();
					 } catch (Exception e2) {
					    	System.out.println(e2.getMessage());  
					    }  //ends catch statement
			}//ends action performed
		});//ends action listener
		
		JLabel lblDisplaySelectedTickets = new JLabel("Display Selected Tickets");
		
		JButton btnViewTickets = new JButton("View Tickets");
		btnViewTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    
					  
					  Connection connect = null;
					  Statement statement = null;
					  
					  // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				      connect = DriverManager
				          .getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				      //Select Statement
				    
				      statement = connect.createStatement();
				      
				      String sql = "SELECT * FROM tickets.mgillticket ORDER BY ticket_priority";
				      System.out.println("Fetching the data");
				     
				      ResultSet rs  = statement.executeQuery(sql);
				      
				      JFrame frame = new JFrame();
					  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				      JScrollPane sp = null;
				      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
				      jt.setBounds(200,200,200,300);
			          sp = new JScrollPane(jt);
			          frame.getContentPane().add(sp);
			          frame.setVisible(true); //repaint frame on screen
						
					  //closing connections
					  statement.close();
					  connect.close();
					} catch (Exception e2) {
					    	System.out.println(e2.getMessage());  
					    }  //ends catch statement
			}
		});
		
		
		
		JLabel lblCannotRememberTicket = new JLabel("Cannot Remember Ticket Number?");
		
		JButton btnAllTickets = new JButton("All Tickets");
		btnAllTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    
					  
					  Connection connect = null;
					  Statement statement = null;
					  
					  // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				      connect = DriverManager
				          .getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
			 	      
				      //Select Statement
				    
				      statement = connect.createStatement();
				      
				      String sql = "SELECT * FROM tickets.mgillticket ORDER BY ticket_priority";
				      System.out.println("Fetching the data");
				     
				      ResultSet rs  = statement.executeQuery(sql);
				      
				      JFrame frame = new JFrame();
					  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				      JScrollPane sp = null;
				      JTable jt = new JTable(ticketsJTable.buildTableModel(rs));
				      jt.setBounds(200,200,200,300);
			          sp = new JScrollPane(jt);
			          frame.getContentPane().add(sp);
			          frame.setVisible(true); //repaint frame on screen
						
					  //closing connections
					  statement.close();
					  connect.close();
					} catch (Exception e2) {
					    	System.out.println(e2.getMessage());  
					    }  //ends catch statement
			}
		
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBack)
							.addGap(94)
							.addComponent(lblDeleteTicket)
							.addContainerGap(156, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDeleteATicket)
								.addComponent(btnDeleteTicket))
							.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnViewTickets)
								.addComponent(lblDisplaySelectedTickets))
							.addContainerGap(139, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCannotRememberTicket)
							.addContainerGap(378, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAllTickets)
							.addContainerGap(335, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBack)
						.addComponent(lblDeleteTicket))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDisplaySelectedTickets)
						.addComponent(lblDeleteATicket))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnViewTickets)
						.addComponent(btnDeleteTicket))
					.addGap(58)
					.addComponent(lblCannotRememberTicket)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAllTickets)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
