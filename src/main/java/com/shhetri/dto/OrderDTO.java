package com.shhetri.dto;

import com.shhetri.validators.annotations.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    @NotNull(message = "Order date is required")
    @Date(message = "Order must be a valid date")
    private String orderDate;

    @Valid
    private List<OrderLineDTO> orderLines = new ArrayList<>();

    @NotNull(message = "Person id is required")
    private int personId;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
