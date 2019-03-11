package com.arangodb.spring.demo.entity;

import com.arangodb.springframework.annotation.Document;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Collection;

@Document("order")
public class Order {

    @Id
    private String id;

    private Integer weight;
    private LocalDate date_of_creation;
    private LocalDate date_of_filling;
    private Integer price;
    private Collection<Route> path;


    public Order() {
        super();
    }

    public Order(final Integer weight, final LocalDate date_of_creation, final LocalDate date_of_filling, final Integer price) {
        super();
        this.weight = weight;
        this.date_of_creation = date_of_creation;
        this.date_of_filling = date_of_filling;
        this.price = price;
    }


    public Order(final Integer weight, final LocalDate date_of_creation, final LocalDate date_of_filling, final Integer price, final Collection<Route> path) {
        super();
        this.weight = weight;
        this.date_of_creation = date_of_creation;
        this.date_of_filling = date_of_filling;
        this.price = price;
        this.path = path;
    }

    // getter & setter

    @Override
    public String toString() {
        return "Order [id=" + id + ", weight=" + weight + ", date_of_creation=" + date_of_creation + ", date_of_filling=" + date_of_filling
                + ", price=" + price + ", path=" + path.toString()+ "]";
    }

    public String getId(){
        return id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDate getDate_of_creation(){
        return date_of_creation;
    }

    public void setDate_of_creation(LocalDate date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public LocalDate getDate_of_filling(){
        return date_of_filling;
    }

    public void setDate_of_filling(LocalDate date_of_filling) {
        this.date_of_filling = date_of_filling;
    }

    public Integer getPrice(){
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Collection<Route> getPath() { return path; }

    public void setPath(Collection<Route> path) {
        this.path = path;
    }
}
