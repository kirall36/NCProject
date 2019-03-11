package com.arangodb.spring.demo.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;

@Configuration
@EnableArangoRepositories(basePackages = { "com.arangodb.spring.demo" })
public class DemoConfiguration extends AbstractArangoConfiguration {
    @Value("${spring.datasource.host}")
    private String host;

    @Value("${spring.datasource.port}")
    private Integer port;

    @Value("${spring.datasource.user}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.database}")
    private String database;

    @Override
    public Builder arango() {
        return new ArangoDB.Builder().host(host, port).user(user).password(password);
    }

    @Override
    public String database() {
        return database;
    }
}