
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class SearchRoom extends JFrame implements ActionListener
{
    JComboBox c1;
    JCheckBox c2;
    JButton b1, b2;
    JTable t1;
    
    public SearchRoom()
    {
        setBounds(500,200,1000,650);
        setLayout(null);
        
        JLabel l1 = new JLabel("Search For Rooms");
        l1.setForeground(Color.BLUE);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        l1.setBounds(370,30,200,30);
        add(l1);
        
        JLabel l2 = new JLabel("Room Type");
        l2.setBounds(50,100,100,20);
        add(l2);
        
        String st[] = {"Single Bed", "Double Bed", "Deluxe", "Semi Luxury", "Suite luxury"};
        c1 = new JComboBox(st);
        c1.setBackground(Color.WHITE);
        c1.setBounds(150,100,150,25);
        add(c1);
        
        c2 = new JCheckBox("Only Display Available");
        c2.setBackground(Color.WHITE);
        c2.setBounds(600,100,190,30);
        add(c2);
        
        t1 = new JTable();
        t1.setBounds(0,200,1000,300);
        add(t1);
        
        getContentPane().setBackground(Color.WHITE);
        
        b1 = new JButton("Submit");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(300,520,120,30);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(500,520,120,30);
        b2.addActionListener(this);
        add(b2);
        
        JLabel l3 = new JLabel("Room Number");
        l3.setBounds(50,160,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("Availability");
        l4.setBounds(260,160,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("Cleaning Status");
        l5.setBounds(450,160,120,20);
        add(l5);
        
        JLabel l6 = new JLabel("Price");
        l6.setBounds(670,160,100,20);
        add(l6);
        
        JLabel l7 = new JLabel("Room Type");
        l7.setBounds(850,160,100,20);
        add(l7);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            Conn c = new Conn();
            try
            {
                String str1 = "SELECT * FROM room WHERE room_type = '"+c1.getSelectedItem()+"'";
                String str2 = "SELECT * FROM room WHERE room_type = '"+c1.getSelectedItem()+"' AND available = 'Available'";
                ResultSet rs1 = c.s.executeQuery(str1);
                t1.setModel(DbUtils.resultSetToTableModel(rs1));
                
                if(c2.isSelected())
                {
                    ResultSet rs2 = c.s.executeQuery(str2);
                    t1.setModel(DbUtils.resultSetToTableModel(rs2));
                    rs2.close();
                }
                rs1.close();
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
    
    public static void main(String[] args)
    {
        new SearchRoom().setVisible(true);
    }
}
