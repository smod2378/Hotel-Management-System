
import javax.swing.*; // to use JFrame
import java.awt.*; // to use colors
import java.awt.event.*; // to use ActionListener

public class HotelManagementSystem extends JFrame implements ActionListener
{
    public HotelManagementSystem()
    {
        setBounds(300,300,1366,565);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("first.jpg"));
        JLabel l1 = new JLabel(i1);
        setLayout(null); // only once
        l1.setBounds(0,0,1366,565);
        add(l1);
        
        JLabel l2 = new JLabel("Hotel Management System");
        l2.setBounds(20,430,1000,90);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("serif", Font.PLAIN,60));
        l1.add(l2); // we need to call object l1 as we need to superimpose l2 on l1
        
        JButton b1 = new JButton("Next");
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        b1.setBounds(1150,450,150,50);
        b1.addActionListener(this);
        l1.add(b1);
        
        setVisible(true);
        
        // 1->2->3->4 loops infinitely
        while(true)
        {
            l2.setVisible(false); // 1. diasppear
            try
            {
                Thread.sleep(500); // 2. waits for 500ms
            }
            catch(Exception e)
            {    
            }
            l2.setVisible(true); // 3. appear
            try
            {
                Thread.sleep(500); // 4. waits for 500ms
            }
            catch(Exception e)
            {
            }
        }
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae) // This method has to be override if we use ActionListener
    {
        new Login().setVisible(true);
        this.setVisible(false);
    }
    
    public static void main(String[] args)
    {
        new HotelManagementSystem();
    }
}
