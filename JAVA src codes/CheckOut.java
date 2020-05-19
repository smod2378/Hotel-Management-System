

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CheckOut extends JFrame implements ActionListener
{
    JTextField t1;
    Choice c1;
    JButton b1, b2, b3;
    
    public CheckOut()
    {
        setBounds(600,210,800,300);
        setLayout(null);
        
        JLabel l1 = new JLabel("Check Out");
        l1.setFont(new Font("Tahoma", Font.PLAIN,20));
        l1.setForeground(Color.BLUE);
        l1.setBounds(100,20,110,30);
        add(l1);
        
        JLabel l2 = new JLabel("Customer ID");
        l2.setBounds(30,80,100,30);
        add(l2);
        
        c1 = new Choice();
        try
        {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while(rs.next())
            {
                c1.add(rs.getString("number"));
            }
            rs.close();
            c.s.close();
        }
        catch(Exception e)
        {
        }
        c1.setBounds(150,80,150,30);
        c1.setBackground(Color.WHITE);
        add(c1);
        
        JLabel l3 = new JLabel("Room Number");
        l3.setBounds(30,130,100,30);
        add(l3);
        
        t1 = new JTextField();
        t1.setBounds(150,130,150,30);
        add(t1);
        
        b1 = new JButton("Check Out");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(30,200,120,30);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(170,200,120,30);
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("tick.png"));
        Image im = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(im);
        b3 = new JButton(i2);
        b3.setBounds(310,80,20,20);
        b3.addActionListener(this);
        add(b3);
        
        ImageIcon i3 = new ImageIcon(getClass().getResource("sixth.jpg"));
        Image im2 = i3.getImage().getScaledInstance(400,250,Image.SCALE_DEFAULT);
        ImageIcon i4 = new ImageIcon(im2);
        JLabel il3 = new JLabel(i4);
        il3.setBounds(370,5,400,250);
        add(il3);
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            
            try
            {
                Conn c = new Conn();
                String id = c1.getSelectedItem();
                String room = t1.getText();
                // this query is to remove the name of the customer from the customer info once he/she checkouts
                String str1 = "DELETE FROM customer WHERE number = '"+id+"'";
                c.s.executeUpdate(str1);
               
                // this query is to update the corresponding room (the room from where the customer checkouts) status to Available
                String str2 = "UPDATE room SET available = 'Available' WHERE room_number = '"+room+"'";
                
                c.s.executeUpdate(str2); 
                JOptionPane.showMessageDialog(null, "Check Out Done"); // Check Out done means room status is updated and the customer is removed from customer info
                
                c.s.close();
                new Reception().setVisible(true);
                this.setVisible(false);
            }
            catch(Exception e)
            {
            }
        }
        else if(ae.getSource()==b2)
        {
            new Reception().setVisible(true);
            this.setVisible(false);
        }
        else if(ae.getSource()==b3)
        {
            Conn c = new Conn();
            String id = c1.getSelectedItem();
            try
            {
                ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE number = '"+id+"'");
                while(rs.next())
                {
                    t1.setText(rs.getString("allocated room")); // rs.getString("allocated room") will return allocated room corresponding to the number=id mentioned in rs
                }
                rs.close();
                c.s.close();
            }
            catch(Exception e)
            {
            }
        }
    }
    
    public static void main(String args[])
    {
        new CheckOut().setVisible(true);
    }
}
