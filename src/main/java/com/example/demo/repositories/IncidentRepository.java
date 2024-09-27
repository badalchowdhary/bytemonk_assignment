package com.example.demo.repositories;

import com.example.demo.entities.Incident;
import com.example.demo.entities.SeverityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Optional<Incident> findByTitle(String title);
    List<Incident> findBySeverity(SeverityLevel severity);
    List<Incident> findByIncidentDateBetween(LocalDate startDate, LocalDate endDate);
}
