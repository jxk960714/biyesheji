package com.jxk.sqmy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jxk.sqmy.dao")
public class SqmyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqmyApplication.class, args);
    }

}
