package com.applause.testermatchingservice.service;

import com.applause.testermatchingservice.dto.DeviceDTO;
import com.applause.testermatchingservice.model.Device;

import java.util.Set;

public interface DeviceService {

    Set<DeviceDTO> getDevices();

    Set<Device> getDevices(Set<Integer> deviceIds);

}
