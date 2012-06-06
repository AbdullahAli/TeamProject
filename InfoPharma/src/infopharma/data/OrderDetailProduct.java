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
    
    public OrderDetailProduct() {
        
    }
    
    public OrderDetailProduct(int productId, int orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
}
