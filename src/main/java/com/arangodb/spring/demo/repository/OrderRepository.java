package com.arangodb.spring.demo.repository;

import com.arangodb.spring.demo.entity.Order;
import com.arangodb.spring.demo.entity.Route;
import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface OrderRepository extends ArangoRepository<Order>{

    Order findById(String Id);

    @Query("INSERT { weight: @weight, date_of_creation: @date_of_creation, date_of_filling: @date_of_filling, price: @price, path: @path } in order")
    void insertToRepository(@Param("weight") Integer weight, @Param("date_of_creation") LocalDate date_of_creation , @Param("date_of_filling") LocalDate date_of_filling, @Param("price") Integer price, @Param("path")Collection<Route> path);

    void removeById(String Id);
}