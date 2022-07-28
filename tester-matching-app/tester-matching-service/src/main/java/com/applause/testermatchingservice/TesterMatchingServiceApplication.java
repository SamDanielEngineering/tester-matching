package com.applause.testermatchingservice;

import com.applause.testermatchingservice.service.impl.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TesterMatchingServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesterMatchingServiceApplication.class);

    private final DataProcessor dataProcessor;

    @Autowired
    public TesterMatchingServiceApplication(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(TesterMatchingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialize() {
        return args -> dataProcessor.initializeData();
    }
}
