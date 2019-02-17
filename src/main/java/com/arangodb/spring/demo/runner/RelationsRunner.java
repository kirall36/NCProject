package com.arangodb.spring.demo.runner;

import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.repository.RouteRepository;
import com.arangodb.spring.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.Collection;

@ComponentScan("com.arangodb.spring.demo")
public class RelationsRunner implements CommandLineRunner {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void run(final String... args) throws Exception {
        Collection<Route> createRoutes = createRoutes();

        routeRepository.save(createRoutes);
        long count = routeRepository.count();

        Iterable<Route> all = routeRepository.findAll();

        System.out.println(String.format("A total of %s routes are persisted in the database", count));
        Iterable<Route> sortedRoutes = routeRepository.findAll();
        sortedRoutes.forEach(System.out::println);


    }

    public Collection<Route> createRoutes(){
        return Arrays.asList(new Route( warehouseRepository.findByName("Encor"), warehouseRepository.findByName("Goldfield"), "EG1", 1500 ),
                new Route( warehouseRepository.findByName("Encor"), warehouseRepository.findByName("Sheffeld"), "ES1", 2300),
                new Route( warehouseRepository.findByName("Sheffeld"), warehouseRepository.findByName("Kind house"), "SK1", 750),
                new Route(warehouseRepository.findByName("Kind house"), warehouseRepository.findByName("Rock century"), "GR1", 980),
                new Route(warehouseRepository.findByName("Kind house"), warehouseRepository.findByName("Kind house"), "KK1", 1200));
    }
}