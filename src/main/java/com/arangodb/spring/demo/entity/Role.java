package com.arangodb.spring.demo.entity;

import com.arangodb.springframework.annotation.Document;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Document("role")
public class Role {

    @Id
    private String id;

    @NotNull
    private String name;

    public Role()
    {
        super();
    }

    public Role(String name)
    {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name+ "]";
    }

    //getters & setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }


}
