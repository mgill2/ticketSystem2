import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
    
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    
    public Login(){
        prepareGUI();
    }
    
    public static void main(String[] args){
        Login login = new Login();
        login.showTextField();
        
        
    }
    
    private void prepareGUI(){
        mainFrame = new JFrame("Login");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent wE){
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        
        statusLabel.setSize(350,100);
        
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    
    private void showTextField(){
        headerLabel.setText("Account Access");
        
        JLabel  namelabel= new JLabel("User ID: ", JLabel.RIGHT);
        JLabel  passwordLabel = new JLabel("Password: ", JLabel.CENTER);
        final JTextField userText = new JTextField(6);
        final JPasswordField passwordText = new JPasswordField(6);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
        	
            @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
                
                /*check for correct password/username
                 *include a desired 'hardcoded' username /password to verify against
                 *user input values for both username & password fields
                 *give popup message if either username or password is incorrect
                 */
            	if (userText.getText().equals("test") && 
            			 passwordText.getText().equals("test"))
            	{
            		
            	    //close of Login window
            		mainFrame.dispose();
            		//open up Records file upon successful login
            		try
                    {
                    	new ticketsGUI();
                    }catch(Exception e1){e1.printStackTrace();}
            		
            	}
            	
            	else {
            		String message = "Enter correct username\n and/or password";
            		JOptionPane.showMessageDialog(null, message);
            	}
               }
           
            
        });
        
        controlPanel.add(namelabel);
        controlPanel.add(userText);
        controlPanel.add(passwordLabel);       
        controlPanel.add(passwordText);
        controlPanel.add(loginButton);
        mainFrame.setVisible(true);  
    }
}