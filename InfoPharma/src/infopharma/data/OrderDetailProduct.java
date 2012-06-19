/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author dfernandez
 */
public class OrderDetailProduct {
    
    private int productId;
    private int orderId;
    private double quantity;
    
    public OrderDetailProduct() {
        
    }
    
    public OrderDetailProduct(int productId, int orderId, double quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public double getQuantity() {
        return quantity;
    }
    
}
