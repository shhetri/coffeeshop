package com.shhetri.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@DynamicUpdate
public class Product extends Model {
    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product Description is required")
    private String description;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private double price;

    @NotNull(message = "Product Type is required")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderLine> ordersLines = new ArrayList<>();

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

    public List<OrderLine> getOrdersLines() {
        return ordersLines;
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
