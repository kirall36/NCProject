package com.arangodb.spring.demo.controllers;

import com.arangodb.spring.demo.entity.Order;
import com.arangodb.spring.demo.services.OrderService;
import com.arangodb.spring.demo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    // -------------------Retrieve All Orders---------------------------------------------
    @RequestMapping(value = "/order/", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> listAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    // -------------------Retrieve Single Order------------------------------------------
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@PathVariable("id") String id) {
        Order order = orderService.findById("order/" + id);
        if ( order == null) {
            return new ResponseEntity(new CustomErrorType("Order with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    // -------------------Create an Order-------------------------------------------
    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody @Valid Order order, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(new CustomErrorType(bindingResult.getFieldErrors().toString()), HttpStatus.CONFLICT);
        }
        if (orderService.isOrderExist(order)) {
            return new ResponseEntity(new CustomErrorType("Unable to create. An Order with id " +
                    order.getId() + " already exist."),HttpStatus.CONFLICT);
        }

        orderService.create(order);

        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/order/{id}").buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    // ------------------- Update an Order ------------------------------------------------

    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOrder(@PathVariable("id") String id, @RequestBody @Valid Order order, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(new CustomErrorType(bindingResult.getFieldErrors().toString()), HttpStatus.CONFLICT);
        }
        Order currentOrder = orderService.findById("order/" + id);

        if (currentOrder == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Order with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentOrder.setWeight(order.getWeight());
        currentOrder.setDate_of_creation(order.getDate_of_creation());
        currentOrder.setDate_of_filling(order.getDate_of_filling());
        currentOrder.setPrice(order.getPrice());
        currentOrder.setFrom(order.getFrom());
        currentOrder.setTo(order.getTo());
        currentOrder.setStatus(order.isStatus());
        currentOrder.setPath(order.getPath());

        orderService.updateOrder(currentOrder);
        return new ResponseEntity<Order>(currentOrder, HttpStatus.OK);
    }

    // ------------------- Delete a Order-----------------------------------------

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable("id") String id) {

        Order order = orderService.findById("order/" + id);
        if (order == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Order with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        orderService.deleteOrderById(id);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Orders-----------------------------

    @RequestMapping(value = "/order/", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteAllOrders() {

        orderService.deleteAllOrders();
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

}
