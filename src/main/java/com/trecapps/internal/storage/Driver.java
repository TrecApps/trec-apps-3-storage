package com.trecapps.internal.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Driver {

    static Logger logger = Logger.getLogger(Driver.class.toString());

    public static void main(String[] args)
    {
        logger.log(Level.INFO, "About to launch spring!");
        SpringApplication.run(Driver.class, args);
        logger.log(Level.INFO, "Launched spring!");
    }
}
