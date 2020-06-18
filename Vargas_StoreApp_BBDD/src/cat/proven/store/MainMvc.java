package cat.proven.store;

import cat.proven.store.controllers.MainController;
import cat.proven.store.model.Model;

/**
 * MVC main class for Store Application
 * @author Roser
 */
public class MainMvc {

    //Main
    public static void main(String[] args) {
        MainMvc app= new MainMvc();
        app.run();
    }
    /**
     * method to instantiate Model and Controller
     */
    public void run(){
         Model model = new Model();
         MainController controller = new MainController(model);
         controller.start();         
     }
    
}
