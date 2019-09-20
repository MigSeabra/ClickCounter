package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Application
 * Class containing main method
 */
@SpringBootApplication
public class Application {

    /**
     * Spring Main Method
     * Allows to run the Spring Application
     * @param args User inputs
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
