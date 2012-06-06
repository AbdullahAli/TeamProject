/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author dfernandez
 */
public class Product {
    
    private int id;
    private String catName;
    private String name;
    private String description;
    private double unitPrice;
    private int minStockLimit;
    private String packageType;
    private String unit;
    private int unitsInPack;
    private int currentStock;
    private String dateReceived;
    
    public Product() {
        
    }
    
    public Product(int id,
                   String catName,
                   String name,
                   String description,
                   double unitPrice,
                   int minStockLimit,
                   String packageType,
                   String unit,
                   int unitsInPack,
                   int currentStock,
                   String dateReceived) {
        
        this.id = id;
        this.catName = catName;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.minStockLimit = minStockLimit;
        this.packageType = packageType;
        this.unit = unit;
        this.unitsInPack = unitsInPack;
        this.currentStock = currentStock;
        this.dateReceived = dateReceived;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setCatName(String catName) {
        this.catName = catName;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public void setMinStockLimit(int minStockLimit) {
        this.minStockLimit = minStockLimit;
    }
    
    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public void setUnitsInPack(int unitsInPack) {
        this.unitsInPack = unitsInPack;
    }
    
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    
    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }
    
    public int getId() {
        return id;
    }
    
    public String getCatName() {
        return catName;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public int getMinStockLimit() {
        return minStockLimit;
    }
    
    public String getPackageType() {
        return packageType;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public int getUnitsInPack() {
        return unitsInPack;
    }
    
    public int getCurrentStock() {
        return currentStock;
    }
    
    public String getDateReceived() {
        return dateReceived;
    }
}
