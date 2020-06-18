package cat.proven.views;

import cat.proven.store.model.Product;

/**
 *
 * @author Roser
 */
public interface FormInterface {
    
    /**
     * reads product data from user
     * @return product or null in case of error
     */
    Product input();
    
}
