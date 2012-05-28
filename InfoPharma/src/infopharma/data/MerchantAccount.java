/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author dfernandez
 */
public class MerchantAccount extends UserAccount {
    
    private String company;
    private String address;
    private String postcode;
    private String telNumber;
    private Double creditLimit;
    
    public MerchantAccount() {
        super("merchant");
    }
    
    public MerchantAccount(String company, 
                           String address, 
                           String postcode, 
                           String telNumber, 
                           Double creditLimit) {
        super("merchant");
        this.address = address;
        this.company = company;
        this.postcode = postcode;
        this.telNumber = telNumber;
        this.creditLimit = creditLimit;
    }
    
    public String getCompany(){
        return company;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getPostcode(){
        return postcode;
    }
    
    public String getTelNumber(){
        return telNumber;
    }
    
    public Double getCreditLimit(){
        return creditLimit;
    }
    
    public void setCompany(String company){
        this.company = company;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setPostcode(String postcode){
        this.postcode = postcode;
    }
    
    public void setTelNumber(String telNumber){
        this.telNumber = telNumber;
    }
    
    public void setCreditLimit(Double creditLimit){
        this.creditLimit = creditLimit;
    }
    
}
