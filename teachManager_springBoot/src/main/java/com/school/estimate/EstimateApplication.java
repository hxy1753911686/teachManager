package com.school.estimate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.school.estimate.dao")

public class EstimateApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(EstimateApplication.class);
    }
}
/*
public class EstimateApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstimateApplication.class, args);
    }

}*/
