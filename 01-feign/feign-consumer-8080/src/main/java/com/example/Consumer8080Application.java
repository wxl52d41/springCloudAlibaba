package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // 开启Feign功能
public class Consumer8080Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer8080Application.class, args);
    }

}
