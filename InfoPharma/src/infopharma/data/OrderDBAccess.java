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
import java.util.HashMap;

/**
 *
 * @author dfernandez
 */
public class OrderDBAccess extends DBAccess {
    
    public HashMap<Integer, String> getOrderStatuses() {
        HashMap<Integer, String> orderStatuses = new HashMap<Integer, String>();
        
        Connection connection = null;
        Statement statementStatuses = null;
        ResultSet resultSetStatuses = null;
        String sqlStatuses = "SELECT * FROM OrderStatuses";
        
        try {
            connection = makeConnection();
            connection.setTransactionIsolation(connection.TRANSACTION_READ_COMMITTED);
            statementStatuses = (Statement) connection.createStatement();
            resultSetStatuses = statementStatuses.executeQuery(sqlStatuses);
            while(resultSetStatuses.next()) {
                int statusID = resultSetStatuses.getInt("statusID");
                String status = resultSetStatuses.getString("status");
                orderStatuses.put(statusID, status);
            }
        }catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statementStatuses != null) {
                    statementStatuses.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getOrderStatuses");
            }
        }
        return orderStatuses;
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> ordersArray = new ArrayList<Order>();
        
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
                double total = resultSetOrders.getDouble("total");
                String date = resultSetOrders.getString("orderDate");
                int statusID = resultSetOrders.getInt("statusID");
                int paymentID = resultSetOrders.getInt("paymentID");
                int dispatchID = resultSetOrders.getInt("dispatchID");
                int accountNumber = resultSetOrders.getInt("accountNumber");
                Order order = new Order(orderID, total, date, statusID, paymentID, dispatchID, accountNumber);
                ordersArray.add(order);
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
