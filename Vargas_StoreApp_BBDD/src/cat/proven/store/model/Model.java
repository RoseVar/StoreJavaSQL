package cat.proven.store.model;

import cat.proven.store.model.persistence.ProductDAO;
import java.util.List;

/**
 *
 * @author Roser
 */
public class Model {
    
    // attributes
    private ProductDAO productDao;
        
    
    //constructor
    public Model() {
        this.productDao = new ProductDAO();

    }
    

    //methods
    
    /**
     * retrieves all products from the data store
     * @return list of all products or null in case of error
     */
    public List<Product> listAllProducts(){
        List<Product> found = productDao.selectAll();
        return found;
    }
    

    /**
     * retrieves a product with the given code from the data source
     * @param code is the code to search
     * @return product with the given or null if not found or in case of error.
     */
    public Product findProductByCode(String code) {
        Product result=productDao.findProductByCode(code);
        return result;
    }
    
    /**
     * Adds a given product to the data source.
     * avoids adding null products or products with null code,
     * negative price, negative stock, 
     * in case of TV element: negative inches, 
     * and in case of Fridge element, avoids negative capacity.
     * also avoids adding when we have previously
     * another product with the same code in the list.
     * @param product is the product to add
     * @return true if successfully added, false otherwise.
     */
    public boolean addProduct(Product product) {
        boolean b= false;
        if (product==null) {
            b=false;
        } else {
            if (product.getCode()==null || product.getCode().equals("")) {
                b= false;
            } else {
                Product newProduct = productDao.findProductByCode(product.getCode());
                if (newProduct!=null) {
                    b= false;
                } else {
                    int result = productDao.insertNewProduct(product);
                    if (result==1) {
                        b= true;
                    }else {
                        b= false;
                    }
                }
            }
        }    
        return b; 
    }
     
    /**
     * finds products with stock lower than the given value.
     * @param stock the threshold value to search prodcuts
     * @return  list of products or null in case of error
     */
    public List<Product> findProductWithLowStock (int stock) {
        List<Product> found = productDao.searchProductsByLowStock(stock);
        return found;
        
    }
    
    /**
     * modifies a product in the data source.
     * @param currentProduct current attributes for the project
     * @param newProduct new attributes for the project
     * @return true if successfully modified, false otherwise
     */
    public boolean modifyProduct(Product currentProduct, Product newProduct) {
        boolean b= false;
        int registerAffected= productDao.modifyProduct(currentProduct, newProduct);
        if (registerAffected==1) {
            b=true;
        } 
        return b;
    }
        
    /**
     * remove the product given from the data source
     * @param product to be removed
     * @return true if product is removed
     *         false if product is not removed
     */
    public boolean removeProductData (Product product) {
        boolean resp = false;
        int registerAffected= productDao.removeProduct(product);
        if (registerAffected==1) {
            resp=true;
        } 
        return resp;
    }
    
        

    
}