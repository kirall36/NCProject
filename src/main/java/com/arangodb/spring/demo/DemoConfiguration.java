package com.arangodb.spring.demo;

import org.springframework.context.annotation.Configuration;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;

@Configuration
@EnableArangoRepositories(basePackages = { "com.arangodb.spring.demo" })
public class DemoConfiguration extends AbstractArangoConfiguration {
    @Override
    public Builder arango() {
        return new ArangoDB.Builder().host("localhost", 8529).user("root").password("root");
    }

    @Override
    public String database() {
        return "shipment";
    }
}