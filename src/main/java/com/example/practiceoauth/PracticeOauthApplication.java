package com.example.practiceoauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
@SpringBootApplication
public class PracticeOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeOauthApplication.class, args);
    }

}
