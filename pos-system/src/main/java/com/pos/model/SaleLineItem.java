package com.pos.model;

import com.pos.util.Money;
import java.math.BigDecimal;

public class SaleLineItem {
    private final Product product;
    private int quantity;
    private final BigDecimal unitPrice;
    private final BigDecimal taxRate;

    public SaleLineItem(Product product, int quantity) {
        this(product, quantity, product.getPrice(), product.getTaxRate());
    }

    public SaleLineItem(Product product, int quantity, BigDecimal unitPrice, BigDecimal taxRate) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.taxRate = taxRate;
    }

    public BigDecimal getLineTotal() {
        return Money.round(unitPrice.multiply(BigDecimal.valueOf(quantity)));
    }

    public BigDecimal getLineTax() {
        return getLineTotal().multiply(taxRate);
    }

    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
