package cat.proven.store.model;

import java.util.Objects;

/**
 *ADT for product.
 * @author Roser
 */
public class Product {
    //attributes
    private String code;
    private String description;
    private double price;
    private int stock;
    
    /**
     * Full initialization constructor 
     * @param code: the value for the code attribute
     * @param description: description of the product item
     * @param price: value of the price
     * @param stock : amount of it in the store 
     */
    public Product(String code, String description, double price, int stock) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    /**
     * Default constructor.
     */
    public Product() {
    }
    
    /**
     * Constructor to generate a product only with key code
     * @param code 
     */
    public Product(String code) {
        this.code = code;
    }
    
    /**
     * Copy constructor
     * @param other 
     */
    public Product(Product other) {
        this.code = other.code;
        this.description = other.description;
        this.price = other.price;
        this.stock = other.stock;
    }
    
    //accessors
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    // serveix per donar un número únic de codificació d'objecte.
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b;
        if (obj == null){ //null object
            b= false;
        } else {
            if (this== obj) { //same object
                b= true;
            } else {
                if (obj instanceof Product) { //obj is a product
                    Product other = (Product) obj;
                    b= (this.code.equals(other.code)); //compare code of 2 instances
                } else {
                    b= false;
                }
            }
        }
        return b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("[code="); sb.append(code); sb.append("]");
        sb.append("[description="); sb.append(description); sb.append("]");
        sb.append("[price="); sb.append(price); sb.append("]");
        sb.append("[stock="); sb.append(stock); sb.append("]");
        sb.append("}");
        return sb.toString();
    }
    
    
    
}
