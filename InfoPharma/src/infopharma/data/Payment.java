/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author dfernandez
 */
public class Payment {
    
    private int id;
    private int typeId;
    private String date;
    private double amount;
    
    public Payment(int id, int typeId, String date, double amount) {
        this.id = id;
        this.typeId = typeId;
        this.date = date;
        this.amount = amount;
    }
    
    public Payment(int typeId, String date, double amount) {
        this.typeId = typeId;
        this.date = date;
        this.amount = amount;
    }
    
    public int getId() {
        return id;
    }
    
    public int getTypeId() {
        return typeId;
    }
    
    public String getDate() {
        return date;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTypeId(int typdId) {
        this.typeId = typeId;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
