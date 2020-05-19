
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddCustomer extends JFrame implements ActionListener
{
    JTextField t1, t2, t3 ,t4, t5;
    JButton b1, b2;
    JComboBox c1;
    Choice c2; // used as an alternative of combobox. It is in package awt (that is why it has no J)
    JRadioButton r1, r2;
    
    public AddCustomer()
    {
        setBounds(500,240,800,550);
        setLayout(null);
        
        JLabel l1 = new JLabel("New Customer Form");
        l1.setFont(new Font("Tahoma", Font.BOLD, 20));
        l1.setForeground(Color.BLUE);
        l1.setBounds(100,20,300,30); // Increase the width from 200 to 300 if the label is not displayed completely
        add(l1);
        
        JLabel id = new JLabel("ID");
        id.setBounds(35,80,100,20);
        add(id);
        
        String str1[] = {"Passport", "Aadhar Card", "Driving License", "Voter ID"};
        c1 = new JComboBox(str1);
        c1.setBackground(Color.WHITE);
        c1.setBounds(200,80,150,25);
        add(c1);
        
        JLabel number = new JLabel("Number");
        number.setBounds(35,120,100,20);
        add(number);
        
        t1 = new JTextField();
        t1.setBounds(200,120,150,25);
        add(t1);
        
        JLabel name = new JLabel("Name");
        name.setBounds(35,160,100,20);
        add(name);
        
        t2 = new JTextField();
        t2.setBounds(200,160,150,25);
        add(t2);
        
        JLabel gender = new JLabel("Gender");
        gender.setBounds(35,200,100,20);
        add(gender);
        
        r1 = new JRadioButton("Male");
        r1.setBackground(Color.WHITE);
        r1.setBounds(200,200,60,25);
        add(r1);
        
        r2 = new JRadioButton("Female");
        r2.setBackground(Color.WHITE);
        r2.setBounds(280,200,80,25); // width increased from 60 to 80 ad Female was not displyed ccompletely
        add(r2);
        
        JLabel country = new JLabel("Country");
        country.setBounds(35,240,100,20);
        add(country);
        
        t3 = new JTextField();
        t3.setBounds(200,240,150,25);
        add(t3);
        
        JLabel room = new JLabel("Allocated Room");
        room.setBounds(35,280,150,20);
        add(room);
        
        // Allocated room number cannot be static whereas it has to be dynamic hence database has to be used
        c2 = new Choice();
        try
        {
            Conn c = new Conn();
            String str = "SELECT * FROM room WHERE available = 'Available'";
            ResultSet rs = c.s.executeQuery(str);
            while(rs.next())
            {
                c2.add(rs.getString("room_number"));
            }
            rs.close();
            c.s.close();
        }
        catch(Exception e)
        {
        }
        c2.setBackground(Color.WHITE);
        c2.setBounds(200,280,150,25);
        add(c2);
        // Allocated room fininshes
        
        JLabel check = new JLabel("Checked In");
        check.setBounds(35,320,150,20);
        add(check);
        
        t4 = new JTextField();
        t4.setBounds(200,320,150,25);
        add(t4);
        
        JLabel deposit = new JLabel("Deposit");
        deposit.setBounds(35,360,150,20);
        add(deposit);
        
        t5 = new JTextField();
        t5.setBounds(200,360,150,25);
        add(t5);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("fifth.png"));
        Image im = i1.getImage().getScaledInstance(300,400,Image.SCALE_DEFAULT);
        ImageIcon i2  = new ImageIcon(im);
        JLabel il1 = new JLabel(i2);
        il1.setBounds(400,50,300,400);
        add(il1);
        
        b1 = new JButton("Add Customer");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(40,410,140,25);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(210,410,140,25);
        b2.addActionListener(this);
        add(b2);
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            String id = (String)c1.getSelectedItem();
            String number = t1.getText();
            String name = t2.getText();
            String gender = null;
            if(r1.isSelected())
            {
                gender = "Male";
            }
            else if(r2.isSelected())
            {
                gender = "Female";
            }
            String country = t3.getText();
            String room = c2.getSelectedItem();
            String check = t4.getText();
            String deposit = t5.getText();
            
            Conn c = new Conn();
            try
            {
                String str = "INSERT INTO customer VALUES('"+id+"','"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+check+"','"+deposit+"')";
                String str_up = "UPDATE room SET available = 'Occupied' WHERE room_number = '"+room+"'";
                c.s.executeUpdate(str);
                c.s.executeUpdate(str_up);
                
                JOptionPane.showMessageDialog(null, "New Customer Added");
                c.s.close();
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
    }
    
    public static void main(String[] args)
    {
        new AddCustomer().setVisible(true);
    }
}
