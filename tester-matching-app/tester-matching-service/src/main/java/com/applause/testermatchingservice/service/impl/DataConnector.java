package com.applause.testermatchingservice.service.impl;

import com.applause.testermatchingservice.model.Bug;
import com.applause.testermatchingservice.model.Device;
import com.applause.testermatchingservice.model.Tester;
import com.applause.testermatchingservice.repository.BugRepository;
import com.applause.testermatchingservice.repository.DeviceRepository;
import com.applause.testermatchingservice.repository.TesterRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DataConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataConnector.class);

    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;
    private final BugRepository bugRepository;

    @Autowired
    public DataConnector(TesterRepository testerRepository, DeviceRepository deviceRepository, BugRepository bugRepository) {
        this.testerRepository = testerRepository;
        this.deviceRepository = deviceRepository;
        this.bugRepository = bugRepository;
    }


    public boolean loadData() {
        LOGGER.info("Testers loaded: {}", loadTesters());
        LOGGER.info("Devices loaded: {}", loadDevices());
        LOGGER.info("Bugs loaded: {}", loadBugs());
        LOGGER.info("Testers devices loaded: {}", loadTestersDevices());

        return true;
    }

    private boolean loadTesters() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/testers.csv")))) {

            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true)
                    .build());

            List<Tester> testers = csvParser.getRecords().parallelStream()
                    .map(csvRecord -> {
                        int id = Integer.parseInt(csvRecord.get("testerId"));
                        String firstName = csvRecord.get("firstName");
                        String lastName = csvRecord.get("lastName");
                        String country = csvRecord.get("country");
                        String lastLogin = csvRecord.get("lastLogin");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        return new Tester(id, firstName, lastName, country, LocalDateTime.parse(lastLogin, formatter));
                    }).toList();

            testerRepository.saveAll(testers);

            return true;
        } catch (Exception e) {
            LOGGER.error("Unable to extract tester CSV data.", e);
            return false;
        }
    }

    private boolean loadDevices() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/devices.csv")))) {

            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true)
                    .build());

            List<Device> devices = csvParser.getRecords().parallelStream()
                    .map(csvRecord -> {
                        int id = Integer.parseInt(csvRecord.get("deviceId"));
                        String description = csvRecord.get("description");

                        return new Device(id, description);
                    }).toList();

            deviceRepository.saveAll(devices);

            return true;
        } catch (Exception e) {
            LOGGER.error("Unable to extract device CSV data.", e);
            return false;
        }
    }

    private boolean loadBugs() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/bugs.csv")))) {

            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true)
                    .build());

            csvParser.getRecords()
                    .forEach(csvRecord -> {
                        int id = Integer.parseInt(csvRecord.get("bugId"));
                        int deviceId = Integer.parseInt(csvRecord.get("deviceId"));
                        int testerId = Integer.parseInt(csvRecord.get("testerId"));

                        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
                        Optional<Tester> optionalTester = testerRepository.findById(testerId);

                        if (optionalTester.isPresent() && optionalDevice.isPresent()) {
                            Device device = optionalDevice.get();
                            Tester tester = optionalTester.get();

                            bugRepository.save(new Bug(id, device, tester));
                        }
                    });

            return true;
        } catch (Exception e) {
            LOGGER.error("Unable to extract bug CSV data.", e);
            return false;
        }
    }

    private boolean loadTestersDevices() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/tester_device.csv")))) {
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true)
                    .build());

            csvParser.getRecords()
                    .forEach(csvRecord -> {
                        int testerId = Integer.parseInt(csvRecord.get("testerId"));
                        int deviceId = Integer.parseInt(csvRecord.get("deviceId"));
                        Optional<Tester> optionalTester = testerRepository.findById(testerId);
                        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);

                        if (optionalTester.isPresent() && optionalDevice.isPresent()) {
                            Tester tester = optionalTester.get();
                            Device device = optionalDevice.get();

                            Set<Device> devices = tester.getDevices();
                            devices.add(device);
                            tester.setDevices(devices);
                            testerRepository.save(tester);
                        }
                    });

            return true;
        } catch (Exception e) {
            LOGGER.error("Unable to extract testers devices CSV data.", e);
            return false;
        }
    }
}
