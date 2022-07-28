package com.applause.testermatchingservice.service.impl;

import com.applause.testermatchingservice.repository.TesterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataProcessor.class);

    private final DataConnector dataConnector;

    @Autowired
    TesterRepository testerRepository;

    @Autowired
    public DataProcessor(DataConnector dataConnector) {
        this.dataConnector = dataConnector;
    }

    public void initializeData() {
        LOGGER.info("Data loaded: {}", dataConnector.loadData());
    }
}
