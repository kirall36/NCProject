package com.arangodb.spring.demo.application;

import com.arangodb.spring.demo.runner.CrudRunner;
import com.arangodb.spring.demo.runner.OrderRunner;
import com.arangodb.spring.demo.runner.RelationsRunner;
import com.arangodb.spring.demo.runner.UserRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(scanBasePackages={"com.arangodb.spring.demo"})
@EnableWebMvc
public class DemoApplication {
    public static void main(final String... args) {
        //Object[] runner = new Object[] { OrderRunner.class};
        //System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
        SpringApplication.run(DemoApplication.class, args);
    }
}