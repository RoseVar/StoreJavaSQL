package cat.proven.store.controllers;

import cat.proven.store.model.Model;
import cat.proven.store.model.Product;
import cat.proven.views.FormInterface;
import cat.proven.views.MainView;
import cat.proven.views.ProductForm;
import java.util.List;

/**
 * Main controller for Store Application
 * @author Roser
 */
public class MainController {
    //Attributes
    private final Model model;
    private final MainView view;

    //Constructor
    public MainController(Model model) {
        this.model= model;
        this.view= new MainView(this, model);
    }

    /**
     * method for start the process: show the view
     */
    public void start () {
        view.display();
    }
    
    /**
     * method for processing the given option 
     * following differents instrucctions
     * @param action 
     */
    public void processRequest(String action) {
        //process option
        if (action ==null) {
            action="wrong_option";
        }
        //process option
            switch (action){
                case "exit": //exit
                    view.setExit(true);
                    break;
                case "list_all_products": // List all products
                    listAllProducts();
                    break;
                case "list_product_by_code": // List product by code
                    listProductByCode();
                    break;
                case "list_product_with_low_stock": // List products with low stock
                    listProductsLowStock();
                    break;    
                case "add_a_new_product": // Add a new product
                    addNewProduct();
                    break;
                case "modify_a_product": // Modify a product
                    modifyProduct ();
                    break;
                case "remove_a_product": // Remove a produc
                    removeProduct();
                    break;
                // case action is null, or others  
                case "wrong_option":    
                default: // Not a valid option 
                    System.out.println("Invalid option!");
                    break;  
            }
            System.out.println("Processing action: " + action);
    }

    /**
     * It retrieves the list of all products and diplays all products.
     */    
    private void listAllProducts() { 
        //retrieve the list of products
        List<Product> result=model.listAllProducts();
        
        //display the retrieved data
        view.displayProductList(result);

    }

/**
     * asks the user for the code to search, 
     * if the input is not a code type, it will inform the user.
     * If the input is correct type, it will compare the given code with codes of the list of products.
     * if it does not find a match, it will inform the user and exit.
     * if it finds a match, it will return the product to the user.
     */
    private void listProductByCode() {
        //asks the user for the code to search
        String code= view.inputString("Input the code: ");
        // test
            //String code = null;
        if (code ==null) {
            view.displayMessage("Error reading the code");
        } else {
            Product result= model.findProductByCode(code);
            if (result==null){
               view.displayMessage("Product not found");
            } else {
                view.displayProduct(result);
            }
        }
    }

 /**
     * It asks the user whitch is the minimum stock of product.
     * if error reading stock, report to the user,
     * if correcty read, search for product with stock lower than the given one,
     * and report to the user.
     */
    private void listProductsLowStock() {
        //ask minimun stock 
        try{
            int minimumStock = Integer.parseInt(view.inputString("Which is the minimum stock you want to search?"));
            //find products with this stock
            List<Product> result=model.findProductWithLowStock(minimumStock);
            if (result ==null){
                view.displayMessage("There is no product with this stock");
            } else {
            //display the retrieved data
                view.displayProductList(result); 
            }   
        } catch (NumberFormatException e) {
            view.displayMessage("Wrong input");
        }

    }

    /**
     * adds a new product,
     * reads a product from the user
     * add it to data source
     * and report result to user
     */
    private void addNewProduct() {
        FormInterface form=new ProductForm();
        Product newProduct = form.input();
        if (newProduct !=null) {
            //try to add
            boolean result =  model.addProduct(newProduct);
            //report to user
            if  (result) {
                System.out.println("Product succesfully added");
            } else {
                System.out.println("Error adding product");
            }
        } else {
            System.out.println("Error adding product");
        }
        
    } 

    /**
    * ask the user the code of the product to change,
    * if it is correctly readed, searh in data source
    * If not found, reports that to user. It found, shows the product to user and
    * ask for confirmation.
    * If confirmed, reads from the user new data for the product
    * If not read, report to user. If read, modidy the product and report result to user.
    */
    private void modifyProduct() {
        //input code
        String codeToSearch = view.inputString("Which is the code of the product to make changes in?");
        if (codeToSearch!=null){
            //search product by code
            Product resultOfSearch= model.findProductByCode(codeToSearch);
           
            //display product if found or report not found
            if (resultOfSearch!=null) {
                System.out.print("The product information is ");
                view.displayProduct(resultOfSearch);
                //ask for confirmation
                String correctProduct = view.inputString("Is this the product you want to change?(yes/no)");
                if (correctProduct!=null) {
                    //modify product
                    if (correctProduct.equalsIgnoreCase("yes")) {
                        FormInterface form = new ProductForm();
                        //if form is ok, try to modify
                        if (form != null) {
                            Product newProduct = form.input();                            
                            boolean canModify = model.modifyProduct(resultOfSearch, newProduct);
                            // report the result to the user
                            if (canModify) {
                                 System.out.println("Product updated!");
                            } else {
                                 System.out.println("Product not modified");
                            }

                        } else {
                            System.out.println("Product not modified");
                        }
                    }
                } else {
                    System.out.println("Product not modified");
                }
            } else {
                System.out.println("Product not modified");
            }    
        } else {
            System.out.println("Product not modified");
        }
        
    } 
    
    
    /**
     * Remove the product indicated by the user
     * searching it by code. Once located, ask for confirmation to remove.
     * If it is confirmed, remove the product, and notify the user.
     * If it is not, notify the user "not deleted"
     */
    private void removeProduct() {
            //ask fot the code of the product
            String codeToSearch = view.inputString("Which is the code of the product you want to remove?");     
            if (codeToSearch !=null) {
                //test
                    //Product product= null;
                Product product = model.findProductByCode(codeToSearch);
                if (product !=null) {
                    //show the found product
                    System.out.println("This is the product information: " + product.toString());
                    //ask for confirmation
                    String answer = view.inputString("Are you sure you want to remove it? (yes/no)");
                    if (answer.equals("yes")) {
                        //try to remove and report to the user
                        boolean canRemove= model.removeProductData(product);
                        if (canRemove) {
                            System.out.println("The product has been succesfully removed");
                        } else {
                            System.out.println("The product has not been removed");
                        }
                    } else {
                        System.out.println("The product has not been removed");
                    }

                } else {
                    System.out.println("The product has not been removed: the product does not exist.");
                }
            } else {
                System.out.println("The product has not been removed: the product code is not valid.");
            }            
    }
}