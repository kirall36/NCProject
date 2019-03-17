package com.arangodb.spring.demo.controllers;

import com.arangodb.spring.demo.entity.Order;
import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.services.OrderService;
import com.arangodb.spring.demo.services.RouteService;
import com.arangodb.spring.demo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RouteController {

    @Autowired
    RouteService routeService;

    // -------------------Retrieve All Routes---------------------------------------------
    @RequestMapping(value = "/route/", method = RequestMethod.GET)
    public ResponseEntity<List<Route>> listAllRoutes() {
        List<Route> routes = routeService.findAllRoutes();
        if (routes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Route>>(routes, HttpStatus.OK);
    }

    // -------------------Retrieve Single Route------------------------------------------
    @RequestMapping(value = "/route/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRoute(@PathVariable("id") String id) {
        Route route = routeService.findById("route/" + id);
        if ( route == null) {
            return new ResponseEntity(new CustomErrorType("Route with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Route>(route, HttpStatus.OK);
    }

    // -------------------Create a Route-------------------------------------------
    @RequestMapping(value = "/route/", method = RequestMethod.POST)
    public ResponseEntity<?> createRoute(@RequestBody @Valid Route route, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(new CustomErrorType(bindingResult.getFieldErrors().toString()), HttpStatus.CONFLICT);
        }
        if (routeService.isRouteExist(route)) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A Route with name " +
                    route.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        routeService.save(route);

        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/route/{id}").buildAndExpand(route.getId()).toUri());
        return new ResponseEntity<Route>(route, HttpStatus.CREATED);
    }

    // ------------------- Update a Route ------------------------------------------------

    @RequestMapping(value = "/route/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRoute(@PathVariable("id") String id, @RequestBody @Valid Route route, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(new CustomErrorType(bindingResult.getFieldErrors().toString()), HttpStatus.CONFLICT);
        }
        Route currentRoute = routeService.findById("route/" + id);

        if (currentRoute == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Route with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentRoute.setName(route.getName());
        currentRoute.setFrom(route.getFrom());
        currentRoute.setTo(route.getTo());
        currentRoute.setShipping_cost(route.getShipping_cost());

        routeService.updateRoute(currentRoute);
        return new ResponseEntity<Route>(currentRoute, HttpStatus.OK);
    }

    // ------------------- Delete a Route-----------------------------------------

    @RequestMapping(value = "/route/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRoute(@PathVariable("id") String id) {

        Route route = routeService.findById("route/" + id);
        if (route == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Route with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        routeService.deleteRouteById(id);
        return new ResponseEntity<Route>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Routes-----------------------------

    @RequestMapping(value = "/route/", method = RequestMethod.DELETE)
    public ResponseEntity<Route> deleteAllRoute() {

        routeService.deleteAllRoutes();
        return new ResponseEntity<Route>(HttpStatus.NO_CONTENT);
    }
}
