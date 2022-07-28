package com.applause.testermatchingservice.dto;

import com.applause.testermatchingservice.model.Device;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceDTO {

    private int id;

    private String description;

    public DeviceDTO(Device device) {
        this.id = device.getId();
        this.description = device.getDescription();
    }
}
