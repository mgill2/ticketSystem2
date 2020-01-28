import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;


public class closingGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					closingGUI window = new closingGUI();
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
	public closingGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				adminGUI.main();
			}
		});
		
		JButton btnShowClosedTickets = new JButton("Show Closed Tickets");
		btnShowClosedTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Connection connect = null;
				Statement statement = null;
				
				//timestamp
			    String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				       connect = DriverManager.getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
				       
				       statement = connect.createStatement();
				        String sql = "SELECT * FROM tickets.mgillticket WHERE ticket_closed <= current_timestamp";
						
				        
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
					} catch (Exception e2) {
						    	System.out.println(e2.getMessage());  
				      }  //ends catch statement
			}//ends action performed
		});//ends action listener
		
		JButton btnCloseATicket = new JButton("Close a Ticket");
		btnCloseATicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							 				
				String close = null;
				int alterTicket = 0;
				Connection connect = null;
				Statement statement = null;
				
				try 
				{
					close = JOptionPane.showInputDialog(null, "Please enter the ticket id you want to close!");
					alterTicket = Integer.parseInt(close);
				}catch(Exception dt){System.out.println("Not a valid ticket id");}
				
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      // Setup the connection with the DB
				       connect = DriverManager.getConnection("jdbc:mysql://www.test.net/tickets?"
				              + "user=fp411&password=411");
			 	    
				       //Select Statement
				    
				        statement = connect.createStatement();
				        String sql = "UPDATE mgillticket SET ticket_closed = current_timestamp WHERE ticket_id =" + alterTicket;
						
						statement.executeUpdate(sql);
						
						JOptionPane.showMessageDialog(null, "Ticket number : "+alterTicket+" was closed!");
						 			          
				        statement.close();
						connect.close();
					} catch (Exception e2) {
						    	System.out.println(e2.getMessage());  
						    }  //ends catch statement
			}
		});
		
		JLabel lblClosingTickets = new JLabel("Closing Tickets");
		lblClosingTickets.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblClosingTickets.setForeground(Color.ORANGE);
		
		JLabel lblToViewOpen = new JLabel("To view open tickets");
		
		JLabel lblToCloseA = new JLabel("To close a ticket");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack)
								.addComponent(lblToViewOpen))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(40)
									.addComponent(lblClosingTickets))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(118)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnCloseATicket)
										.addComponent(lblToCloseA)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnShowClosedTickets)))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(lblClosingTickets))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblToViewOpen)
						.addComponent(lblToCloseA))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShowClosedTickets)
						.addComponent(btnCloseATicket))
					.addContainerGap(130, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
