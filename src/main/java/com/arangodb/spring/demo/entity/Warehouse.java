package com.arangodb.spring.demo.entity;

import com.arangodb.springframework.annotation.Relations;
import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Document;

import java.util.Collection;

@Document("warehouses")
public class Warehouse {

    @Id
    private String id;

    private String name;
    private String city;
    private String street;
    private String house;

    @Relations(edges = Route.class, lazy = true)
    private Collection<Route> routes;

    public Warehouse() {
        super();
    }

    public Warehouse(final String name, final String city, final String street, final String house) {
        super();
        this.name = name;
        this.city = city;
        this.street = street;
        this.house = house;
    }


    // getter & setter

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", name=" + name + ", city=" + city + ", street=" + street + "house=" + house + "]";
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAdress(){
        return "city=" + city + "street=" + street + "house=" + house;
    }

    public Collection<Route> getRoutes() {
        return routes;
    }
}