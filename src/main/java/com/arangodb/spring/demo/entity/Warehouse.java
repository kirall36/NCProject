package com.arangodb.spring.demo.entity;

import com.arangodb.springframework.annotation.Relations;
import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Document("warehouses")
public class Warehouse {

    @Id
    private String id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_]+$")
    private String name;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$")
    private String city;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_ ]+$")
    private String street;

    @NotNull
    @Pattern(regexp = "^[a-z/0-9_]+$")
    private String house;

    //@Relations(edges = Route.class, lazy = true)
    //private Collection<Route> routes;

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

    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getHouse() { return house; }

    public void setHouse(String house) { this.house = house; }

    public String getStreet() { return street;}

    public void setStreet(String street) { this.street = street; }

    public String getAdress(){ return "city=" + city + "street=" + street + "house=" + house; }

   // public Collection<Route> getRoutes() { return routes; }

    //public void setRoutes(Collection<Route> routes) { this.routes = routes; }
}