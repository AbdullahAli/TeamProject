/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Abdullah
 */
public class CatDBAccess extends DBAccess{
    
    public boolean canCreateCatalogue()
    {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try
        {
            con = makeConnection();
            statement = (Statement) con.createStatement();
            rs = statement.executeQuery("SELECT COUNT(*) FROM Catalogue");
            rs.next();
            int count = rs.getInt(1);
            System.out.println("there are "+count+ " catalogues in the databse.");
            if(count == 0)
            {
                return true;
            }
        }
        catch(Exception e)
        {
            
        }
        finally
        {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(rs != null)
                {
                    rs.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess checkIfThereIsNoCatalogue");
            }
        }
        //a catalogue is already in the database, cant insert one
        return false;
    }
    
    public void insertNewCatalogue(String catalogueName)
    {
        Connection con = null;
        Statement statement = null;
        
        try
        {
            con = makeConnection();
            statement = (Statement) con.createStatement();
            int executeUpdate = statement.executeUpdate("INSERT INTO Catalogue VALUES('"+catalogueName+"')");
            System.out.println("Inserted catalogue into database.");
            
        }
        catch(Exception e)
        {
            System.out.println("Could not insert catalogue into database in insertNewCatalogue in CatDBAcess\n"+e.getMessage());
        }
        finally
        {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in insertNewCatalogue in CatDBAcess");
            }
        }
    }
}
