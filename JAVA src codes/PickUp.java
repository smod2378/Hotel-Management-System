

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class PickUp extends JFrame implements ActionListener
{
    Choice c1;
    JButton b1, b2;
    JTable t1;
    
    public PickUp()
    {
        setBounds(500,200,1000,650);
        setLayout(null);
        
        JLabel l1 = new JLabel("Pick Up Service");
        l1.setForeground(Color.BLUE);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        l1.setBounds(370,30,200,30);
        add(l1);
        
        JLabel l2 = new JLabel("Type Of Car");
        l2.setBounds(50,100,100,20);
        add(l2);
        
        c1 = new Choice();
        try
        {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM driver");
            while(rs.next())
            {
                c1.add(rs.getString("car_model"));
            }
        }
        catch(Exception e)
        {
        }
        c1.setBackground(Color.WHITE);
        c1.setBounds(150,100,200,25);
        add(c1);
        
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
        
        JLabel l3 = new JLabel("Name");
        l3.setBounds(30,160,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("Age");
        l4.setBounds(180,160,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("Gender");
        l5.setBounds(320,160,120,20);
        add(l5);
        
        JLabel l6 = new JLabel("Company");
        l6.setBounds(460,160,100,20);
        add(l6);
        
        JLabel l7 = new JLabel("Model");
        l7.setBounds(610,160,100,20);
        add(l7);
        
        JLabel l8 = new JLabel("Availability");
        l8.setBounds(740,160,100,20);
        add(l8);
        
        JLabel l9 = new JLabel("Location");
        l9.setBounds(890,160,100,20);
        add(l9);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            Conn c = new Conn();
            try
            {
                String str1 = "SELECT * FROM driver WHERE car_model = '"+c1.getSelectedItem()+"'";
                
                ResultSet rs1 = c.s.executeQuery(str1);
                t1.setModel(DbUtils.resultSetToTableModel(rs1));
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
        new PickUp().setVisible(true);
    }
}
