package com.applause.testermatchingservice.controller;

import com.applause.testermatchingservice.dto.DeviceDTO;
import com.applause.testermatchingservice.service.DeviceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin
@RequestMapping("api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public Flux<DeviceDTO> getDevices() {
        return Flux.fromIterable(deviceService.getDevices());
    }
}
