
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Room extends JFrame implements ActionListener
{
    JTable t1;
    JButton b1, b2;
    
    public Room()
    {
        setBounds(450,200,1140,600);
        setLayout(null);
        
        t1 = new JTable();
        t1.setBounds(0,40,500,400);
        add(t1);
        
        JLabel l1 = new JLabel("Room No.");
        l1.setBounds(15,10,100,20);
        add(l1);
        
        JLabel l2 = new JLabel("Availability");
        l2.setBounds(110,10,100,20);
        add(l2);
        
        JLabel l3 = new JLabel("Status");
        l3.setBounds(220,10,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("Price");
        l4.setBounds(320,10,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("Room Type");
        l5.setBounds(410,10,100,20);
        add(l5);
        
        b1 = new JButton("Load Data");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(100,460,120,30);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(250,460,120,30);
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("eight.jpg"));
        Image im =  i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(im);
        JLabel il1 = new JLabel(i2);
        il1.setBounds(520,10,600,540);
        add(il1);
        
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
                String str = "SELECT * FROM room";
                ResultSet rs = c.s.executeQuery(str);
                
                t1.setModel(DbUtils.resultSetToTableModel(rs));
                
                rs.close();
                c.s.close();
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
    }
    
    public static void main(String args[])
    {
        new Room().setVisible(true);
    }
}
