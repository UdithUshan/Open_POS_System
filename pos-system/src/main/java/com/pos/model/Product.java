package com.pos.model;

import com.pos.util.PosExeption;
import java.math.BigDecimal;

public class Product {
    private Long id;
    private String barcode;
    private String name;
    private BigDecimal price;
    private BigDecimal taxRate;
    private int stockQuantity;
    private int recorderLevel;
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, String barcode , BigDecimal price, BigDecimal taxRate, int stockQuantity, int recorderLevel, Category category) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;
        this.stockQuantity = stockQuantity;
        this.recorderLevel = recorderLevel;
        this.category = category;
    }

    public boolean isInStock(int qty) {
        return stockQuantity >= qty;
    }

    public boolean isLowStock() {
        return stockQuantity <= recorderLevel;
    }

    public void decreaseStock(int qty) {
        if (qty <= 0)
            throw new PosExeption("Quantity must e positive.");
        stockQuantity += qty;
    }

    @Override
    public String toString() {
        return name + " [" + barcode + "]";
    }
}
