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
public class AccountDBAccess extends DBAccess{
    
    public HashMap<Integer, String> getUserTypes(){
        HashMap<Integer, String> userTypes = new HashMap<Integer, String>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM UserRoles";
        try{
            connection = makeConnection();
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int roleID = resultSet.getInt("roleID");
                String roleType = resultSet.getString("roleType");
                userTypes.put(roleID, roleType);
            }
        }catch(SQLException ex){
            
        }finally{
            closeConnection(connection);
        }
        return userTypes;
    }
    
    public ArrayList<Integer> getDiscountPlans(){
        ArrayList<Integer> discountPlans = new ArrayList<Integer>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM DiscountPlans";
        try{
            connection = makeConnection();
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int discountID = resultSet.getInt("discountPlanID");
                discountPlans.add(discountID);
            }
        }catch(SQLException ex){
            
        }finally{
            closeConnection(connection);
        }
        return discountPlans;
    }
    
    public void registerMerchantUser(MerchantAccount merchantAccount){
        String company = merchantAccount.getCompany();
        String address = merchantAccount.getAddress();
        String postcode = merchantAccount.getPostcode();
        String telNumber = merchantAccount.getTelNumber();
        Double creditLimit = merchantAccount.getCreditLimit();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String maxAccountNumberSQL = "SELECT MAX(accountNumber) AS 'accountNumber' FROM LoginDetails";
        String roleIDSQL = "SELECT * FROM UserRoles WHERE roleType = 'Merchant'";
        String sql = "INSERT INTO MerchantDetails VALUES('','status','" + company + "', '" + address + "', '" + postcode + "', '" + telNumber + "', '" + creditLimit + "','','')";
        try {
            connection = makeConnection();
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException ex){
            System.err.println("Error: "+ex.getMessage());
        }finally{
            try{
                if(connection != null){
                    closeConnection(connection);
                }
                if(statement != null){
                    statement.close();
                }
            }
            catch(Exception e){
                System.err.println("Could not close the resources in AccountDBAccess registerMerchantUser");
            }
        }
    }
    
    public void registerStaffUser(String username, String password, String role) throws SQLException{
        Connection connection = null;
        Statement statementAccountNumber = null;
        Statement statementRoleID = null;
        Statement statementCreateAccount = null;
        ResultSet resultSetRoleID = null;
        //SHOULD WE HAVE AN ACCOUNT NUMBER?
        int roleID = 1;
        String userRoleIDSQL = "SELECT * FROM UserRoles WHERE roleType = '" + role + "'";
        String createAccountSQL = "INSERT INTO LoginDetails VALUES('" + username + "', '" + password + "', '" + roleID + "', NULL)";
        
        try{
            connection = makeConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
            
            statementRoleID = (Statement) connection.createStatement();
            resultSetRoleID = statementRoleID.executeQuery(userRoleIDSQL);
            if(resultSetRoleID.next()){
                roleID = resultSetRoleID.getInt("roleID");
            }
            
            statementCreateAccount = (Statement) connection.createStatement();
            statementCreateAccount.executeUpdate(createAccountSQL);
            
            connection.commit();
        }catch(SQLException ex){
            connection.rollback();
            System.err.println("Error: "+ex.getMessage());
        }finally{
            try{
                if(connection != null){
                    closeConnection(connection);
                }
                if(statementAccountNumber != null){
                    statementAccountNumber.close();
                }
                if(statementRoleID != null){
                    statementRoleID.close();
                }
                if(statementCreateAccount != null){
                    statementCreateAccount.close();
                }
            }
            catch(Exception e){
                System.err.println("Could not close the resources in AccountDBAccess registerMerchantUser");
            }
        }
        
    }
    
}
