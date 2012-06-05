package infopharma.data;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class RprtDBAccess extends DBAccess 
{
    public ArrayList<String> getMerchantDetails(String accountNumber) 
    {
        ArrayList<String> details = new ArrayList<String>();
        
        Connection connection = null;
        Statement stat = null;
        ResultSet rs = null;
        String sqlStatuses = "SELECT * FROM MerchantDetails WHERE accountNumber='"+accountNumber+"'";
        
        try 
        {
            connection = makeConnection();
            stat = (Statement) connection.createStatement();
            rs = stat.executeQuery(sqlStatuses);            
            if(rs.next())
            {
                details.add(rs.getString("company"));
                details.add(rs.getString("address"));
                details.add(rs.getString("postcode"));
                details.add(rs.getString("phone"));
                details.add(rs.getString("accountNumber"));
            }
            return details;
        }
        catch(SQLException ex) 
        {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if(connection != null) 
                {
                    connection.close();
                }
                if(stat != null) 
                {
                    stat.close();
                }
            }
            catch(Exception e) 
            {
                System.err.println("Could not close the resources in Reports getMerchantDetails");
            }
        }
        return null;
    }
}