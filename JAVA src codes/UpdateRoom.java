
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateRoom extends JFrame implements ActionListener
{
    Choice c1;
    JTextField t1, t2, t3;
    JButton b1, b2, b3;
    
    public UpdateRoom()
    {
        setBounds(500,200,980,450);
        setLayout(null);
        
        JLabel l1 = new JLabel("Update Room Status");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        l1.setForeground(Color.BLUE);
        l1.setBounds(30,20,260,30);
        add(l1);
        
        JLabel l2 = new JLabel("Guest ID");
        l2.setBounds(30,80,120,20);
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
        c1.setBounds(200,80,150,25);
        c1.setBackground(Color.WHITE);
        add(c1);
        
        JLabel l3 = new JLabel("Room Number");
        l3.setBounds(30,130,120,20);
        add(l3);
        
        t1 = new JTextField();
        t1.setBounds(200,130,150,25);
        add(t1);
        
        JLabel l4 = new JLabel("Availability");
        l4.setBounds(30,180,120,20);
        add(l4);
        
        t2 = new JTextField();
        t2.setBounds(200,180,150,25);
        add(t2);
        
        JLabel l5 = new JLabel("Cleaing Status");
        l5.setBounds(30,230,120,20);
        add(l5);
        
        t3 = new JTextField();
        t3.setBounds(200,230,150,25);
        add(t3);
        
        b1 = new JButton("Check");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(130,300,120,30);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Update");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(40,350,120,30);
        b2.addActionListener(this);
        add(b2);
        
        b3 = new JButton("Back");
        b3.setForeground(Color.WHITE);
        b3.setBackground(Color.BLACK);
        b3.setBounds(220,350,120,30);
        b3.addActionListener(this);
        add(b3);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("seventh.jpg"));
        Image im = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(im);
        JLabel il1 = new JLabel(i2);
        il1.setBounds(400,10,500,400);
        add(il1);
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            // Example :  first we will find the allocated room (customer table) of the customer whose passport number is 7864 and setText in t1.... and then with the help of allocated room (room_number in room table) we will find corresponding availability and cleaning status and then apply setText in t2 and t3 respectively
            String room = null;
            Conn c = new Conn();
            try
            {
                String s1 = c1.getSelectedItem();
                ResultSet rs = c.s.executeQuery("SELECT * from customer WHERE number = '"+s1+"'");
                
                while(rs.next())
                {
                    t1.setText(rs.getString("allocated room")); //getText() will take the value which is entered in the textfield and then do operation in databsse where as setField() will enter the value in the textField from the database
                    room = rs.getString("allocated room");
                }
                ResultSet rs2 = c.s.executeQuery("SELECT * from room WHERE room_number = '"+room+"'");
                while(rs.next())
                {
                    t2.setText(rs2.getString("available"));
                    t3.setText(rs2.getString("status"));
                }
                rs.close();
                rs2.close();
                c.s.close();
            }
            catch(Exception e)
            {
            }
        }
        else if(ae.getSource()==b2)
        {
            Conn c = new Conn();
            try
            {
                String room_no = t1.getText();
                String available = t2.getText();
                String status = t3.getText();
                
                String s2 = "UPDATE room SET available = '"+available+"', status = '"+status+"' WHERE room_number = '"+room_no+"'";
                c.s.executeUpdate(s2);
                
                JOptionPane.showMessageDialog(null, "Room Details Updated");
                c.s.close();
                new Reception().setVisible(true);
                this.setVisible(false);
            }
            catch(Exception e)
            {
            }
        }
        else if(ae.getSource()==b3)
        {
            new Reception().setVisible(true);
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args)
    {
        new UpdateRoom().setVisible(true);
    }
}
