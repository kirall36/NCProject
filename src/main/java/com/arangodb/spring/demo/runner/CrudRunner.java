package com.arangodb.spring.demo.runner;

import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.spring.demo.repository.RouteRepository;
import com.arangodb.spring.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import com.arangodb.springframework.core.ArangoOperations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collection;

@ComponentScan("com.arangodb.spring.demo")
public class CrudRunner implements CommandLineRunner {

    @Autowired
    private ArangoOperations operations;
    @Autowired
    private WarehouseRepository repository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("##All warehouses");
        Iterable<Warehouse> allWarehouses = repository.findAll();
        allWarehouses.forEach(System.out::println);

        Warehouse newWarehouse = new Warehouse("TestName", "TestCity", "TestStreet", "100");
        repository.save(newWarehouse);


        System.out.println("##All warehouses after saving");
        allWarehouses = repository.findAll();
        allWarehouses.forEach(System.out::println);

        repository.removeByNameAndCity("TestName", "TestCity");

        System.out.println("##All warehouses after removing");
        allWarehouses = repository.findAll();
        allWarehouses.forEach(System.out::println);
    }

    public static Collection<Warehouse> createWorkhouses(){
        return Arrays.asList(new Warehouse("Rock century", "Necropolis", "Mountain", "12"),
                new Warehouse("Sheffeld", "London", "Black", "13"),
                new Warehouse("Kind house", "Moscow", "Nevskay", "235/3"),
                new Warehouse("Goldfeild", "New-York", "5th avenue", "3a"),
                new Warehouse("Encor", "Voronezh", "Moscowscay", "43"));
    }
}