package com.arangodb.spring.demo.repository;

import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

public interface RouteRepository extends ArangoRepository<Route>{
    Route findByFromAndTo(String From, String To);
    Route findByName(String Name);
    Route findById(String Id);

    @Query("INSERT { name: @name, _from: @from, _to: @to, shipping_cost: @shipping_cost } in route OPTIONS { ignoreErrors: true }")
    void insertToRepository(@Param("name") String name, @Param("from") String from, @Param("to") String to, @Param("shipping_cost") Integer shipping_cost);

    @Query("LET doc = DOCUMENT(@key) UPDATE doc WITH { name: @name } IN route OPTIONS { ignoreErrors: true }")
    void updateName(@Param("key") String key,@Param("name") String name);

    @Query("LET doc = DOCUMENT(@key) UPDATE doc WITH { _from: @from } IN route OPTIONS { ignoreErrors: true }")
    void updateFrom(@Param("key") String key,@Param("from") String from);

    @Query("LET doc = DOCUMENT(@key) UPDATE doc WITH { _to: @to } IN route OPTIONS { ignoreErrors: true }")
    void updateTo(@Param("key") String key,@Param("to") String to);

    @Query("LET doc = DOCUMENT(@key) UPDATE doc WITH { shipping_cost: @shipping_cost } IN route OPTIONS { ignoreErrors: true }")
    void updateShippingCost(@Param("key") String key,@Param("shipping_cost") Integer shipping_cost);

    void removeByName(String Name);
}
