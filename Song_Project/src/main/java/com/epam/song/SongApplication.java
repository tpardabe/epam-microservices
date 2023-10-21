package com.epam.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SongApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongApplication.class);
    }
}
