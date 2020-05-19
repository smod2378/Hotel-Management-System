 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
    JLabel l1, l2;
    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;
    
    public Login()
    {
        setBounds(600,300,600,300);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); 
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350,10,200,200);
        add(l3);
        
        l1 = new JLabel("Username");
        l1.setBounds(40,20,100,30);
        add(l1);
        
        l2 = new JLabel("Password");
        l2.setBounds(40,70,100,30);
        add(l2);
        
        t1 = new JTextField();
        t1.setBounds(150,20,150,30);
        add(t1);
        
        t2 = new JPasswordField();
        t2.setBounds(150,70,150,30);
        add(t2);
        
        b1 = new JButton("Login");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(40,150,120,30); // x,y,width,length
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Cancel");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(180,150,120,30);
        b2.addActionListener(this);
        add(b2);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            String username = t1.getText();
            String password = t2.getText();
            Conn c = new Conn();
            
            String str = "SELECT * FROM login WHERE username = '"+username+"' AND password = '"+password+"'";
            try
            {
                ResultSet rs = c.s.executeQuery(str);
                if(rs.next())
                {
                    c.s.close(); // very important step without this sqlite will get locked because connection has to be closed
                    new Dashboard().setVisible(true);
                    //System.out.println("Login successful");
                    this.setVisible(false);
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Try Again : Invalid Username or Password !");
                    
                }
            }
            catch(Exception e)
            {
                
            }
        }
        else if(ae.getSource()==b2)
        {
            System.exit(0); // project terminates if we click on cancel,i.e., b2
        }
    }
    
    public static void main(String[] args)
    {
        new Login();
    }
}
