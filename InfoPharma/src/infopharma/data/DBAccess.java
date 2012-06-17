/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdullah
 */
public class DBAccess {
    
    public Connection makeConnection()
    {
        Connection con = null;

        String url = "jdbc:mysql://localhost:8889/ipos";
        String user = "username";
        String password = "password";

        try 
        {
            con = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("is connected");
            return con;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog (null, "Could not connect to the database.", "CONNECTION ERROR", JOptionPane.ERROR_MESSAGE);
        } 
        //used to check if the connection failed
        return null;
    }
    
    public void closeConnection(Connection con)
    {
        try 
        {
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException ex) 
        {
                System.out.println("could not close connection");
        }
    }

    public void startTransaction()
    {

    }

    public void endTransaction()
    {

    }
}
