/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author dfernandez
 */
public class Order {
    
    private int id;
    private double total;
    private String date;
    private int statusID;
    private int paymentID;
    private int dispatchID;
    private int accountNumber;
    
    public Order() {
        
    }
    
    public Order(int id, 
                 double total, 
                 String date, 
                 int statusID, 
                 int paymentID, 
                 int dispatchID, 
                 int accountNumber) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.statusID = statusID;
        this.paymentID = paymentID;
        this.dispatchID = dispatchID;
        this.accountNumber = accountNumber;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setStatusId(int statusID) {
        this.statusID = statusID;
    }
    
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    
    public void setDispatchID(int dispatchID) {
        this.dispatchID = dispatchID;
    }
    
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public int getID() {
        return id;
    }
    
    public double getTotal() {
        return total;
    }
    
    public String getDate() {
        return date;
    }
    
    public int getStatusID() {
        return statusID;
    }
    
    public int getPaymentID() {
        return paymentID;
    }
    
    public int getDispatchID() {
        return dispatchID;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
}
