/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dfernandez
 */
public class OrderDBAccess extends DBAccess {
    
    public ArrayList<Integer> getAllOrderIDs() {
        ArrayList<Integer> ordersArray = new ArrayList<Integer>();
        
        Connection connection = null;
        Statement statementOrders = null;
        ResultSet resultSetOrders = null;
        String sqlOrders = "SELECT * FROM OrderDetails";
        
        try {
            connection = makeConnection();
            connection.setTransactionIsolation(connection.TRANSACTION_READ_COMMITTED);
            statementOrders = (Statement) connection.createStatement();
            resultSetOrders = statementOrders.executeQuery(sqlOrders);
            while(resultSetOrders.next()) {
                int orderID = resultSetOrders.getInt("orderDetailsID");
                ordersArray.add(orderID);
            }
            
        }catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statementOrders != null) {
                    statementOrders.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getAllOrderIDs");
            }
        }
        return ordersArray;
    }
    
}
