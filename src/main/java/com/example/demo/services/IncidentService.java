package com.example.demo.services;

import com.example.demo.entities.Incident;
import com.example.demo.entities.SeverityLevel;
import com.example.demo.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncidentService {
    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public Incident createIncident(Incident incident) throws Exception {
        if (repository.findByTitle(incident.getTitle()).isPresent()) {
            throw new Exception("Incident title must be unique.");
        } else if (incident.getTitle().length() < 10) {
            throw new Exception("Incidnet title must be 10 characters long.");
        }
        validateDate(incident.getIncidentDate());
        validateSeverity(incident.getSeverity());
        return repository.save(incident);
    }

    public Incident updateIncident(Long id, Incident updatedIncident) throws Exception {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new Exception("Incident not found."));
        validateDate(updatedIncident.getIncidentDate());
        validateSeverity(updatedIncident.getSeverity());
        incident.setStatus(updatedIncident.getStatus());
        incident.setDescription(updatedIncident.getDescription());
        return repository.save(incident);
    }

    private void validateDate(LocalDate date) throws Exception {
        if (date.isAfter(LocalDate.now()) || date.isBefore(LocalDate.now().minusDays(30))) {
            throw new Exception("Incident cannot be created for a past date greater than 30 days or a future date.");
        }
    }

    private void validateSeverity(SeverityLevel severity) throws Exception {
        if (severity == null) {
            throw new Exception("Severity level is invalid.");
        }
    }

    public List<Incident> listAllIncidents(LocalDate startDate, LocalDate endDate, SeverityLevel severity) {
        if (startDate != null && endDate != null) {
            return repository.findByIncidentDateBetween(startDate, endDate);
        } else if (severity != null) {
            return repository.findBySeverity(severity);
        } else {
            return repository.findAll();
        }
    }

    public Incident getIncident(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Incident not found."));
    }
}
