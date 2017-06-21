package com.shhetri.controller.rest;

import com.shhetri.dto.OrderDTO;
import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.Order;
import com.shhetri.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> index() {
        return orderService.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Order store(@Valid @RequestBody OrderDTO order) throws ModelNotFoundException {
        return orderService.save(order);
    }
}
