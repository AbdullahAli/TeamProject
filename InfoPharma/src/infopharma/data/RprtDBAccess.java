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
    
    
    public ArrayList<ArrayList<String>> getMerchantOrders(String accountNumber, String startDate, String endDate) 
    {
        ArrayList<ArrayList<String>> allOrders = new ArrayList<ArrayList<String>>();
        Connection connection = null;
        Statement stat = null;
        ResultSet rs = null;
        String sqlOrders = "SELECT * FROM OrderDetails WHERE accountNumber = '"+accountNumber+"' AND orderDate >='"+startDate+"' AND"
                + " orderDate <= '"+endDate+"'";
        
        try {
            connection = makeConnection();
            connection.setTransactionIsolation(connection.TRANSACTION_READ_COMMITTED);
            stat = (Statement) connection.createStatement();
            rs = stat.executeQuery(sqlOrders);
            while(rs.next()) 
            {
                ArrayList<String> orderDetails = new ArrayList<String>();
                orderDetails.add(rs.getString("orderDetailsID"));
                orderDetails.add(rs.getString("orderDate"));
                orderDetails.add(rs.getString("total"));
                
                if(rs.getString("dispatchID") == null)
                {
                    orderDetails.add("No");
                }
                else
                {
                    orderDetails.add("Yes");
                }
                
                if(rs.getString("paymentID") == null)
                {
                    orderDetails.add("Pending");
                }
                else
                {
                    orderDetails.add("Yes");
                }
                
                if(rs.getString("paymentID") == null)
                {
                    orderDetails.add("No");
                }
                else
                {
                    orderDetails.add("Yes");
                }
                
                allOrders.add(orderDetails);
            }
            
            return allOrders;
        }catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(stat != null) {
                    stat.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getMerchantOrders");
            }
        }
        return null;
    }
    
    public ArrayList<String> getOrderProductIDs(String orderID)
    {
        ArrayList<String> productIDs = new ArrayList<String>();
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String sqlProductIDs= "SELECT * FROM OrderDetails_Products WHERE orderDetailsID ='"+orderID+"'";
        
        try
        {
            con = makeConnection();
            stat = (Statement) con.createStatement();
            rs = stat.executeQuery(sqlProductIDs);
            while(rs.next())
            {
                productIDs.add(rs.getString("productID"));
            }
            return productIDs;
        }catch(Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(con != null) {
                    con.close();
                }
                if(stat != null) {
                    stat.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in reports getOrderProductIDs");
            }
        }
        return null;
    }
    
    public String getProductPrice(String productID)
    {
        String price = "";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String sqlProductIDs= "SELECT * FROM Products WHERE productID ='"+productID+"'";
        
        try
        {
            con = makeConnection();
            stat = (Statement) con.createStatement();
            rs = stat.executeQuery(sqlProductIDs);
            if(rs.next())
            {
                price = rs.getString("unitPrice");
            }
            return price;
        }catch(Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(con != null) {
                    con.close();
                }
                if(stat != null) {
                    stat.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in reports ");
            }
        }
        return null;
    }
    
    public ArrayList<ArrayList<String>> getActivityOrder(String accountNumber, String startDate, String endDate) 
    {
        ArrayList<ArrayList<String>> allOrders = new ArrayList<ArrayList<String>>();
        Connection connection = null;
        Statement stat = null;
        ResultSet rs = null;
        String sqlOrders = "SELECT * FROM OrderDetails WHERE accountNumber = '"+accountNumber+"' AND orderDate >='"+startDate+"' AND"
                + " orderDate <= '"+endDate+"'";
        
        
        
        try {
            connection = makeConnection();
            connection.setTransactionIsolation(connection.TRANSACTION_READ_COMMITTED);
            stat = (Statement) connection.createStatement();
            rs = stat.executeQuery(sqlOrders);
            while(rs.next()) 
            {
                ArrayList<String> orderDetails = new ArrayList<String>();
                String orderID = rs.getString("orderDetailsID");
                orderDetails.add(orderID);
                
                ArrayList<String> productIDs = getOrderProductIDs(orderID);
                
                for(int i = 0; i < productIDs.size(); i++)
                {
                    String productID = productIDs.get(i);
                    orderDetails.add(productID);
                    
                    String price = getProductPrice(productID);
                    orderDetails.add(price);
                }
                
                allOrders.add(orderDetails);
            }
            
            return allOrders;
        }catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(stat != null) {
                    stat.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getMerchantOrders");
            }
        }
        return null;
    }
    
    
    public ArrayList<String> getLowStockedProducts()
    {
        ArrayList<String> products = new ArrayList<String>();
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Products WHERE currentStock < minimumStockLimit";
        
        try
        {
            con = makeConnection();
            stat = (Statement) con.createStatement();
            rs = stat.executeQuery(sql);
            
            while(rs.next())
            {
                products.add(rs.getString("productID"));
                products.add(rs.getString("currentStock"));
                products.add(rs.getString("minimumStockLimit"));
            }
            System.out.println("returned low stock");
            return products;
        }
        catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(con != null) {
                    con.close();
                }
                if(stat != null) {
                    stat.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in getlowstockprodcts");
            }
        }
        return null;
    }
    
}