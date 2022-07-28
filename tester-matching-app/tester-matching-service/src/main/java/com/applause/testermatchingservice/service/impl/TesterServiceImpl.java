package com.applause.testermatchingservice.service.impl;

import com.applause.testermatchingservice.dto.DeviceDTO;
import com.applause.testermatchingservice.dto.TesterDTO;
import com.applause.testermatchingservice.model.Device;
import com.applause.testermatchingservice.model.Tester;
import com.applause.testermatchingservice.repository.TesterRepository;
import com.applause.testermatchingservice.service.TesterService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TesterServiceImpl implements TesterService {

    private final TesterRepository testerRepository;

    public TesterServiceImpl(TesterRepository testerRepository) {
        this.testerRepository = testerRepository;
    }

    @Override
    public Set<String> getCountries() {
        return testerRepository.findAll()
                .parallelStream()
                .map(Tester::getCountry)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Tester> getTestersByDevices(Set<Device> devices) {
        return testerRepository.findAll()
                .parallelStream()
                .filter(tester -> tester.getDevices().containsAll(devices))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Tester> getTestersByCountry(Set<String> countries) {
        return testerRepository.findAll()
                .parallelStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TesterDTO> getTestersByCountryAndDevices(Set<Device> devices, Set<String> countries) {
        return testerRepository.getTestersByDevicesInAndAndCountryIn(devices, countries)
                .parallelStream()
                .map(tester -> {
                    int bugCount = tester.getBugs()
                            .parallelStream()
                            .filter(bug -> devices.contains(bug.getDevice()))
                            .collect(Collectors.toSet())
                            .size();

                    Set<DeviceDTO> filteredDevices = tester.getDevices()
                            .parallelStream()
                            .filter(devices::contains)
                            .map(DeviceDTO::new)
                            .collect(Collectors.toSet());

                    return new TesterDTO(tester, filteredDevices, bugCount);
                })
                .sorted(Comparator.comparing(TesterDTO::getBugCount).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
