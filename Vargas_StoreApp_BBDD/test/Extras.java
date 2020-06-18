public class Extras {
    
//clase no necesaria, es como yo había hecho el inputProduct()
        
//    /**
//     * displays a message and reads next integer from the user
//     * @param message is the message to display to user
//     * @return the answer of the user or null in case of error
//     */
//    private int inputInt (String message){
//    System.out.print(message);
//    Scanner scan = new Scanner(System.in);
//    return scan.nextInt();
//    }
//    
//    /**
//     * displays a message and reads next Double from the user
//     * @param message is the message to display to user
//     * @return the answer of the user or null in case of error
//     */
//    private double inputDouble (String message){
//    System.out.print(message);
//    Scanner scan = new Scanner(System.in);
//    return scan.nextDouble();
//    }
//    
//    -------------------------------------------
//    
//        /**
//     * ask the user the attributes of new product
//     * ask for the code (it is necessary due it is primary key)
//     * ask if want to input the other attributes because are not necessary
//     * and if they want, we ask for them.
//     * @return the product input
//     */
//    public Product InputProduct() {
//        Product newProduct;
//        String description = null;
//        double price = 0;
//        int stock = 0;
//        boolean result=false;
//        boolean controlInsertCode=false;
//        
//            String code= inputString("Input the code: ");
//            if (code ==null) {
//                System.out.println("Error reading the code");
//            } else {
//            
//                boolean addDescription= false;
//                do{
//                    String askDescription= inputString("Do you want to add a description (Y/N): ");
//
//                    if (askDescription.equalsIgnoreCase("Y")) {
//                        addDescription = true;
//                        description= inputString("Input the description: ");
//                    } else if (askDescription.equalsIgnoreCase("N")) {
//                        addDescription = true;
//                    } else {
//                        addDescription = false;
//                    }                     
//                } while (!addDescription);            
//
//                boolean addPrice =false;
//                do{
//                    String askPrice= inputString("Do you want to add a price (Y/N): ");
//
//                    if (askPrice.equalsIgnoreCase("Y")) {
//                        addPrice = true;
//                        boolean correctType = true;
//                        do {
//                            try {
//                                price= inputDouble("Input the price: ");
//                                correctType = true;
//                            }  catch (InputMismatchException e){
//                            System.out.println("Price must be a number");
//                                correctType=false;
//                            }
//                        } while (!correctType);    
//                    } else if (askPrice.equalsIgnoreCase("N")) {
//                        addPrice = true;
//                    } else {
//                        addPrice = false;
//                    }                     
//                } while (!addPrice);
//
//                boolean addStock =false;
//                do{
//                    String askStock= inputString("Do you want to add a stock(Y/N): ");
//
//                    if (askStock.equalsIgnoreCase("Y")) {
//                        addStock = true;
//                        boolean correctType = true;
//                        do {
//                            try {
//                                stock= inputInt("Input the stock: ");
//                                correctType=true;
//                            } catch (InputMismatchException e){
//                                System.out.println("Stock must be a number");
//                                correctType=false;
//                            }
//                        } while (!correctType);    
//                    } else if (askStock.equalsIgnoreCase("N")) {
//                        addStock = true;
//                    } else {
//                        addStock = false;
//                    }                     
//                } while (!addStock);
//            }
//        newProduct = new Product(code, description, price, stock);        
//        return newProduct;        
//    }        
    
    
    
}
