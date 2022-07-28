package com.applause.testermatchingservice.repository;

import com.applause.testermatchingservice.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
