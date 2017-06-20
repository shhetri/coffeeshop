package com.shhetri.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@DynamicUpdate
public class Product extends Model {
    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @Min(0)
    private double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public Product() {
        super();
    }

    public Product(String productName, String description, double price, ProductType productType) {
        super();
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
