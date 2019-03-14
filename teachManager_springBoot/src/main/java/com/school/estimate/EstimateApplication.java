package com.school.estimate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.school.estimate.dao")
public class EstimateApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstimateApplication.class, args);
    }

}
