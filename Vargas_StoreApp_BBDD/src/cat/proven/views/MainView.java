package cat.proven.views;

import cat.proven.menu.Menu;
import cat.proven.store.controllers.MainController;
import cat.proven.store.model.Model;
import cat.proven.store.model.Product;
import cat.proven.util.InOut;
import java.util.List;
import java.util.Scanner;

/**
 * Main view of Store Application
 * @author Roser
 */
public class MainView {
    //Attributes
    private final MainController controller;
    private final Model model;
    private final Menu menu;
    
    private boolean exit;
    

    /**
     * Full initialization constructor 
     * @param controller: controller of the program
     * @param model : model of the program
     * also instantiate a new MainMenu to show the user
     */
    public MainView(MainController controller, Model model) {
        this.controller= controller;
        this.model=model;
        this.menu=new MainMenu();
    }
    
    // getters & setters needed
    public boolean isExit() {
        return exit;
    }
    public void setExit(boolean exit) {
        this.exit = exit;
    }
    
    
    /**
     * method for display MainView
     */
    public void display (){
        exit = false;
        //do, while the user do not choose exit program
        do {
            //show menu
            menu.show();
            //catch the option selected by the user
            String action = menu.getSelectedOptionActionCommand();
            //if there is one
            if (action!=null) {
                //process the given action
                processAction(action);
            }
        } while (!exit);
    }
    /**
     * Take the action requested by the user
     * and call controller method, so it can process it
     * (only it the action is not null).
     * @param action choosen by the user
     */
    private void processAction(String action) {
        if (action !=null) {
            switch (action) {
                //whatever the action is (if it is not null) call controller method.
                default:
                    controller.processRequest(action);
            }
        }
    }
    
    
    /*************** VIEW METHODS ***************************/
        
    /**
     * Show all information of a given product located in a given position
     * @param data is all the information of the product to display
     */
    public void displayProductList(List<Product> data) {
        if (data != null) {
            for (int i=0; i<data.size();i++) {
               System.out.println(data.get(i).toString());
            }
            System.out.format("%d products found\n", data.size());
        
        } else {
            System.out.println("Null data!");
        }    
    }
    
    /**
     * displays all information of a given product
     * @param product is the product to display
     */    
    public void displayProduct(Product product) {
        if (product != null) {
            System.out.println(product.toString());
        } else {
            System.out.println("Null product!");
        }   
        
    }
    
            
    /**
     * displays a message and reads an answer from the user
     * @param message is the message to display to user
     * @return the answer of the user or null in case of error
     */
    public String inputString (String message){
        System.out.print(message);
        Scanner scan = InOut.getScanner();
        return scan.next();
    }

    
    /**
     * Display a nessage to user.
     * @param message the message to display
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    
}
