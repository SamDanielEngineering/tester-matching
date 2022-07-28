package com.applause.testermatchingservice.service;

import com.applause.testermatchingservice.dto.TesterDTO;
import com.applause.testermatchingservice.model.Device;
import com.applause.testermatchingservice.model.Tester;

import java.util.Set;

public interface TesterService {

    Set<String> getCountries();

    Set<Tester> getTestersByDevices(Set<Device> devices);

    Set<Tester> getTestersByCountry(Set<String> countries);

    Set<TesterDTO> getTestersByCountryAndDevices(Set<Device> devices, Set<String> countries);
}
