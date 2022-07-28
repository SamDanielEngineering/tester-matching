package com.applause.testermatchingservice.controller;

import com.applause.testermatchingservice.dto.DeviceCountryDTO;
import com.applause.testermatchingservice.dto.TesterDTO;
import com.applause.testermatchingservice.model.Device;
import com.applause.testermatchingservice.service.DeviceService;
import com.applause.testermatchingservice.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/tester")
public class TesterController {

    private final TesterService testerService;
    private final DeviceService deviceService;

    @Autowired
    public TesterController(TesterService testerService, DeviceService deviceService) {
        this.testerService = testerService;
        this.deviceService = deviceService;
    }

    @GetMapping("/countries")
    public Mono<Set<String>> getCountries() {
        return Mono.just(testerService.getCountries());
    }

    @PostMapping
    public Flux<TesterDTO> getTesters(@RequestBody DeviceCountryDTO deviceCountryDTO) {
        Set<Device> devices = deviceService.getDevices(deviceCountryDTO.getDeviceIds());
        Set<String> countries = deviceCountryDTO.getCountries();
        return Flux.fromIterable(testerService.getTestersByCountryAndDevices(devices, countries));
    }
}
