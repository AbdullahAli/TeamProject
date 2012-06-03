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
    
    public String getOrderStatus(int orderID) {
        String orderStatus = "";
        
        Connection connection = null;
        Statement statementStatusID = null;
        ResultSet resultSetStatusID = null;
        Statement statementStatus = null;
        ResultSet resultSetStatus = null;
        String sqlStatusID = "SELECT * FROM OrderDetails WHERE orderDetailsID = '" + orderID + "'";
        
        try {
            connection = makeConnection();
            connection.setTransactionIsolation(connection.TRANSACTION_READ_COMMITTED);
            statementStatusID = (Statement) connection.createStatement();
            resultSetStatusID = statementStatusID.executeQuery(sqlStatusID);
            if(resultSetStatusID.next()) {
                int orderStatusID = resultSetStatusID.getInt("statusID");
                String sqlStatus = "SELECT * FROM OrderStatuses WHERE statusID = '" + orderStatusID + "'";
                statementStatus = (Statement) connection.createStatement();
                resultSetStatus = statementStatusID.executeQuery(sqlStatus);
                if(resultSetStatus.next()) {
                    orderStatus = resultSetStatus.getString("status");
                }
            }
            
        }catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statementStatusID != null) {
                    statementStatusID.close();
                }
                if(statementStatus != null) {
                    statementStatus.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getAllOrderIDs");
            }
        }
        return orderStatus;
    }
    
}
