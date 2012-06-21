/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Abdullah
 */
public class MiscDBAccess extends DBAccess{
        public UserAccount login(String username, String password)
        {
            UserAccount userAccount = null;
            Connection con = null;
            Statement stat = null;
            ResultSet rs = null;
            String sql = 
                "SELECT * FROM LoginDetails " +
                "INNER JOIN UserRoles " +
                "ON LoginDetails.roleID = UserRoles.roleID " +
                "WHERE LoginDetails.username='" + username + "'" +
                "AND LoginDetails.password= MD5('" + password + "')";
            
            System.out.println(sql);
            try
            {
                con = makeConnection();
                stat = (Statement) con.createStatement();
                rs = stat.executeQuery(sql);
                if(rs.next())
                {
                    String usr = rs.getString("username");
                    String pwd = rs.getString("password");
                    String role = rs.getString("roleType");
                    userAccount = new UserAccount(role, username);
                    System.out.println("logged in as: " + role);
                    return userAccount;
                }
                else
                {
                    System.out.println("cant log in");
                }
            }
            catch(SQLException ex) 
            {
                System.out.println(ex.getStackTrace());
            }
            return null;
	}
        
        
        public ArrayList<Vector> getLatePayments()
     {
         
         Connection con = null;
         Statement stat = null;
         ResultSet rs = null;
         ResultSetMetaData md = null;
         String sql = "SELECT * FROM OrderDetails WHERE paymentID IS NULL AND orderDate <= CURDATE() - 15";
         
         System.out.println(sql);
         ArrayList<Vector> latePayments  = null;
         
         
         try
         {
             con = makeConnection();
             stat = (Statement) con.createStatement();
             rs = stat.executeQuery(sql);
             md = (ResultSetMetaData) rs.getMetaData();
             
             
             Vector columns = new Vector(8);
            
            columns.add("ID");
            columns.add("Total");
            columns.add("Order Date");
            columns.add("Account Number");

            Vector data = new Vector();
            Vector row;
            
            //store row data
            while(rs.next())
            {
                row = new Vector(8);
                row.add(rs.getString("orderDetailsID"));
                row.add(rs.getString("total"));
                row.add(rs.getString("orderDate"));
                row.add(rs.getString("accountNumber"));
                data.add(row);
            }
            
            latePayments = new ArrayList<Vector>();
            latePayments.add(data);
            latePayments.add(columns);
            
            return latePayments;
            
         }
         catch (Exception e)
         {
             System.err.println("Error: "+e.getMessage());
         }
         finally
         {
             try
            {
                if(con != null)
                {
                    con.close();
                }

                if(stat != null)
                {
                    stat.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess latePayments");
            }
         }

        return null;
     }
        
        public void suspendAccounts()
     {
         
         Connection con = null;
         Statement stat = null;
         ResultSet rs = null;
         ResultSetMetaData md = null;
         String sql = "SELECT accountNumber FROM OrderDetails WHERE paymentID IS NULL AND orderDate <= CURDATE() - 30";
         
         System.out.println(sql);
         ArrayList<Vector> latePayments  = null;
         try
         {
             con = makeConnection();
             stat = (Statement) con.createStatement();
             rs = stat.executeQuery(sql);
            //store row data
            while(rs.next())
            {
                
                String updateSQL = "UPDATE MerchantDetails set accountStatusID = 3 WHERE accountNumber = '"+
                        rs.getInt("accountNumber") +"'";
                
                System.out.println(updateSQL);
                
                Statement stat2 = (Statement)con.createStatement();
                stat2.executeUpdate(updateSQL);
                
            }
            
         }
         catch (Exception e)
         {
             System.err.println("Error: "+e.getMessage());
         }
         finally
         {
             try
            {
                if(con != null)
                {
                    con.close();
                }

                if(stat != null)
                {
                    stat.close();
                }

            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess suspentacounts");
            }
         }
     }
        
        
        public ArrayList<Vector> getLowStock()
     {
         
         Connection con = null;
         Statement stat = null;
         ResultSet rs = null;
         ResultSetMetaData md = null;
         String sql = "SELECT * FROM Products WHERE (minimumStockLimit * 0.1) >= currentStock";
         
         System.out.println(sql);
         ArrayList<Vector> latePayments  = null;
         
         
         try
         {
             con = makeConnection();
             stat = (Statement) con.createStatement();
             rs = stat.executeQuery(sql);
             md = (ResultSetMetaData) rs.getMetaData();
             
             
             Vector columns = new Vector(5);
            
            columns.add("ID");
            columns.add("Name");
            columns.add("Description");
            columns.add("Stock");
            columns.add("Stock Limit");

            Vector data = new Vector();
            Vector row;
            
            //store row data
            while(rs.next())
            {
                row = new Vector(5);
                row.add(rs.getString("productID"));
                row.add(rs.getString("name"));
                row.add(rs.getString("description"));
                row.add(rs.getString("currentStock"));
                row.add(rs.getString("minimumStockLimit"));
                data.add(row);
            }
            
            latePayments = new ArrayList<Vector>();
            latePayments.add(data);
            latePayments.add(columns);
            
            return latePayments;
            
         }
         catch (Exception e)
         {
             System.err.println("Error: "+e.getMessage());
         }
         finally
         {
             try
            {
                if(con != null)
                {
                    con.close();
                }

                if(stat != null)
                {
                    stat.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in getLowStock");
            }
         }

        return null;
     }
        
        public int getAccountNumber(String username)
    {
        Connection connection = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql = "SELECT accountNumber FROM LoginDetails WHERE username = '"+username+"'";
        System.out.println("::  "+sql);
        try {
            connection = makeConnection();
            stat = (Statement) connection.createStatement();
            rs = stat.executeQuery(sql);
            rs.next();
            return rs.getInt("accountNumber");
            
            
        }catch(SQLException ex) {
            try { 
                connection.rollback();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(stat != null) {
                    stat.close();
                }
                
                if(rs != null) {
                    rs.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in AccountDBAccess ");
            }
        }
        return 0;
    }
}
