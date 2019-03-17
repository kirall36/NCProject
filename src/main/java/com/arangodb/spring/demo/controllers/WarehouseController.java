package com.arangodb.spring.demo.controllers;

import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.spring.demo.services.WarehouseService;
import com.arangodb.spring.demo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static java.lang.String.valueOf;

@RestController
@RequestMapping("/api")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    // -------------------Retrieve All Warehouses---------------------------------------------
    @RequestMapping(value = "/warehouse/", method = RequestMethod.GET)
    public ResponseEntity<List<Warehouse>> listAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.findAllWarehouses();
        if (warehouses.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
    }

    // -------------------Retrieve Single Warehouse------------------------------------------
    @RequestMapping(value = "/warehouse/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWarehouse(@PathVariable("id") String id) {
        Warehouse warehouse = warehouseService.findById("warehouses/" + id);
        if ( warehouse == null) {
            return new ResponseEntity(new CustomErrorType("Warehouse with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }

    // -------------------Create a Warehouse-------------------------------------------
    @RequestMapping(value = "/warehouse/", method = RequestMethod.POST)
    public ResponseEntity<?> createWarehouse(@RequestBody @Valid Warehouse warehouse, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(new CustomErrorType(bindingResult.getFieldErrors().toString()), HttpStatus.CONFLICT);
        }
        if (warehouseService.isWarehouseExist(warehouse)) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A Warehouse with id " +
                    warehouse.getId() + " already exist."),HttpStatus.CONFLICT);
        }
        warehouseService.save(warehouse);

        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/warehouse/{id}").buildAndExpand(warehouse.getId()).toUri());
        return new ResponseEntity<Warehouse>(warehouse, HttpStatus.CREATED);
    }

    // ------------------- Update a Warehouse ------------------------------------------------

    @RequestMapping(value = "/warehouse/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateWarehouse(@PathVariable("id") String id, @RequestBody @Valid Warehouse warehouse, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(new CustomErrorType(bindingResult.getFieldErrors().toString()), HttpStatus.CONFLICT);
        }
        Warehouse currentWarehouse = warehouseService.findById("warehouses/" + id);

        if (currentWarehouse == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Warehouse with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentWarehouse.setCity(warehouse.getCity());
        currentWarehouse.setHouse(warehouse.getHouse());
        currentWarehouse.setName(warehouse.getName());
        currentWarehouse.setStreet(warehouse.getStreet());

        warehouseService.updateWarehouse(currentWarehouse);

        return new ResponseEntity<Warehouse>(currentWarehouse, HttpStatus.OK);
    }

    // ------------------- Delete a Warehouse-----------------------------------------

    @RequestMapping(value = "/warehouse/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWarehouse(@PathVariable("id") String id) {

        Warehouse warehouse = warehouseService.findById("warehouses/" + id);
        if (warehouse == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Warehouse with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        warehouseService.deleteWarehouseById(id);
        return new ResponseEntity<Warehouse>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Orders-----------------------------

    @RequestMapping(value = "/warehouse/", method = RequestMethod.DELETE)
    public ResponseEntity<Warehouse> deleteAllWarehouses() {

        warehouseService.deleteAllWarehouses();
        return new ResponseEntity<Warehouse>(HttpStatus.NO_CONTENT);
    }
}
