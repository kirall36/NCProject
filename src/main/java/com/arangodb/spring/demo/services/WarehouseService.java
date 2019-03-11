package com.arangodb.spring.demo.services;

import com.arangodb.spring.demo.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService{
    Optional<Warehouse> save(Warehouse warehouse);
    List<Warehouse> findAllWarehouses();
    Warehouse findById(String Id);
    Boolean isWarehouseExist(Warehouse warehouse);
    void updateWarehouse(Warehouse warehouse);
    void deleteWarehouseById(String Id);
    void deleteAllWarehouses();
}
