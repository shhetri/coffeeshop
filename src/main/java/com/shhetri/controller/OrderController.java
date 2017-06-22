package com.shhetri.controller;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.service.OrderService;
import com.shhetri.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller("orderController")
public class OrderController {
    private final OrderService orderService;
    private PersonService personService;

    @Autowired
    public OrderController(OrderService orderService, PersonService personService) {
        this.orderService = orderService;
        this.personService = personService;
    }

    @GetMapping("/orders")
    public String myOrders(Model model, Principal principal) throws ModelNotFoundException {
        model.addAttribute("user", personService.findByEmail(principal.getName()));

        return "order/list";
    }

    @GetMapping("/admin/orders")
    public String allOrders() {
        return "admin/order/list";
    }
}
