package com.arangodb.spring.demo.runner;

import com.arangodb.spring.demo.entity.Order;
import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.spring.demo.repository.OrderRepository;
import com.arangodb.spring.demo.repository.RouteRepository;
import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.core.ArangoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@ComponentScan("com.arangodb.spring.demo")
public class OrderRunner implements CommandLineRunner {

    @Autowired
    private ArangoOperations operations;
    @Autowired
    private OrderRepository repository;
    @Autowired RouteRepository routeRepository;

    @Override
    public void run(final String... args) throws Exception {

        Collection<Route> routes = createRoutes();
        System.out.println(routes);
        //Order o = new Order(1000, LocalDate.of(2016, 05, 06), LocalDate.of(2016, 06, 07), 1200, routes);
        //repository.save(o);
        Order o = repository.findById("order/74946");
        System.out.println(o);
    }

    public Collection<Route> createRoutes(){
        return Arrays.asList(routeRepository.findByName("SK1"), routeRepository.findByName("GR1"));
    }
}
