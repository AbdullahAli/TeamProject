/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author dfernandez
 */
public class CardPayment extends Payment {
    
    private String cardNo;
    private String cardHolder;
    private String expDate;
    private String startDate;
    private int secCode;
    
    public CardPayment(int id, 
                       int typeId, 
                       String date, 
                       double amount, 
                       String cardNo, 
                       String cardHolder, 
                       String expDate, 
                       String startDate, 
                       int secCode) {
        super(id, typeId, date, amount);
        this.cardNo = cardNo;
        this.cardHolder = cardHolder;
        this.expDate = expDate;
        this.startDate = startDate;
        this.secCode = secCode;
    }
    
    public String getCardNo() {
        return cardNo;
    }
    
    public String getCardHolder() {
        return cardHolder;
    }
    
    public String getExpDate() {
        return expDate;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public int getSecCode() {
        return secCode;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }
    
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    } 
    
    public void setSecCode(int secCode) {
        this.secCode = secCode;
    }
}
