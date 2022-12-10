import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.lang.*;
import java.util.*;
 


@SuppressWarnings("unused")
public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
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
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(8, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(443, 13, 253, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(409, 170, 353, 48);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(409, 286, 353, 52);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 149, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(424, 390, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
               // String password = passwordField.getText();
               String password=String.valueOf(passwordField.getPassword()); 
                
                    
               try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swingd",
                        "root", "qwertyuiop@1");

                    PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select name, password from student where name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        UserHome ah = new UserHome(userName);
                        ah.setTitle("Welcome");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                        UserLogin f = new UserLogin(1);
                        f.setVisible(true);
                                               
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (Exception sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 11, 1008, 551);
        contentPane.add(label);
    }
    
    public UserLogin(int x)
    {
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setBounds(0, 0, 400, 400);
         setResizable(false);
         JPanel NewcontentPane = new JPanel();
         NewcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(NewcontentPane);
         NewcontentPane.setLayout(null);
         
         JButton NewButton = new JButton("Encrypt");
         NewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
         NewButton.setBounds(100, 100, 100, 50);
         
         JButton NewButtonD = new JButton("Decrypt");
         NewButtonD.setFont(new Font("Tahoma", Font.PLAIN, 14));
         NewButtonD.setBounds(200, 100, 100, 50);
         
         NewButton.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent e) {
            	 JFileChooser fileChooser=new JFileChooser();
                 fileChooser.showOpenDialog(null);
                 File file=fileChooser.getSelectedFile();
                 
                 try
                 {
                     BufferedImage img = ImageIO.read(file);
                     int width = img.getWidth();
                     int height = img.getHeight();
                     int totalR = 0, totalG = 0, totalB = 0;
                     for(int y = 0; y < height; y++)
                     {
                         for(int x = 0; x < width; x++)
                         {
                             int p = img.getRGB(x, y);
                             int r = (p >> 16) & 0xff;
                             int g = (p >> 8) & 0xff;
                             int b = p & 0xff;

                             totalR += r;
                             totalG += g;
                             totalB += b;

                         }
                     }
                     
                     //chaotic map implementation
                         
                         Random rand = new Random();
                         Random rand2 = new Random();
                         
						int r1 = (int) (3.57 + (4 - 3.57) * rand.nextFloat());    
						int r2 = (int) (3.57 + (4 - 3.57) * rand2.nextFloat());
						
						
//						r1 = (float)((int)(r1 * 100f))/100f;
//						r2 = (float)((int)(r2 *100f))/100f;
                         int x0 = (int)(totalR + totalG + totalB); 
                         int y0 = x0;
                         
                         
						ArrayList<Pair> X = new ArrayList<>();
                         
						ArrayList<Pair> Y = new ArrayList<>();
                         X.add(new Pair(x0, 0));
                         for(int i = 1; i < width; i++)
                         {
                        	 int number = x0 * (1 - x0) * r1;
                        	 x0 = number;
                        	 X.add(new Pair(x0, i));
                         }
                         Y.add(new Pair(y0, 0));
                         for(int i = 1; i < height; i++)
                         {
                        	 int number = y0 * (1 - y0) * r2; 
                        	 y0 = number;
                        	 Y.add(new Pair(y0, i));
                         }
                         int key1 = (int)(totalR + totalG + totalB);
                         float key2 = r1, key3 = r2;
                         
                         Collections.sort(X, new PairComparator());
                         Collections.sort(Y, new PairComparator());
                         
                         for(int y = 0; y < height; y++)
                         {
                             for(int x = 0; x < width; x++)
                             {
                                 int p = img.getRGB(x, y);
                                 Pair r = X.get(x);
                                 Pair q = Y.get(y);
                                 int newX = r.index;
                                 int newY = q.index;
                                 
                                 img.setRGB(newX, newY, p);
                                 
                             }
                         }
                        
                         try{
                             file = new File("C:/Users/Anant Kumar/Desktop/ImageEncryption/Encryption.png");
                             ImageIO.write(img, "png", file);
                         }
                         catch(IOException error)
                         {
                             System.out.println("Error : " + error);                                      
                         }
                      
                      JOptionPane.showMessageDialog(null, "Done\nFile saved in EncryptedImgs on Desktop..");
                      JOptionPane.showMessageDialog(null, "Your Decryption key is: " + key1 + " " + key2 + " " + key3 + "\n");

                 }catch(Exception errorr)
                 {
                     errorr.printStackTrace();
                 }
             }
         });
         
         NewButtonD.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        	 
        	 JFrame g=new JFrame();
 	        g.setTitle("DECRYPTION");
 	        g.setSize(500,350);
 	        g.setLocationRelativeTo(null);
 	        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	        
 	       JLabel lblUsername = new JLabel("Key 1");
 	        lblUsername.setBackground(Color.BLACK);
 	        lblUsername.setForeground(Color.BLACK);
 	        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
 	        lblUsername.setBounds(395, 170, 149, 52);
 	       
 	        JLabel lblUsernamek2 = new JLabel("Key 2");
	        lblUsernamek2.setBackground(Color.BLACK);
	        lblUsernamek2.setForeground(Color.BLACK);
	        lblUsernamek2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblUsernamek2.setBounds(395, 286, 149, 52);
 	        
	        JLabel lblUsernamek3 = new JLabel("Key 3");
 	        lblUsernamek3.setBackground(Color.BLACK);
 	        lblUsernamek3.setForeground(Color.BLACK);
 	        lblUsernamek3.setFont(new Font("Tahoma", Font.PLAIN, 14));
 	        lblUsernamek3.setBounds(395, 402, 149, 52);
 	        
 	        //key1
 	        JTextField key1 = new JTextField();
 	        key1.setBounds(409, 170, 353, 48);
 	        JPanel contentPane = new JPanel();
 			contentPane.add(key1);
 	        key1.setColumns(10);
 	        
 	        
 	        //key2
 	        JTextField key2 = new JTextField();
 	        key2.setBounds(409, 286, 353, 52);
 			contentPane.add(key2);
 	        key2.setColumns(10);
 	        
 	        
 	        //key3
 	        JTextField key3 = new JTextField();
 	        key3.setBounds(481, 402, 281, 68);
 			contentPane.add(key3);
 	        key3.setColumns(10);
 	        
 	      
 	        g.getContentPane().setLayout(new FlowLayout());
 	        
 	        JButton btnNewButton = new JButton("decrypt");
 	        btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
 	        g.getContentPane().add(lblUsername);
 	        g.getContentPane().add(key1);
 	        g.getContentPane().add(lblUsernamek2);
 	        g.getContentPane().add(key2);
 	        g.getContentPane().add(lblUsernamek3);
 	        g.getContentPane().add(key3);
 	        g.getContentPane().add(btnNewButton);
 	        
 	       // f.add(textField);
 	        g.setVisible(true);
 	        
 	       btnNewButton.addActionListener(new ActionListener() {
 	        	 public void actionPerformed(ActionEvent e) {
 	        		 
 	        		JFileChooser fileChooser=new JFileChooser();
 	                 fileChooser.showOpenDialog(null);
 	                 File file=fileChooser.getSelectedFile();
 	                 
 	                try
 	                 {
 	                     BufferedImage img = ImageIO.read(file);
 	                     int width = img.getWidth();
 	                     int height = img.getHeight();
 	                     int x0 = Integer.parseInt(key1.getText());
 		        		 int r1 = Integer.parseInt(key2.getText());
 		        		 int r2 = Integer.parseInt(key3.getText());
 		         		 int y0 = x0;
// 		         		 r1 = (float)((int)(r1 * 100f))/100f;
// 		         		 r2 = (float)((int)(r2 *100f))/100f;
 		         		 
 		       
 		         		 ArrayList<Pair> X = new ArrayList<>();
 		         		 ArrayList<Pair> Y = new ArrayList<>();
 	                      X.add(new Pair(x0, 0));
 	          
 	                      for(int i = 1; i < width; i++)
 	                      {
 	                     	 int number = x0 * (1 - x0) * r1;
 	                     	 x0 = number;
 	                     	 X.add(new Pair(x0, i));
 	                      }
 	                      
 	                      
 	                      Y.add(new Pair(y0, 0));
 	                      for(int i = 1; i < height; i++)
 	                      {															
 	                     	 int number = y0 * (1 - y0) * r2;
 	                     	 y0 = number;
 	                     	 Y.add(new Pair(y0, i));
 	                      }
 	                      
 	                      Collections.sort(X, new PairComparator());
 	                      Collections.sort(Y, new PairComparator());
 		        		 
 	                      for(int y = 0; y < height; y++)
 	                      {
 	                          for(int x = 0; x < width; x++)
 	                          {
 	                        	  Pair r = X.get(x);
 	                              Pair q = Y.get(y);
 	                              int p = img.getRGB(r.index, q.index);
 	                              
 	                              img.setRGB(x, y, p);
 	                              
 	                          }
 	                      }
 	                        
 	                         try{
 	                             file = new File("C:/Users/Anant Kumar/Desktop/ImageEncryption/Decryption.png");
 	                             ImageIO.write(img, "png", file);
 	                         }
 	                         catch(IOException error)
 	                         {
 	                             System.out.println("Error : " + error);                                      
 	                         }
 	                      
 	                      JOptionPane.showMessageDialog(null, "Done\nFile saved in EncryptedImgs on Desktop..");
// 	                      JOptionPane.showMessageDialog(null, "Your Decryption key is: " + key1 + " " + key2 + " " + key3 + "\n");

 	                 }catch(Exception errorr)
 	                 {
 	                     errorr.printStackTrace();
 	                 }	 
 	        	 }
 	       });
 	        
 	        
        	 		
        	 } 
         });
         
         NewcontentPane.add(NewButton);
         NewcontentPane.add(NewButtonD);
    }
}

class PairComparator implements Comparator<Pair>
{
    public int compare(Pair p1,Pair p2)
    {
        if(p1.x < p2.x)
        {
            return -1;
        }
        else if(p1.x > p2.x)
        {
            return 1;
        }
        else
        {
            if(p1.index < p2.index)
            {
                return -1;
            }
            else if(p1.index > p2.index)
            {
                return 1;
            }
            else
            
            {
                return 0;
            }
        }
    }
}

class Pair
{
	int x;
	int index;
	
	public Pair(int x, int index)
	{
		this.x  = x;
		this.index = index;
	}
}