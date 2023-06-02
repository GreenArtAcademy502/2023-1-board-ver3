package com.green.boardver3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class Boardver3Application {

    public static void main(String[] args) {
        SpringApplication.run(Boardver3Application.class, args);
    }

}
