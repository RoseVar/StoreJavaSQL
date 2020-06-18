package cat.proven.views;

import cat.proven.store.model.Product;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CLASS DEPRECATED, use instead FridgeFOrm or TvForm.
 * @author Roser
 */
public class ProductForm implements FormInterface {

    @Override
    public Product input() {    
        Product p = null;
        Scanner sc = new Scanner (System.in);
        System.out.print("code:");
        String code = sc.next();
        System.out.print("description:");
        String description = sc.next();
        try {
            System.out.print("price:");
            double price = sc.nextDouble();
            System.out.println("stock:");
            int stock = sc.nextInt();
            p = new Product (code, description, price, stock);
        } catch (InputMismatchException e) {
            p=null;
        }
        return p;
    }
    
}
