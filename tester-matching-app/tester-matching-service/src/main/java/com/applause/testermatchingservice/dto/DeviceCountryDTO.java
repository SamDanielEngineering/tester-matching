package com.applause.testermatchingservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DeviceCountryDTO {

    private final Set<Integer> deviceIds;

    private final Set<String> countries;

}
