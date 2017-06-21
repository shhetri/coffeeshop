package com.shhetri.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderLineDTO {
    @NotNull
    @Min(1)
    private int quantity;

    @NotNull
    private int productId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
