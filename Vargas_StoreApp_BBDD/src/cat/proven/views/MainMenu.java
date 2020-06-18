package cat.proven.views;

import cat.proven.menu.Menu;
import cat.proven.menu.MenuItem;

/**
 *Main menu for product store application
 * @author Roser
 */
public class MainMenu extends Menu {

    //constructor
    public MainMenu() {
        setTitle("Store main menu");
        addOption(new MenuItem("Exit","exit"));
        addOption(new MenuItem("List all products","list_all_products"));
        addOption(new MenuItem("List product by code","list_product_by_code"));
        addOption(new MenuItem("List products with low stock","list_product_with_low_stock"));
        addOption(new MenuItem("Add a new product","add_a_new_product"));
        addOption(new MenuItem("Modify a product","modify_a_product"));
        addOption(new MenuItem("Remove a product","remove_a_product"));
     
    }
    
}
