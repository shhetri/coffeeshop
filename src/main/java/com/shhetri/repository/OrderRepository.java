package com.shhetri.repository;

import com.shhetri.model.Order;
import com.shhetri.model.User;
import com.shhetri.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findDistinctOrderByOrderLines_Product(Product product);

    List<Order> findOrderByUser(User user);

    List<Order> findOrderByOrderDateBetween(Date minDate, Date maxDate);
}
