/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

import java.util.Date;

/**
 *
 * @author dfernandez
 */
public class Dispatch {
    
    private int dispatchId;
    private String courierRef;
    private String courier;
    private String estDeliveryDate;
    private String dispatchDate;
    
    public Dispatch(int dispatchId, 
                    String courierRef, 
                    String courier,
                    String estDeliveryDate,
                    String dispatchDate) {
        this.dispatchId = dispatchId;
        this.courierRef = courierRef;
        this.courier = courier;
        this.estDeliveryDate = estDeliveryDate;
        this.dispatchDate = dispatchDate;
    }
    
    public Dispatch(String courierRef, 
                    String courier,
                    String estDeliveryDate,
                    String dispatchDate) {
        this.courierRef = courierRef;
        this.courier = courier;
        this.estDeliveryDate = estDeliveryDate;
        this.dispatchDate = dispatchDate;
    }
    
    public int getId() {
        return dispatchId;
    }
    
    public void setId(int dispatchId) {
        this.dispatchId = dispatchId;
    }
    
    public String getCourierRef() {
        return courierRef;
    }
    
    public void setCourierRef(String courierRef) {
        this.courierRef = courierRef;
    }
    
    public String getCourier() {
        return courier;
    }
    
    public void setCourier(String courier) {
        this.courier = courier;
    }
    
    public String getEstDeliveryDate() {
        return estDeliveryDate;
    }
    
    public void setEstDeliveryDate(String estDeliveryDate) {
        this.estDeliveryDate = estDeliveryDate;
    }
    
    public String getDispatchDate() {
        return dispatchDate;
    }
    
    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }
}
