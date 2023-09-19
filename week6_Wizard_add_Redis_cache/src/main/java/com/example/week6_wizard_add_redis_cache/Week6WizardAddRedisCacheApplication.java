package com.example.week6_wizard_add_redis_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Week6WizardAddRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week6WizardAddRedisCacheApplication.class, args);
    }

}
