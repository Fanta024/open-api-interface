package com.fanta.testinterface;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
public class TestInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestInterfaceApplication.class, args);
    }

}
