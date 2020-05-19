
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener
{
    JMenuBar mb;
    JMenu m1, m2;
    JMenuItem i1,i2,i3,i4;
    
    public Dashboard()
    {
        setBounds(0,0,1950,1020);
        setLayout(null);
        
        mb = new JMenuBar();
        mb.setBounds(0,0,1950,30);
        add(mb);
        
        m1 = new JMenu("HOTEL MANAGEMENT");
        m1.setForeground(Color.BLUE);
        mb.add(m1);
        
        m2 = new JMenu("ADMIN");
        m2.setForeground(Color.RED);
        mb.add(m2);
        
        i1 = new JMenuItem("RECEPTION");
        i1.addActionListener(this);
        m1.add(i1);
        
        i2 = new JMenuItem("ADD EMPLOYEE");
        i2.addActionListener(this);
        m2.add(i2);
        
        i3 = new JMenuItem("ADD ROOM");
        i3.addActionListener(this);
        m2.add(i3);
        
        i4 = new JMenuItem("ADD DRIVER");
        i4.addActionListener(this);
        m2.add(i4);
        
        ImageIcon ii1 = new ImageIcon(getClass().getResource("third.jpg"));
        Image im = ii1.getImage().getScaledInstance(1950,1000,Image.SCALE_DEFAULT);
        ImageIcon ii2 = new ImageIcon(im);
        JLabel l1 = new JLabel(ii2);
        l1.setBounds(0,0,1950,1000);
        add(l1);
        
        JLabel l2 = new JLabel("THE TAJ GROUP WELCOMES YOU !");
        l2.setBounds(550,80,1000,50);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 46));
        l1.add(l2);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("RECEPTION"))
        {
            new Reception().setVisible(true);
        }
        else if(ae.getActionCommand().equals("ADD EMPLOYEE"))
        {
            new AddEmployee().setVisible(true);
        }
        else if(ae.getActionCommand().equals("ADD ROOM"))
        {
            new AddRoom().setVisible(true);
        }
        else if(ae.getActionCommand().equals("ADD DRIVER"))
        {
            new AddDriver().setVisible(true);
        }
    }
    
    public static void main(String[] args)
    {
        new Dashboard().setVisible(true);
    }
}
