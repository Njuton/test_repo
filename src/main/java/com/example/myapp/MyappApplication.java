package com.example.myapp;

import com.example.myapp.config.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@PropertySource(value = "file:${user.dir}/app.yaml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
@SpringBootApplication
public class MyappApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyappApplication.class, args);
    }
}
