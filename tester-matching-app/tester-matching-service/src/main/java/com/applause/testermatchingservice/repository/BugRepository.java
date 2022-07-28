package com.applause.testermatchingservice.repository;

import com.applause.testermatchingservice.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Integer> {
}
