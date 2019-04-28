package com.arangodb.spring.demo.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Edge
public class Route {

    @Id
    private String id;

    @From
    @NotNull
    private Warehouse from;

    @To
    @NotNull
    private Warehouse to;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_]+$")
    private String name;

    @NotNull
    @Min(value = 0)
    private Integer shipping_cost;

    public Route()
    {
        super();
    }

    public Route(final Warehouse from, final Warehouse to, final String name, final Integer shipping_cost) {
        super();
        this.from = from;
        this.to = to;
        this.name = name;
        this.shipping_cost = shipping_cost;
    }


    @Override
    public String toString() {
        return "Route [id=" + id + ", from=" + from + ", to=" + to + ", name=" + name + ", shipping_cost=" + shipping_cost + "]";
    }

    public String getId(){
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(Integer shipping_cost) {
        this.shipping_cost = shipping_cost;
    }
}