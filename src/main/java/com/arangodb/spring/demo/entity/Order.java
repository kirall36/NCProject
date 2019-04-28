package com.arangodb.spring.demo.entity;

import com.arangodb.spring.demo.services.RouteService;
import com.arangodb.springframework.annotation.Document;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Collection;

@Document("order")
public class Order {

    @Id
    private String id;

    @NotNull
    @Min(value = 0)
    private Integer weight;

    @Past
    @NotNull
    private LocalDate date_of_creation;

    @Future
    @NotNull
    private LocalDate date_of_filling; //complition date

    @NotNull
    private boolean status;

    @NotNull
    private Warehouse from;

    @NotNull
    private Warehouse to;

    @NotNull
    @Min(value = 0)
    private Integer price;

    private Collection<Route> path;


    public Order() {
        super();
    }

    public Order(final Integer weight, final LocalDate date_of_creation, final LocalDate date_of_filling, final boolean status, final Integer price, final Warehouse from, final Warehouse to) {
        super();
        this.weight = weight;
        this.date_of_creation = date_of_creation;
        this.date_of_filling = date_of_filling;
        this.status = status;
        this.price = price;
        this.from = from;
        this.to = to;
        this.path = null;
    }


    public Order(final Integer weight, final LocalDate date_of_creation, final LocalDate date_of_filling, final boolean status, final Integer price, final Warehouse from, final Warehouse to, final Collection<Route> path) {
        super();
        this.weight = weight;
        this.date_of_creation = date_of_creation;
        this.date_of_filling = date_of_filling;
        this.status = status;
        this.price = price;
        this.from = from;
        this.to = to;
        this.path = path;
    }

    // getter & setter

    @Override
    public String toString() {
        return "Order [id=" + id + ", weight=" + weight + ", date_of_creation=" + date_of_creation + ", date_of_filling=" + date_of_filling +
                ", status=" + status + ", from=" + from.getId() + ", to=" + to.getId()
                + ", price=" + price + "]";
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

    public Warehouse getFrom() {
        return from;
    }

    public void setFrom(Warehouse from) {
        this.from = from;
    }

    public Warehouse getTo() {
        return to;
    }

    public void setTo(Warehouse to) {
        this.to = to;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Collection<Route> getPath() { return path; }

    public void setPath(Collection<Route> path) {
        this.path = path;
    }
}
