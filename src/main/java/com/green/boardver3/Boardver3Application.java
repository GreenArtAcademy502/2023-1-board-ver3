package com.green.boardver3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class Boardver3Application {
    public static void main(String[] args) {
        SpringApplication.run(Boardver3Application.class, args);
    }
}
