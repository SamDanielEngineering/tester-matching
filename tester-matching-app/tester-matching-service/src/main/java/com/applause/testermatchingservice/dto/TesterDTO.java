package com.applause.testermatchingservice.dto;

import com.applause.testermatchingservice.model.Tester;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TesterDTO {

    private int id;
    private String name;
    private String country;
    private Set<DeviceDTO> devices;
    private Integer bugCount;

    public TesterDTO(Tester tester, Set<DeviceDTO> devices, int bugCount) {
        this.id = tester.getId();
        this.name = tester.getFirstName() + " " + tester.getLastName();
        this.country = tester.getCountry();
        this.devices = devices;
        this.bugCount = bugCount;
    }
}
