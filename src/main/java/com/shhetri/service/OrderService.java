package com.shhetri.service;

import com.shhetri.dto.OrderDTO;
import com.shhetri.dto.OrderLineDTO;
import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.Order;
import com.shhetri.model.OrderLine;
import com.shhetri.model.Person;
import com.shhetri.model.Product;
import com.shhetri.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private ProductService productService;
    private PersonService personService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService, PersonService personService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.personService = personService;
    }

    public Order save(OrderDTO order) throws ModelNotFoundException {
        Order newOrder = new Order();
        Person person = personService.findById(order.getPersonId());

        if (order.getOrderLines() != null) {
            for (OrderLineDTO orderLine : order.getOrderLines()) {
                Product product = productService.getProduct(orderLine.getProductId());
                OrderLine newOrderLine = new OrderLine();
                newOrderLine.setQuantity(orderLine.getQuantity());
                newOrderLine.setProduct(product);
                newOrder.addOrderLine(newOrderLine);
            }
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            newOrder.setOrderDate(simpleDateFormat.parse(order.getOrderDate()));
            newOrder.setPerson(person);
            return orderRepository.save(newOrder);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public List<Order> findByProduct(Product product) {
        return orderRepository.findDistinctOrderByOrderLines_Product(product);
    }

    public List<Order> findByUser(Person person) {
        return orderRepository.findOrderByPerson(person);
    }

    public List<Order> findByDate(Date minDate, Date maxDate) {
        return orderRepository.findOrderByOrderDateBetween(minDate, maxDate);
    }

    public Order findById(int id) throws ModelNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Order.class.getSimpleName(), id));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
