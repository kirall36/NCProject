package com.arangodb.spring.demo.services;

import com.arangodb.spring.demo.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService{

    Optional<Order> save(Order order);
    List<Order> findAllOrders();
    Order findById(String id);
    Boolean isOrderExist(Order order);
    void updateOrder(Order order);
    void deleteOrderById(String Id);
    void deleteAllOrders();
}
