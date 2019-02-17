package com.arangodb.spring.demo.repository;

import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.springframework.repository.ArangoRepository;

public interface WarehouseRepository extends ArangoRepository<Warehouse> {

    Warehouse findByName(String Name);

    void removeByNameAndCity(String Name, String City);
}