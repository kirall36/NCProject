package com.arangodb.spring.demo.repository;

import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

public interface WarehouseRepository extends ArangoRepository<Warehouse> {

    Warehouse findByName(String Name);
    Warehouse findById(String Id);

    @Query("INSERT { name: @name, city: @city, street: @street, house: @house } in warehouses")
    void insertToRepository(@Param("name") String name, @Param("city") String city, @Param("street") String street, @Param("house") String house);

    @Query("LET doc = DOCUMENT(@key) UPDATE doc WITH { name: @name } IN warehouses")
    void updateName(@Param("key") String key,@Param("name") String name);

    @Query("LET doc = DOCUMENT(@key) UPDATE doc WITH { city: @city } IN warehouses")
    void updateCity(@Param("key") String key,@Param("city") String city);

    @Query("LET doc = DOCUMENT(@key) UPDATE doc WITH { street: @street } IN warehouses")
    void updateStreet(@Param("key") String key,@Param("street") String street);

    @Query("LET doc = DOCUMENT(@key) UPDATE doc WITH { house: @house } IN warehouses")
    void updateHouse(@Param("key") String key,@Param("house") String house);

    void removeByNameAndCity(String Name, String City);
}