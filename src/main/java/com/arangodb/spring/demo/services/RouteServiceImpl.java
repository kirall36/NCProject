package com.arangodb.spring.demo.services;

import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.spring.demo.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> findAllRoutes() {
        List<Route> routes = new ArrayList<>();
        routeRepository.findAll().forEach(routes::add);
        return routes;
    }

    @Override
    public List<Route> findShortestPath(String from, String to){
        List<Route> path = new ArrayList<>();
        Collection<Route> routes = routeRepository.findShortestPath(from, to); //.forEach(path::add);\
        for(Route r : routes)
        {
            System.out.printf(r.toString());
        }
        return path;
    }

    @Override
    public Route findById(String Id) {
        return routeRepository.findById(Id);
    }

    @Override
    public Boolean isRouteExist(Route route) {
        List<Route> routes = new ArrayList<>();
        routeRepository.findAll().forEach(routes::add);
        for(Route r : routes)
        {
            if(r.getName().equals(route.getName())) return true;
        }
        return false;
    }

    @Override
    public Optional<Route> save(Route route) {
        return Optional.of(routeRepository.save(route));
    }

    @Override
    public void updateRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public void deleteRouteById(String Id) {
        routeRepository.delete(Id);
    }

    @Override
    public void deleteAllRoutes() {
        routeRepository.deleteAll();
    }
}
