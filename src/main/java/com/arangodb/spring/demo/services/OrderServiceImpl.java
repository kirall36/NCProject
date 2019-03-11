package com.arangodb.spring.demo.services;

import com.arangodb.spring.demo.entity.Order;
import com.arangodb.spring.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> save(Order order) {
        return Optional.of(orderRepository.save(order));
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order findById(String Id) {
        return orderRepository.findById(Id);
    }

    @Override
    public Boolean isOrderExist(Order order) {
        return orderRepository.exists(order.getId());
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(String Id) {
        orderRepository.delete(Id);
    }

    @Override
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
