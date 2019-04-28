package com.arangodb.spring.demo.services;


import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    List<Route> findAllRoutes();
    Route findById(String Id);
    List<Route> findShortestPath(String from, String to);
    Boolean isRouteExist(Route route);
    Optional<Route> save(Route route);
    void updateRoute(Route route);
    void deleteRouteById(String Id);
    void deleteAllRoutes();
}
