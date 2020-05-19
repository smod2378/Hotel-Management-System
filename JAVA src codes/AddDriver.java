
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddDriver extends JFrame implements ActionListener
{
    JTextField t1, t2, t3, t4, t5;
    JRadioButton r1,r2;
    JComboBox c1, c2;
    JButton b1, b2;
    
    public AddDriver()
    {
        setBounds(500,210,1010,560);
        setLayout(null);
        
        JLabel title = new JLabel("Add Driver");
        title.setFont(new Font("Talhoma", Font.BOLD, 18));
        title.setBounds(130,10,150,30);
        add(title);
        
        JLabel name = new JLabel("Name");
        name.setFont(new Font("Talhoma", Font.PLAIN, 15));
        name.setBounds(60,70,100,30);
        add(name);
        
        t1 = new JTextField();
        t1.setBounds(200,70,150,30);
        add(t1);
        
        JLabel age = new JLabel("Age");
        age.setFont(new Font("Talhoma", Font.PLAIN, 15));
        age.setBounds(60,120,100,30);
        add(age);
        
        t2 = new JTextField();
        t2.setBounds(200,120,150,30);
        add(t2);
        
        JLabel gender = new JLabel("Gender");
        gender.setFont(new Font("Talhoma", Font.PLAIN, 15));
        gender.setBounds(60,170,100,30);
        add(gender);
        
        String str1[] = {"Male", "Female"};
        c1 = new JComboBox(str1);
        c1.setBackground(Color.WHITE);
        c1.setBounds(200,170,150,30);
        add(c1);
        
        JLabel car = new JLabel("Car Company");
        car.setFont(new Font("Talhoma", Font.PLAIN, 15));
        car.setBounds(60,220,100,30);
        add(car);
        
        t3 = new JTextField();
        t3.setBounds(200,220,150,30);
        add(t3);
        
        JLabel model = new JLabel("Car Model");
        model.setFont(new Font("Talhoma", Font.PLAIN, 15));
        model.setBounds(60,270,100,30);
        add(model);
        
        t4 = new JTextField();
        t4.setBounds(200,270,150,30);
        add(t4);
        
        JLabel available = new JLabel("Available");
        available.setFont(new Font("Talhoma", Font.PLAIN, 15));
        available.setBounds(60,320,100,30);
        add(available);
       
        String str2[] = {"Available", "Engaged"};
        c2 = new JComboBox(str2);
        c2.setBackground(Color.WHITE);
        c2.setBounds(200,320,150,30);
        add(c2);
        
        JLabel location = new JLabel("Location");
        location.setFont(new Font("Talhoma", Font.PLAIN, 15));
        location.setBounds(60,370,100,30);
        add(location);
        
        t5 = new JTextField();
        t5.setBounds(200,370,150,30);
        add(t5);
        
        b1 =  new JButton("Add Driver");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(60,440,130,30);
        b1.addActionListener(this);
        add(b1);
        
        b2 =  new JButton("Cancel");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setBounds(220,440,130,30);
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("eleven.jpg"));
        Image im = i1.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(im);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(380,10,600,500);
        add(l1);
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            String name = t1.getText();
            String age = t2.getText();
            String gender = (String)c1.getSelectedItem();
            String car = t3.getText();
            String model = t4.getText();
            String available = (String)c2.getSelectedItem();
            String location = t5.getText();
            
            Conn c = new Conn();
            try
            {
                String str = "INSERT INTO driver VALUES('"+name+"', '"+age+"', '"+gender+"', '"+car+"', '"+model+"', '"+available+"', '"+location+"')";
                c.s.executeUpdate(str);
                
                JOptionPane.showMessageDialog(null, "Driver Added");
                this.setVisible(false);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                try
                {
                    c.s.close();
                }catch(Exception e){}
            }
        }
        else if(ae.getSource()==b2)
        {
            this.setVisible(false);
        }
    }
    
    public static void main(String args[])
    {
        new AddDriver().setVisible(true);
    }
}
