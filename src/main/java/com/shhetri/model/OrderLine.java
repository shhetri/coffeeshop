package com.shhetri.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "order_lines")
@DynamicUpdate
public class OrderLine extends Model {
    private int quantity;
    @OneToOne
    private Product product;
    @ManyToOne
    @JsonBackReference
    private Order order;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getSubtotal() {
        return quantity * product.getPrice();
    }

    public double getPrice() {
        return product.getPrice();
    }
}
