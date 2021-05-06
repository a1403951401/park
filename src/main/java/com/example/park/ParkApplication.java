package com.example.park;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@ImportResource("classpath:applicationContext.xml")
public class ParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkApplication.class, args);
    }

}
