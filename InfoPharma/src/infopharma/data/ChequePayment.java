/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author dfernandez
 */
public class ChequePayment extends Payment {
    
    private String payerName;
    private int sortcode;
    private String accountNumber;
    private String chequeNumber;
    
    public ChequePayment(int id, 
                         int typeId, 
                         String date, 
                         double amount,
                         String payerName, 
                         int sortcode,
                         String accountNumber,
                         String chequeNumber) {
        super(id, typeId, date, amount);
        this.payerName = payerName;
        this.sortcode = sortcode;
        this.accountNumber = accountNumber;
        this.chequeNumber = chequeNumber;
    }
    
    public String getPayerName() {
        return payerName;
    }
    
    public int getSortcode() {
        return sortcode;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getChequeNumber() {
        return chequeNumber;
    }
    
    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }
    
    public void setSortcode(int sortcode) {
        this.sortcode = sortcode;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }
}
