
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddRoom extends JFrame implements ActionListener
{
    JTextField t1, t2;
    JComboBox c1, c2, c3;
    JButton b1, b2;
    
    public AddRoom()
    {
        setBounds(520,210,1000,500);
        setLayout(null);
        
        JLabel l1 = new JLabel("Add Rooms");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        l1.setBounds(150,20,100,20);
        add(l1);
        
        JLabel room = new JLabel("Room Number");
        room.setFont(new Font("Tahoma", Font.PLAIN, 16));
        room.setBounds(60,80,120,30);
        add(room);
        
        t1 = new JTextField();
        t1.setBounds(200,80,150,30);
        add(t1);
        
        JLabel available = new JLabel("Available");
        available.setFont(new Font("Tahoma", Font.PLAIN, 16));
        available.setBounds(60,130,120,30);
        add(available);
        
        String str1[] = {"Available", "Occupied"};
        c1 = new JComboBox(str1);
        c1.setBackground(Color.WHITE);
        c1.setBounds(200,130,150,30);
        add(c1);
        
        JLabel status = new JLabel("Cleaning Status");
        status.setFont(new Font("Tahoma", Font.PLAIN, 16));
        status.setBounds(60,180,120,30);
        add(status);
        
        String str2[] = {"Cleaned", "Dirty"};
        c2 = new JComboBox(str2);
        c2.setBackground(Color.WHITE);
        c2.setBounds(200,180,150,30);
        add(c2);
        
        JLabel price = new JLabel("Price");
        price.setFont(new Font("Tahoma", Font.PLAIN, 16));
        price.setBounds(60,230,120,30);
        add(price);
        
        t2 = new JTextField();
        t2.setBounds(200,230,150,30);
        add(t2);
        
        JLabel type = new JLabel("Room Type");
        type.setFont(new Font("Tahoma", Font.PLAIN, 16));
        type.setBounds(60,280,120,30);
        add(type);
        
        String str3[] = {"Single Bed", "Double Bed", "Deluxe", "Semi Luxury", "Suite luxury"};
        c3 = new JComboBox(str3);
        c3.setBackground(Color.WHITE);
        c3.setBounds(200,280,150,30);
        add(c3);
        
        b1 = new JButton("Add Room");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(60,350,130,30);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Cancel");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(220,350,130,30);
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon ii1 = new ImageIcon(getClass().getResource("twelve.jpg"));
        JLabel l2 = new JLabel(ii1);
        l2.setBounds(430,30,500,350);
        add(l2);
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            String room = t1.getText();
            String available = (String)c1.getSelectedItem();
            String status = (String)c2.getSelectedItem();
            String price = t2.getText();
            String type = (String)c3.getSelectedItem();
            
            Conn c = new Conn();
            try
            {
                String str ="INSERT INTO room VALUES('"+room+"','"+available+"','"+status+"','"+price+"','"+type+"')";
                c.s.executeUpdate(str);
                
                JOptionPane.showMessageDialog(null, "New Room Added");
                this.setVisible(false);
                c.s.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else if(ae.getSource()==b2)
        {
            this.setVisible(false);
        }
    }
    
    public static void main()
    {
        new AddRoom().setVisible(true);
    }
}
