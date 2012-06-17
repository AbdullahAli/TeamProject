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
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author dfernandez
 */
public class OrderDBAccess extends DBAccess 
{
    
    public int getDiscountID(int accountNumber)
    {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql = "SELECT discountPlanID FROM MerchantDetails WHERE accountNumber = '"+accountNumber+"'";
        
        try
        {
            con = makeConnection();
            stat = (Statement)con.createStatement();
            rs = stat.executeQuery(sql);
            
            int discountID = 0;
            while(rs.next())
            {
                discountID = rs.getInt("discountPlanID");
            }
            return discountID;
        }
        catch(Exception ex) {
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
                System.err.println("Could not close the resources in OrderDBAccess getMerchantOrders");
            }
        }
        return 0;
    }
    
    public ArrayList<Order> getMerchantOrders(int accountNumber)
    {
        ArrayList<Order> orders = new ArrayList<Order>();
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM OrderDetails WHERE accountNumber = '"+accountNumber+"'";
        
        try
        {
            con = makeConnection();
            stat = (Statement) con.createStatement();
            
            rs = stat.executeQuery(sql);
            
            while(rs.next())
            {
                Order order = new Order();
                order.setID(rs.getInt("orderDetailsID"));
                order.setDate(rs.getString("orderDate"));
                order.setTotal(rs.getDouble("total"));
                
                orders.add(order);
            }
            return orders;
        }
        catch(Exception ex) {
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
                System.err.println("Could not close the resources in OrderDBAccess getMerchantOrders");
            }
        }
        return null;
    }
    
    public void insertOrder(ArrayList<OrderedProduct> orderedProducts, int accountNumber, double orderTotal)
    {
        Connection con = null;
        Statement stat = null;
        String insertIntoOrderDetailsSql;
        ResultSet rs1 = null;
        
        
        //for testing
        accountNumber = MerchantAccount.getAccountNumber();
        
        try
        {
            con = makeConnection();
            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            
            stat = (Statement) con.createStatement();
            
            String getMaxOrderDetailsID = "SELECT MAX(orderDetailsID) FROM OrderDetails";
            rs1 = stat.executeQuery(getMaxOrderDetailsID);
            rs1.next();
            int orderID = rs1.getInt("MAX(orderDetailsID)") + 1;
            System.out.println("max id is: "+rs1.getString("MAX(orderDetailsID)"));
            
            
            String insertIntoOrderDetailsSQL = "INSERT INTO OrderDetails VALUES (";
            insertIntoOrderDetailsSQL += "'"+orderID+"', ";
            insertIntoOrderDetailsSQL += "'"+orderTotal+"', ";
            insertIntoOrderDetailsSQL += "NOW(), ";
            insertIntoOrderDetailsSQL += "'1', ";
            insertIntoOrderDetailsSQL += "NULL, ";
            insertIntoOrderDetailsSQL += "NULL, ";
            insertIntoOrderDetailsSQL += "'"+accountNumber+"')"; 


            //insert into database
            System.out.println("Inserting into orders");
            stat.executeUpdate(insertIntoOrderDetailsSQL);
            System.out.println("inserted into orders");
            
            
            for(OrderedProduct product : orderedProducts)
            {


                String insertIntoOrderDetails_Products = "INSERT INTO OrderDetails_Products VALUES (";
                insertIntoOrderDetails_Products += "NULL, ";
                insertIntoOrderDetails_Products += "'"+product.getid()+"', ";
                insertIntoOrderDetails_Products += "'"+product.getQuantity()+"', ";
                insertIntoOrderDetails_Products += "'"+orderID+"')";

                String updateProductQuantitySQL = "UPDATE Products set currentStock = currentStock - '"+product.getQuantity()+"' "
                        + "WHERE productID = '"+product.getid()+"'";
                
                
                System.out.println("Inserting into orders_products");
                stat.executeUpdate(insertIntoOrderDetails_Products);
                System.out.println("Inserting into orders_products");
                
                System.out.println("updating product quantity");
                stat.executeUpdate(updateProductQuantitySQL);
                System.out.println("updated product quantity");

            }
            String updateCreditSQL = "UPDATE MerchantDetails set balance = balance + '"+orderTotal+"'";
            
            System.out.println("udpating credit");
            stat.executeUpdate(updateCreditSQL);
            System.out.println("updated credit");

            System.out.println("Done...");
            con.commit();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error: " + ex.getMessage());
            try
            {
                con.rollback();
            }
            catch(Exception error)
            {
                System.err.println("Error: " + error.getMessage());
            }
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
                System.err.println("Could not close the resources in OrderDBAccess insertOrder");
            }
        }
        
    } 
    
    
    public HashMap<Integer, String> getOrderStatuses() 
    {
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
    
    public ArrayList<Product> getAllProductsInOrder(int orderId) {
        ArrayList<Product> productsInOrderArray = new ArrayList<Product>();
        
        Connection connection = null;
        Statement statementProductIds = null;
        ResultSet resultSetProductIds = null;
        Statement statementProduct = null;
        ResultSet resultSetProduct = null;
        String sqlProductIds = "SELECT * FROM OrderDetails_Products WHERE orderDetailsID = '" + orderId + "'";
        
        try {
            connection = makeConnection();
            connection.setTransactionIsolation(connection.TRANSACTION_READ_COMMITTED);
            statementProductIds = (Statement) connection.createStatement();
            resultSetProductIds = statementProductIds.executeQuery(sqlProductIds);
            while(resultSetProductIds.next()) {
                int productId = resultSetProductIds.getInt("productID");
                String sqlProduct = "SELECT * FROM Products WHERE productID = '" + productId + "'";
                statementProduct = (Statement) connection.createStatement();
                resultSetProduct = statementProduct.executeQuery(sqlProduct);
                while(resultSetProduct.next()) {
                    int id = resultSetProduct.getInt("productID");
                    String catName = resultSetProduct.getString("catalogueName");
                    String name = resultSetProduct.getString("name");
                    String description = resultSetProduct.getString("description");
                    double unitPrice = resultSetProduct.getDouble("unitPrice");
                    int minStockLimit = resultSetProduct.getInt("minimumStockLimit");
                    String packageType = resultSetProduct.getString("packageType");
                    String unit = resultSetProduct.getString("unit");
                    int unitsInPack = resultSetProduct.getInt("unitsInAPack");
                    int currentStock = resultSetProduct.getInt("currentStock");
                    String dateReceived = resultSetProduct.getString("dateRecieved");
                    Product product = new Product(id, 
                                                catName, 
                                                name, 
                                                description, 
                                                unitPrice, 
                                                minStockLimit, 
                                                packageType,
                                                unit,
                                                unitsInPack,
                                                currentStock,
                                                dateReceived);
                    productsInOrderArray.add(product);
                }
            }
        }catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statementProductIds != null) {
                    statementProductIds.close();
                }
                if(statementProduct != null) {
                    statementProduct.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getAllProductsInORder");
            }
        }
        return productsInOrderArray;
    }

    public void updateOrderStatus(int orderID, int statusID) throws SQLException {
        
        Connection connection = null;
        Statement statementUpdate = null;
        String sqlUpdate = "UPDATE OrderDetails SET statusID='" + statusID + "' WHERE orderDetailsID = '" + orderID + "'";
        
        try {
            connection = makeConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
            statementUpdate = (Statement) connection.createStatement();
            statementUpdate.executeUpdate(sqlUpdate);
            connection.commit();
        }catch(SQLException ex) {
            connection.rollback();
            System.err.println("Error: " + ex.getMessage());
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statementUpdate != null) {
                    statementUpdate.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess updateOrderStatus");
            }
        }
    }
    
    public ArrayList<Order> getAllUndispatchedOrders() {
        ArrayList<Order> ordersArray = new ArrayList<Order>();
        Connection connection = null;
        Statement statementOrders = null;
        ResultSet resultSetOrders = null;
        String sqlOrders = "SELECT * FROM OrderDetails WHERE dispatchID IS NULL";
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
                System.err.println("Could not close the resources in OrderDBAccess getAllUndispatchedOrders");
            }
        }
        return ordersArray;
    }
    
    
    public double getCredit(int accountNumber)
    {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql = "SELECT credit, balance FROM MerchantDetails WHERE accountNumber = '"+accountNumber+"'";
        
        try
        {
            con = makeConnection();
            stat = (Statement) con.createStatement();
            
            rs = stat.executeQuery(sql);
            rs.next();
            
            double credit = rs.getDouble("credit");
            double balance = rs.getDouble("balance");
            
            double canSpend = credit - balance;
            return canSpend;
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
                System.err.println("Could not close the resources in OrderDBAccess");
            }
        }
        return new Double(0);
    }
    
    public boolean dispatchOrder(int orderId, int statusId, Dispatch dispatch) {
        String courierRef = dispatch.getCourierRef();
        String courier = dispatch.getCourier();
        String estDeliveryDate = dispatch.getEstDeliveryDate();
        String dispatchDate = dispatch.getDispatchDate();
        Connection connection = null;
        Statement statementDispatch = null;
        Statement statementOrder = null;
        Statement statementDispatchId = null;
        ResultSet rsDispatchId = null;
        String sqlDispatchId = "SELECT MAX(dispatchID) FROM DispatchDetails";
        try {
            connection = makeConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
            statementDispatchId = (Statement) connection.createStatement();
            rsDispatchId = statementDispatchId.executeQuery(sqlDispatchId);
            if(rsDispatchId.next()) {
                int dispatchId = rsDispatchId.getInt("MAX(dispatchID)") + 1;
                String sqlDispatch = "INSERT INTO DispatchDetails VALUES ('" + dispatchId + "', '" + courierRef + "', '" + courier + "', '" + estDeliveryDate + "', '" + dispatchDate + "')";
                statementDispatch = (Statement) connection.createStatement();
                
                String sqlOrderUpdate = "UPDATE OrderDetails SET statusID='" + statusId + "', dispatchID = '" + dispatchId + "' WHERE orderDetailsID = '" + orderId + "'";
                statementOrder = (Statement) connection.createStatement();
                statementDispatch.executeUpdate(sqlDispatch);
                statementOrder.executeUpdate(sqlOrderUpdate);
                connection.commit();
                return true;
            }

        } catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
            try {
                connection.rollback();
            } catch(Exception error) {
                System.err.println("Error: " + error.getMessage());
            }
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statementOrder != null) {
                    statementOrder.close();
                }
                if(statementDispatch != null) {
                    statementDispatch.close();
                }
                if(statementDispatchId != null) {
                    statementDispatchId.close();
                }
            }catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess dispatchOrder");
            }
        }
        return false;
    }
    
    public ArrayList<Order> getUnpaidMerchantOrders(int accountNumber) {
        ArrayList<Order> unpaidOrders = new ArrayList<Order>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM OrderDetails WHERE accountNumber = '" + accountNumber + "' AND paymentID is NULL";
        try {
            con = makeConnection();
            con.setTransactionIsolation(con.TRANSACTION_READ_COMMITTED);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                int orderID = rs.getInt("orderDetailsID");
                double total = rs.getDouble("total");
                String date = rs.getString("orderDate");
                int statusID = rs.getInt("statusID");
                int paymentID = rs.getInt("paymentID");
                int dispatchID = rs.getInt("dispatchID");
                Order order = new Order(orderID, total, date, statusID, paymentID, dispatchID, accountNumber);
                unpaidOrders.add(order);
            }
        } catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if(con != null) {
                    con.close();
                }
                if(st != null) {
                    st.close();
                }
            } catch (Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getUnpaidMerchantOrders");
            }
        }
                
        return unpaidOrders;
    }
    
    public HashMap<Integer, String> getCardTypes() {
        HashMap<Integer, String> cardTypes = new HashMap<Integer, String>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM CardTypes";
        try {
            con = makeConnection();
            con.setTransactionIsolation(con.TRANSACTION_READ_COMMITTED);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("cardTypeID");
                String type = rs.getString("cardType");
                cardTypes.put(id, type);
            }
        } catch(SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
        } finally {
            try {
                if(con != null) {
                    con.close();
                }
                if(st != null) {
                    st.close();
                }
            } catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getCardTypes");
            }
        }
        return cardTypes;
    }

    public HashMap<Integer, String> getPaymentTypes() {
        HashMap<Integer, String> paymentTypes = new HashMap<Integer, String>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM PaymentTypes";
        try {
            con = makeConnection();
            con.setTransactionIsolation(con.TRANSACTION_READ_COMMITTED);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("paymentTypeID");
                String type = rs.getString("paymentType");
                paymentTypes.put(id, type);
            }
        } catch(SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
        } finally {
            try {
                if(con != null) {
                    con.close();
                }
                if(st != null) {
                    st.close();
                }
            } catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess getPaymentTypes");
            }
        }
        return paymentTypes;
    }

    public void makeCardPayment(int accountNumber, int orderId, CardPayment payment) {
        int paymentId;
        int typeId = payment.getTypeId();
        String date = payment.getDate();
        double amount = payment.getAmount();
        String cardNo = payment.getCardNo();
        String cardHolder = payment.getCardHolder();
        String expDate = payment.getExpDate();
        String startDate = payment.getStartDate();
        int secCode = payment.getSecCode();
        Connection con = null;
        Statement st = null;
        ResultSet rsPaymentId = null;
        ResultSet rsBalance = null;
        String sqlPaymentId = "SELECT MAX(paymentID) FROM Payments";
        try {
            con = makeConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
            st = (Statement) con.createStatement();
            rsPaymentId = st.executeQuery(sqlPaymentId);
            rsPaymentId.next();
            paymentId = rsPaymentId.getInt("MAX(paymentID)") + 1;
            System.out.println(paymentId);
            String sqlPayment = 
                    "INSERT INTO Payments VALUES('" + paymentId + "', '" + typeId + "', '" + date + "', '" + amount + "')";
            
            String sqlCardPayment =
                    "INSERT INTO CardPayments VALUES('" + paymentId + "', '" + typeId + "', '" + cardNo + "', '" + cardHolder + "', '" + expDate + "', '" + startDate + "', '" + secCode + "')";
            st.executeUpdate(sqlPayment);
            st.executeUpdate(sqlCardPayment);
            String sqlGetBalance = 
                    "SELECT * FROM MerchantDetails WHERE accountNumber = '" + accountNumber + "'";
            rsBalance = st.executeQuery(sqlGetBalance);
            rsBalance.next();
            double balance = rsBalance.getDouble("balance");
            double newBalance = balance - payment.getAmount();
            String sqlSetBalance = 
                    "UPDATE MerchantDetails SET balance = '" + newBalance + "' WHERE accountNumber = '" + accountNumber + "'";
            st.executeUpdate(sqlSetBalance);
            String sqlSetOrder = 
                    "UPDATE OrderDetails SET paymentID = '" + paymentId + "' WHERE orderDetailsID = '" + orderId + "'";
            st.executeUpdate(sqlSetOrder);
            con.commit();
            
        } catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
            try {
                con.rollback();
            } catch(Exception error) {
                System.err.println("Error: " + error.getMessage());
            }
        } finally {
            try {
                if(con != null) {
                    con.close();
                }
                if(st != null) {
                    st.close();
                }
            } catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess makeCardPayment");
            }
        }
        
    }
    
    public void makeChequePayment(int accountNumber, int orderId, ChequePayment payment) {
        int paymentId;
        int typeId = payment.getTypeId();
        String date = payment.getDate();
        double amount = payment.getAmount();
        String payerName = payment.getPayerName();
        int sortcode = payment.getSortcode();
        String chequeAccNumber = payment.getAccountNumber();
        String chequeNumber = payment.getChequeNumber();
        Connection con = null;
        Statement st = null;
        ResultSet rsPaymentId = null;
        ResultSet rsBalance = null;
        String sqlPaymentId = "SELECT MAX(paymentID) FROM Payments";
        try {
            con = makeConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
            st = (Statement) con.createStatement();
            rsPaymentId = st.executeQuery(sqlPaymentId);
            rsPaymentId.next();
            paymentId = rsPaymentId.getInt("MAX(paymentID)") + 1;
            System.out.println(paymentId);
            String sqlPayment = 
                    "INSERT INTO Payments VALUES('" + paymentId + "', '" + typeId + "', '" + date + "', '" + amount + "')";
            
            String sqlChequePayment =
                    "INSERT INTO ChequePayments VALUES('" + paymentId + "', '" + payerName + "', '" + sortcode + "', '" + chequeAccNumber + "', '" + chequeNumber + "')";
            st.executeUpdate(sqlPayment);
            st.executeUpdate(sqlChequePayment);
            String sqlGetBalance = 
                    "SELECT * FROM MerchantDetails WHERE accountNumber = '" + accountNumber + "'";
            rsBalance = st.executeQuery(sqlGetBalance);
            rsBalance.next();
            double balance = rsBalance.getDouble("balance");
            double newBalance = balance - payment.getAmount();
            String sqlSetBalance = 
                    "UPDATE MerchantDetails SET balance = '" + newBalance + "' WHERE accountNumber = '" + accountNumber + "'";
            st.executeUpdate(sqlSetBalance);
            String sqlSetOrder = 
                    "UPDATE OrderDetails SET paymentID = '" + paymentId + "' WHERE orderDetailsID = '" + orderId + "'";
            st.executeUpdate(sqlSetOrder);
            con.commit();
            
        } catch(SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
            try {
                con.rollback();
            } catch(Exception error) {
                System.err.println("Error: " + error.getMessage());
            }
        } finally {
            try {
                if(con != null) {
                    con.close();
                }
                if(st != null) {
                    st.close();
                }
            } catch(Exception e) {
                System.err.println("Could not close the resources in OrderDBAccess makeCardPayment");
            }
        }
        
    }
}
