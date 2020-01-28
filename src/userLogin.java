import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

import javax.swing.JTextField;
import javax.swing.JButton;


public class userLogin {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userLogin window = new userLogin();
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
	public userLogin() {
		initialize();
		createTable();
		
	}
	
	private void createTable()
	{	
		// vars. for SQL Query create
		  final String createTable = "CREATE TABLE mgillTicket(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_name VARCHAR(30), "
		  		+ "ticket_description VARCHAR(200), ticket_location VARCHAR(40), ticket_service_tag VARCHAR(12), "
		  		+ "ticket_priority VARCHAR(25), ticket_time TIMESTAMP, ticket_closed TIMESTAMP default)";
		  Connection connect = null;
		  Statement statement = null;
				  
		try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
		              + "user=fp411&password=411");
	 	      
		      //create table
		    
		      statement = connect.createStatement();
		      
		      statement.executeUpdate(createTable);
		      System.out.println("Created table in given database...");

			//end create table
		    //close connection/statement object  
		     statement.close();
		     connect.close();
		    } catch (Exception e) {
		    	System.out.println(e.getMessage());  
		    } 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(150, 100, 500, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int ticket_id = 0;
		
		
		String[] priority = { "Select Priority", "Emergency", "High Priority", "Medium Priority", "Low Priority" };
		JComboBox priorityComboBox = new JComboBox(priority);
		priorityComboBox.setSelectedIndex(0);
		
		priorityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String priority = (String)cb.getSelectedItem();
				
			}
		});
		
			
		
		JTextPane ticket_name = new JTextPane();
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		
		JLabel lblPriority = new JLabel("Priority");
		
		JTextArea textArea = new JTextArea();
		
		JLabel lblDecription = new JLabel("Description");
		
		JToggleButton tglbtnHuman = new JToggleButton("Human?");
		tglbtnHuman.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tglbtnHuman.isSelected();
				if(true)
				{
					//do nothing
				}
				else
				{
					System.out.println("Please press the toggle button to continue!");
				}
			}
		});
		
		JLabel lblToggleOnTo = new JLabel("Toggle on to continue");
		
		String[] dept = {"Select Your Department", "Payroll", "Human Resources", "Teachers", "Business", "Marketing",
				"IT"
		};
		JComboBox deptComboBox = new JComboBox(dept);
		deptComboBox.setSelectedIndex(0);
		
		deptComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String dept = (String)cb.getSelectedItem();
				
			}
		});
		
		JLabel lblDepartment = new JLabel("Department");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblServiceTag = new JLabel("Service Tag");
		
		JButton btnSubmitTicket = new JButton("Submit Ticket");
		btnSubmitTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			
				try
				{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				
					Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
	                                                               + "user=fp411&password=411");
	            
					Statement statement = dbConn.createStatement();
	            
	            int result = statement.executeUpdate("Insert into tickets.mgillTicket(ticket_id, ticket_name, ticket_description, ticket_location, ticket_service_tag,"
	            		 		+ "ticket_priority, ticket_time) " + "values('"+ticket_id+"','"+ticket_name.getText()+"','"+textArea.getText()+"','"+deptComboBox.getSelectedItem()+"',"
	            		 + "'"+textField.getText()+"','"+priorityComboBox.getSelectedItem()+"','"+timestamp+"')");
	            
	            if (result != 0) {
					System.out.println("Ticket Created Successfully!!!");
				} else {
					System.out.println("Ticket cannot be Created!!!");
				}
		 
		        JOptionPane.showMessageDialog(null,"Ticket id: " + ticket_id); //******fill in with id value newly created in table!******//
		    } 
		       catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
		       } catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
				
			}
		});
		
		JLabel lblUserTicketSystem = new JLabel("User Ticket System");
		lblUserTicketSystem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserTicketSystem.setForeground(Color.BLUE);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(priorityComboBox, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblPriority)
											.addGap(99)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDepartment)
										.addComponent(deptComboBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblServiceTag)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(tglbtnHuman)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))))
								.addComponent(lblDecription)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblEmailAddress, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
													.addComponent(lblToggleOnTo))
												.addGroup(groupLayout.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(ticket_name, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))))
									.addGap(35))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(121)
							.addComponent(lblUserTicketSystem, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(179)
							.addComponent(btnSubmitTicket)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUserTicketSystem)
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(lblToggleOnTo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tglbtnHuman))
								.addComponent(ticket_name, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblEmailAddress, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblServiceTag)
						.addComponent(lblPriority)
						.addComponent(lblDepartment))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(priorityComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(deptComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblDecription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSubmitTicket)
					.addGap(30))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
