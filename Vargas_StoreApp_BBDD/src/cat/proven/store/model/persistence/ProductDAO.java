
package cat.proven.store.model.persistence;

import cat.proven.store.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarda
 */
public class ProductDAO {
         
    //Attributes, queries:
    static final String QUERY_LIST_ALL = "select * from products;";
    static final String QUERY_LIST_PRODUCT_BY_CODE = "select * from products where code=?;";
    static final String QUERY_LIST_PRODUCT_LOW_STOCK = "select * from products where stock<?;";
    static final String QUERY_ADD_NEW_PRODUCT = "insert into products (`code`, `description`, `price`, `stock`) VALUES (?, ?, ?, ?);";
    static final String QUERY_MODIFY_PRODUCT = "update products set code=?, description=?, price=?, stock=? where code=?;";
    static final String QUERY_REMOVE_PRODUCT = "delete from products where code=?;";
    
    //Constructor
    public ProductDAO() {
    }
    

    /**
     * Method for selecting all products from database
     * @return List of all products (or empty if there is no one)
     *      null otherwise
     */
    public List<Product> selectAll() {
        List<Product> result = new ArrayList();
        try {
            Connection con = DbConnect.getConnection();
            //if connection returned is correct
            if (con!=null) {
                //ask for a statement
                Statement st = con.createStatement();
                //execute the query
                ResultSet res = st.executeQuery(QUERY_LIST_ALL);
                //run trought all result
                while (res.next()) {
                    Product provProd = resultSetProducts(res);
                    //add it to the result list
                    result.add(provProd);
                }                
            } else {
                //if connection returned is null...
                result= null;
            }
        } catch (SQLException ex) {
            //if there is no connection returned...
            result= null;
        }
        
        return result;
    }

    
    /**
     * Method to change a ResultSet into a Product
     * set field by field
     * @param res ResultSet to be parsed
     * @return Product with the data of the ResultSet
     */
    private Product resultSetProducts(ResultSet res) {
        Product provProd= new Product();
        try {
            provProd.setCode(res.getString("code"));
            provProd.setDescription(res.getString("description"));
            provProd.setPrice(res.getDouble("price"));
            provProd.setStock(res.getInt("stock"));
            
        } catch (SQLException ex) {
            provProd=null;
        }
        return provProd;
        
    }
    
    /**
     * Method for searching and return a product
     * identify by its code
     * @param code to seach
     * @return Product with given code, 
     *          null otherwise
     */
    public Product findProductByCode(String code) {
        Product provProduct = new Product();
        //try get a connection
        try {
            Connection con = DbConnect.getConnection();
            //if connection is not null
            if (con!=null) {
                //create preparedStatement by connection and query
                PreparedStatement prepStat = con.prepareStatement(QUERY_LIST_PRODUCT_BY_CODE);
                //set parameters to query
                prepStat.setString(1, code);
                //recover the result of the query
                ResultSet res= prepStat.executeQuery();
                //if there is any result
                if (res.next()) {
                    provProduct= resultSetProducts(res);
                } else {
                    provProduct=null;
                }
            } else {
                provProduct=null;
            }
        //if there is not connection
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            provProduct=null;  
        }
        return provProduct;
    }
    
    /**
     * Method for searching all products that has its stock
     * under a given number
     * @param stock number of minim stock to searh under
     * @return list of products with stock under given one (or empty is there is no one)
     *      null otherwise
     */
    public List<Product> searchProductsByLowStock(int stock) {
        List<Product> result = new ArrayList();
        //try to get a connection
        try {
            Connection con = DbConnect.getConnection();
            //if connection is not null
            if (con!=null) {
               //create a prepared statement and add the parameter 
               PreparedStatement ps= con.prepareStatement(QUERY_LIST_PRODUCT_LOW_STOCK);
               ps.setInt(1, stock);
               //receive the result of the query
               ResultSet rs = ps.executeQuery();
               //if there is results, transform them into products and add them to the result list
               while (rs.next()) {
                   Product provProd = resultSetProducts(rs);
                   result.add(provProd);
               }
            } else {
                result=null;
            }
        //if there is no connection
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            result=null;
        }
        
        return result;
    }
    
    /**
     * Method for adding a new product
     * @param product to be added
     * @return 0 if the product has not been added, 1 if it has been added (1 affected row)
     */
    public int insertNewProduct(Product product) {
        int afectedRows=0;
        //get a connection
        try {
            Connection con = DbConnect.getConnection();
            //if connection is not null
            if (con!=null) {
                //create a prepared statement
                PreparedStatement ps = con.prepareStatement(QUERY_ADD_NEW_PRODUCT);
                //add the values of the paramenters
                ps.setString(1, product.getCode());
                ps.setString(2,product.getDescription());
                ps.setDouble(3, product.getPrice());
                ps.setInt(4,product.getStock());
                //excecute query and catch the result
                afectedRows= ps.executeUpdate();
            } else {
                afectedRows=0;
            }
        } catch (SQLException ex) {
            afectedRows=0;
        }
        return afectedRows;
    }
    
    /**
     * Method for modifing a product in the database
     * @param currentProduct old details of the product
     * @param newProduct new details of the product
     * @return 0 if the product has not been modified, 1 if it has been modified (1 affected row)
     */
    public int modifyProduct(Product currentProduct, Product newProduct) {
        int result=0;
        try {
            //get a connection
            Connection con = DbConnect.getConnection();
            if (con!=null) {
                //create a prepared statement                
                PreparedStatement ps = con.prepareStatement(QUERY_MODIFY_PRODUCT);
                //add the values of the paramenters
                ps.setString(1, newProduct.getCode());
                ps.setString(2,newProduct.getDescription());
                ps.setDouble(3, newProduct.getPrice());
                ps.setInt(4,newProduct.getStock());
                ps.setString(5,currentProduct.getCode());             
                //excecute query and catch the result
                result= ps.executeUpdate();
                
            } else {
                result=0;
            }
        } catch (SQLException ex) {

            result=0;
        }
        
        return result;
    }
    
    /**
     * Method for deleting a product in the database
     * @param product to be deleted
     * @return 0 if the product has not been deleted, 1 if it has been deleted (1 affected row)
     */
    public int removeProduct(Product product) {
        int result=0;
        try {
            //get a connection
            Connection con = DbConnect.getConnection();
            if (con!=null) {
                //create a prepared statement                
                PreparedStatement ps = con.prepareStatement(QUERY_REMOVE_PRODUCT);
                //add the values of the paramenters
                ps.setString(1, product.getCode());            
                //excecute query and catch the result
                result= ps.executeUpdate();           
            } else {
                result=0;
            }
        } catch (SQLException ex) {
            result=0;
        }
        
        return result;
    }
}

