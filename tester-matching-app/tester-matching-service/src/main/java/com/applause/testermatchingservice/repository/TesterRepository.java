package com.applause.testermatchingservice.repository;

import com.applause.testermatchingservice.model.Device;
import com.applause.testermatchingservice.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TesterRepository extends JpaRepository<Tester, Integer> {

    Set<Tester> getTestersByDevicesInAndAndCountryIn(Set<Device> devices, Set<String> countries);

}
