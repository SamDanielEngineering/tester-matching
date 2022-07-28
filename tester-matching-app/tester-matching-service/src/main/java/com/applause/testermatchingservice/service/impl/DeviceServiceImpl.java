package com.applause.testermatchingservice.service.impl;

import com.applause.testermatchingservice.dto.DeviceDTO;
import com.applause.testermatchingservice.model.Device;
import com.applause.testermatchingservice.repository.DeviceRepository;
import com.applause.testermatchingservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Set<DeviceDTO> getDevices() {
        return deviceRepository.findAll()
                .parallelStream()
                .map(DeviceDTO::new)
                .sorted(Comparator.comparing(DeviceDTO::getDescription))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Device> getDevices(Set<Integer> deviceIds) {
        return new HashSet<>(deviceRepository.findAllById(deviceIds));
    }
}
