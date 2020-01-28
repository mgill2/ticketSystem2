import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//import javax.mail.*;			Download Java Mail API  &   Java Activation Framework
//import javax.mail.internet.*;
//import javax.activation.*;

/*proj implements one listener and can trigger action off menu item clicked
 * with one action performed event handler!
 */
@SuppressWarnings("serial")
public class ticketsGUI extends JFrame implements ActionListener {

	//needed class level member vars
	private JFrame mainFrame;
	
	//incl main menu objects
	private	JMenu file = new JMenu("File");
	private	JMenu tickets = new JMenu("Tickets");
	JScrollPane sp = null;
	//incl sub menu item objects
	
	JMenuItem ItemNew;
    JMenuItem ItemExit;
    JMenuItem ItemOpenTicket;
    JMenuItem ItemViewTicket;
    //include more items below
	
	public ticketsGUI() {
		createTable();
		createMenu();
	    prepareGUI();
	}
	
	private void createTable()
	{	
		// vars. for SQL Query create
		  final String createTable = "CREATE TABLE mgillTicket(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_name VARCHAR(30), ticket_description VARCHAR(200))";
		  Connection connect = null;
		  Statement statement = null;
				  
		try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://www.test.net/tickets?"
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
	
private void createMenu()
{
	//set up mnemonics for main menu items (triggered by alt keys)
	file.setMnemonic('F');
	
	//initialize up sub menu items
	ItemNew = new JMenuItem("New");
	ItemNew.setMnemonic('N');
	file.add(ItemNew);
	
	ItemExit = new JMenuItem("Exit");
	ItemExit.setMnemonic('x');
	file.add(ItemExit);
	
	ItemOpenTicket = new JMenuItem("Open Ticket");
	ItemOpenTicket.setMnemonic('x');
	tickets.add(ItemOpenTicket);
	
	ItemViewTicket = new JMenuItem("View Ticket");
	ItemViewTicket.setMnemonic('x');
	tickets.add(ItemViewTicket);
	
	//incl more sub menu items below
	
	//add listeners for each desired menu item 
	ItemExit.addActionListener(this);
	ItemOpenTicket.addActionListener(this);
	ItemViewTicket.addActionListener(this);
	
	//add more listeners for any additional sub menu items
}
 
private void prepareGUI()
{ 
	//initialize frame object
	mainFrame = new JFrame("Main");
    //create jmenu bar
	JMenuBar bar = new JMenuBar();
	bar.add(file);  //set menu orders
	bar.add(tickets);
	 //add menu bar component to frame
    mainFrame.setJMenuBar(bar); 
   
    mainFrame.setSize(400,400);
	mainFrame.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	//add desired coding to trigger each sub menu action as examples shown below
	 if(e.getSource() == ItemExit){
         System.exit(0);
     }
	 else if(e.getSource() == ItemOpenTicket){

		 try {
			 
			//add a ticket information
			 String ticketName=
				 JOptionPane.showInputDialog(null,"Enter your name");
			 String ticketDesc=
					 JOptionPane.showInputDialog(null,"Enter a ticket description");
			 
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.test.net/tickets?"
	                                                               + "user=fp411&password=411");
	            
	            Statement statement = dbConn.createStatement();
	            
	            int result = statement.executeUpdate("Insert into mgillTicket(ticket_name, ticket_description) values('"+ticketName+"','"+ticketDesc+"')");
	            
	            if (result != 0) {
					System.out.println("Ticket Created Successfully!!!");
				} else {
					System.out.println("Ticket cannot be Created!!!");
				}
		 
		        JOptionPane.showMessageDialog(null,"Ticket id: created"); //******fill in with id value newly created in table!******//
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
	 else if(e.getSource() == ItemViewTicket){
			//retrieve ticket information
			 //ticketsJDBC dobj = new ticketsJDBC();
			 try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.test.net/tickets?"
	                                                               + "user=fp411&password=411");
	            
	            Statement statement = dbConn.createStatement();
	            
	            ResultSet results = statement.executeQuery("SELECT * FROM mgillTicket");
	            
	            // Use JTable built in functionality to build a table model and display the table model
	            //off a resultset!!!
	            JTable jt = new JTable(ticketsJTable.buildTableModel(results));
	           
	            jt.setBounds(30,40,200,300);
	            sp=new JScrollPane(jt);
	            mainFrame.add(sp);
	            mainFrame.setVisible(true); //repaint frame on screen
	            statement.close();
		        dbConn.close();   //close connections!!!
	            
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
	     } 	
}

public static void main(String args[])
{
	new ticketsGUI();
}

}
