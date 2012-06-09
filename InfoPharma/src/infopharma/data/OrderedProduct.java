/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author Abdullah
 */
public class OrderedProduct 
{
    private int id;
    private double quantity;
    private double total;
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    public void setQuantity(double quantity)
    {
        this.quantity = quantity;
    }
    
    public void setTotal(double total)
    {
        this.total = total;
    }
    
    public int getid()
    {
        return this.id;
    }
    
    public double getQuantity()
    {
        return this.quantity;
    }
    
    public double getTotal()
    {
        return this.total;
    }
    
}
