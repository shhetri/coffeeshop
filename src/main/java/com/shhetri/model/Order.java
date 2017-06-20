package com.shhetri.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends Model {
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();
    @OneToOne
    private Person person;

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        int quantity = 0;
        for (OrderLine ol : this.orderLines) {
            quantity += ol.getQuantity();
        }
        return quantity;
    }

    public double getTotalAmount() {
        double totalAmount = 0;

        for (OrderLine ol : this.orderLines) {
            totalAmount += ol.getSubtotal();
        }
        return totalAmount;
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLine.setOrder(this);
        this.orderLines.add(orderLine);
    }

    public void removeOrderLine(OrderLine orderLine) {
        this.orderLines.remove(orderLine);
        orderLine.setOrder(null);
    }

    public void clearOrderLines() {
        for (OrderLine orderLine : orderLines) {
            orderLine.setOrder(null);
        }
        orderLines.clear();
    }
}
