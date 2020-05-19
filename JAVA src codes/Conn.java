
import java.sql.*;

public class Conn
{
    Connection c=null;
    Statement s;
    
    public Conn()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            c=DriverManager.getConnection("jdbc:sqlite:projecthms");
            s = c.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
  
}
