/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Abdullah
 */
public class CatDBAccess extends DBAccess{
    
    public boolean canCreateCatalogue()
    {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try
        {
            con = makeConnection();
            statement = (Statement) con.createStatement();
            rs = statement.executeQuery("SELECT COUNT(*) FROM Catalogue");
            rs.next();
            int count = rs.getInt(1);
            System.out.println("there are "+count+ " catalogues in the databse.");
            if(count == 0)
            {
                return true;
            }
        }
        catch(Exception e)
        {
            
        }
        finally
        {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(rs != null)
                {
                    rs.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess checkIfThereIsNoCatalogue");
            }
        }
        //a catalogue is already in the database, cant insert one
        return false;
    }
    
    public void insertNewCatalogue(String catalogueName)
    {
        Connection con = null;
        Statement statement = null;
        
        try
        {
            con = makeConnection();
            statement = (Statement) con.createStatement();
            int executeUpdate = statement.executeUpdate("INSERT INTO Catalogue VALUES('"+catalogueName+"')");
            System.out.println("Inserted catalogue into database.");
            
        }
        catch(Exception e)
        {
            System.out.println("Could not insert catalogue into database in insertNewCatalogue in CatDBAcess\n"+e.getMessage());
        }
        finally
        {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in insertNewCatalogue in CatDBAcess");
            }
        }
    }
    
    public ArrayList<String> getProduct(String name)
     {
        ArrayList<String> productInfo = new ArrayList<String>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Products WHERE name='"+name+"'";
        try
        {
            connection = makeConnection();
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                productInfo.add(resultSet.getString("name"));
                productInfo.add(resultSet.getString("packageType"));
                productInfo.add(resultSet.getString("description"));
                productInfo.add(resultSet.getString("unitPrice"));
                productInfo.add(resultSet.getString("unitsInAPack"));
                productInfo.add(resultSet.getString("minimumStockLimit"));
            }
            return productInfo;
        }
        catch(SQLException ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                {
                    connection.close();
                }

                if(resultSet != null)
                {
                    resultSet.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess getProducts");
            }
        }
        return null;
    }
    
     public HashMap<Integer, String> getProducts()
     {
        HashMap<Integer, String> products = new HashMap<Integer, String>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Products";
        try
        {
            connection = makeConnection();
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                int roleID = resultSet.getInt("productID");
                String roleType = resultSet.getString("name");
                products.put(roleID, roleType);
            }
            return products;
        }
        catch(SQLException ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                {
                    connection.close();
                }

                if(resultSet != null)
                {
                    resultSet.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess getProducts");
            }
        }
        return null;
    }
     
     public HashMap<Integer, String> getMatchingProducts(String id, String name, String name2)
     {
        HashMap<Integer, String> products = new HashMap<Integer, String>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT productID, name FROM Products WHERE productID LIKE '%"+id+"%' AND name LIKE '%"+name+"%' AND name LIKE '%"+name2+"%'";

        try
        {
            connection = makeConnection();
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                int roleID = resultSet.getInt("productID");
                String roleType = resultSet.getString("name");
                products.put(roleID, roleType);
            }
            if(!products.isEmpty())
            {
                System.out.println("Foudn products");
                return products;
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                {
                    connection.close();
                }

                if(resultSet != null)
                {
                    resultSet.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess getProducts");
            }
        }
        System.out.println("Did not find any products");
        return null;
    }
     
     public void deleteProduct(String productName)
     {
         Connection con = null;
         Statement statement = null;
         
         try
         {
             con = makeConnection();
             statement = (Statement) con.createStatement();
             String sql = "DELETE FROM Products WHERE name ='"+productName+"'";
             System.out.println(sql);
             statement.executeUpdate(sql);
             System.out.println("Deleted product.");
         }
         catch(Exception e)
         {
             System.err.println("Error: "+e.getMessage());
         }
         finally
         {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess deleteProduct");
            }
        }
     }
     
     public String getCatalogueName()
     {
         Connection con = null;
         Statement stat = null;
         ResultSet rs = null;
         String sql = "SELECT name FROM Catalogue";
         try
         {
             con = makeConnection();
             stat = (Statement) con.createStatement();
             rs = stat.executeQuery(sql);
             if(rs.next())
             {
                 return rs.getString("name");
             }
         }
         catch(Exception e)
         {
             System.err.println("Error: "+e.getMessage());
         }
         finally
         {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(stat != null)
                {
                    stat.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess getCatalogueName");
            }
        }
        return "";
     }
     
     public boolean insertProduct(String name, String description, String unitPrice, String type, String unitsPerPack, String initialStock, String stockLimit)
     {
         Connection con = null;
         Statement stat = null;
         String sql = "INSERT INTO Products Values(NULL, '"+getCatalogueName()+"', ";
         sql += "'"+name+"', ";
         sql += "'"+description+"', ";
         sql += "'"+unitPrice+"', ";
         sql += "'"+stockLimit+"', ";
         sql += "'"+type+"', ";
         sql += "'box', ";
         sql += "'"+unitsPerPack+"', ";
         sql += "'"+initialStock+"', ";
         sql += "NOW())";
         System.out.println(sql);
         
         try
         {
             con = makeConnection();
             stat = (Statement) con.createStatement();
             stat.executeUpdate(sql);
             System.out.println("inserted product");
             return true;
         }
         catch (Exception e)
         {
             System.err.println("Error: "+e.getMessage());
         }
         finally
         {
             try
            {
                if(con != null)
                {
                    con.close();
                }

                if(stat != null)
                {
                    stat.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess insertProduct");
            }
         }
         return false;
     }
    
     
     public ArrayList<Vector> getCatalogueProdcuts()
     {
         
         Connection con = null;
         Statement stat = null;
         ResultSet rs = null;
         ResultSetMetaData md = null;
         String sql = "SELECT * FROM Products";
         ArrayList<Vector> products = null;
         
         
         try
         {
             con = makeConnection();
             stat = (Statement) con.createStatement();
             rs = stat.executeQuery(sql);
             md = (ResultSetMetaData) rs.getMetaData();
             
             
             Vector columns = new Vector(8);
            
            columns.add("ID");
            columns.add("Name");
            columns.add("Description");
            columns.add("Package Type");
            columns.add("Unit");
            columns.add("Units In A Pack");
            columns.add("Cost");
            columns.add("Stock");
            columns.add("Stock Limit");

            Vector data = new Vector();
            Vector row;
            
            //store row data
            while(rs.next())
            {
                row = new Vector(8);
                row.add(rs.getString("productID"));
                row.add(rs.getString("name"));
                row.add(rs.getString("description"));
                row.add(rs.getString("packageType"));
                row.add(rs.getString("unit"));
                row.add(rs.getString("unitsInAPack"));
                row.add(rs.getString("unitPrice"));
                row.add(rs.getString("currentStock"));
                row.add(rs.getString("minimumStockLimit"));
                data.add(row);
            }
            
            products = new ArrayList<Vector>();
            products.add(data);
            products.add(columns);
            
            return products;
            
         }
         catch (Exception e)
         {
             System.err.println("Error: "+e.getMessage());
         }
         finally
         {
             try
            {
                if(con != null)
                {
                    con.close();
                }

                if(stat != null)
                {
                    stat.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess getcatalogueproducts");
            }
         }

        return null;
     }
     
    public int getProductStock(String name)
    {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try
        {
            con = makeConnection();
            statement = (Statement) con.createStatement();
            String sql = "SELECT currentStock FROM Products WHERE name='"+name+"'";
            
            System.out.println(sql);
            rs = statement.executeQuery(sql);
            int stock = 0;
            while(rs.next())
            {
                stock = rs.getInt("currentStock");
                
            }
            
            System.out.println("current stock is "+stock);
            return stock;
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        finally
        {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(rs != null)
                {
                    rs.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess getProductStock");
            }
        }
        return 0;
    }
    
    
     public void updateStock(String productName, String additionalStock)
    {
        Connection con = null;
        Statement statement = null;
        
        try
        {
            con = makeConnection();
            statement = (Statement) con.createStatement();
            int executeUpdate = statement.executeUpdate("UPDATE Products SET currentStock = currentStock + '"+additionalStock+"' WHERE name ='"+productName+"'");
            System.out.println("updated stock");
            
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        finally
        {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in insertNewCatalogue in CatDBAcess");
            }
        }
    }
    

     public HashMap<Integer, Product> getProductsInformation()
     {
         HashMap<Integer, Product> products = new HashMap<Integer, Product>();
         Connection con = null;
         Statement stat = null;
         ResultSet rs = null;
         String sql = "SELECT * FROM Products";
         
         try
         {
            con = makeConnection();
            stat = (Statement) con.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next())
            {
                int productID = rs.getInt("productID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double unitPrice = rs.getDouble("unitPrice");
                int quantity = rs.getInt("currentStock");
                Product product = new Product();
                product.setId(productID);
                product.setName(name);
                product.setDescription(description);
                product.setUnitPrice(unitPrice);
                product.setCurrentStock(quantity);
                
                products.put(productID, product);
            }
            return products;
         }
         catch(SQLException ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
        finally
        {
            try
            {
                if(con != null)
                {
                    con.close();
                }

                if(rs != null)
                {
                    rs.close();
                }

                if(stat != null)
                {
                    stat.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess getSaleableProducts");
            }
        }
        return null;
     }
     public HashMap<Integer, String> getSaleableProducts()
     {
        HashMap<Integer, String> products = new HashMap<Integer, String>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Products WHERE currentStock > 0";
        try
        {
            connection = makeConnection();
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productID");
                products.put(productID, productName);
            }
            return products;
        }
        catch(SQLException ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                {
                    connection.close();
                }

                if(resultSet != null)
                {
                    resultSet.close();
                }

                if(statement != null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                System.err.println("Could not close the resources in CatDBAccess getSaleableProducts");
            }
        }
        return null;
    }
    
}
