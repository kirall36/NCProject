package com.arangodb.spring.demo;

import com.arangodb.spring.demo.runner.CrudRunner;
import com.arangodb.spring.demo.runner.RelationsRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(final String... args) {
        Object[] runner = new Object[] { CrudRunner.class};
        System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
    }
}