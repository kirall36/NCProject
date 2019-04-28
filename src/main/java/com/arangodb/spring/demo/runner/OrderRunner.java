package com.arangodb.spring.demo.runner;

import com.arangodb.spring.demo.entity.Order;
import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.spring.demo.repository.OrderRepository;
import com.arangodb.spring.demo.repository.RouteRepository;
import com.arangodb.spring.demo.repository.WarehouseRepository;
import com.arangodb.spring.demo.services.OrderService;
import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.core.ArangoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.ServletContext;
import java.io.File;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.*;

@ComponentScan("com.arangodb.spring.demo")
public class OrderRunner implements CommandLineRunner {

    @Autowired
    private ArangoOperations operations;
    @Autowired
    private OrderRepository repository;
    @Autowired RouteRepository routeRepository;

    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    OrderService orderService;

    @Autowired
    ServletContext servletContext;

    @Override
    public void run(final String... args) throws Exception {
        //Collection<Route> routes = createRoutes();
        //System.out.println(routes);
        Order o = new Order(3000, LocalDate.of(2016, 05, 06), LocalDate.of(2016, 06, 07), false, 1200,
                warehouseRepository.findById("warehouses/12378"), warehouseRepository.findById("warehouses/12374"));
        orderService.create(o);
        List<Order> orders = new ArrayList<>();
        repository.findAll().forEach(orders::add);
        for(Order o1 : orders){
            System.out.println(o1);
        }
        //Order o1 = repository.findById("order/74946");
        //System.out.println(o1);
    }

    public Collection<Route> createRoutes(){
        return Arrays.asList(routeRepository.findByName("SK1"), routeRepository.findByName("GR1"));
    }
}