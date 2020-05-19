

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Department extends JFrame implements ActionListener
{
    JButton b1, b2;
    JTable t1;
    
    public Department()
    {
        setBounds(500,200,700,550);
        setLayout(null);
        
        t1 = new JTable();
        t1.setBounds(0,50,500,300);
        add(t1);
        
        getContentPane().setBackground(Color.WHITE);
        
        b1 = new JButton("Submit");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(180,400,120,30);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(380,400,120,30);
        b2.addActionListener(this);
        add(b2);
        
        JLabel l1 = new JLabel("SR. No.");
        l1.setBounds(40,10,100,20);
        add(l1);
        
        JLabel l2 = new JLabel("Department");
        l2.setBounds(200,10,100,20);
        add(l2);
        
        JLabel l3 = new JLabel("Budget");
        l3.setBounds(370,10,100,20);
        add(l3);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            Conn c = new Conn();
            try
            {
                String str1 = "SELECT * FROM department";
                
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
        new Department().setVisible(true);
    }
}
