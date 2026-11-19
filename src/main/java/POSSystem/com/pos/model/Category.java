package src.main.java.POSSystem.com.pos.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Long id;
    private String name;
    private String description;

    private List<Product> products;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<Product> getProducts() {
        return List.copyOf(products);
    }
 
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void addProduct(Product product){
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.!");
        }
    }
}
