package com.shhetri.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_lines")
public class OrderLine extends Model {
    private int quantity;
    @OneToOne
    private Product product;
    @ManyToOne
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
