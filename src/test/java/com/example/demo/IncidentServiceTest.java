package com.example.demo;

import com.example.demo.entities.Incident;
import com.example.demo.repositories.IncidentRepository;
import com.example.demo.services.IncidentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IncidentServiceTest {
    @Mock
    private IncidentRepository repository;

    @InjectMocks
    private IncidentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createIncident_ShouldThrowException_WhenTitleIsNotUnique() {
        // Arrange
        Incident incident = new Incident();
        incident.setTitle("Existing Incident");

        when(repository.findByTitle("Existing Incident")).thenReturn(Optional.of(incident));

        // Act and Assert
        Exception exception = assertThrows(Exception.class, () -> {
            service.createIncident(incident);
        });

        assertEquals("Incident title must be unique.", exception.getMessage());
        verify(repository, never()).save(any(Incident.class));
    }

    @Test
    void createIncident_ShouldSaveIncident_WhenTitleIsUnique() throws Exception {
        // Arrange
        Incident incident = new Incident();
        incident.setTitle("New Unique Incident");

        when(repository.findByTitle("New Unique Incident")).thenReturn(Optional.empty());
        when(repository.save(incident)).thenReturn(incident);

        // Act
        Incident savedIncident = service.createIncident(incident);

        // Assert
        assertEquals("New Unique Incident", savedIncident.getTitle());
        verify(repository, times(1)).save(incident);
    }

    @Test
    void getIncidentById_ShouldThrowException_WhenIncidentNotFound() {
        // Arrange
        Long incidentId = 1L;

        when(repository.findById(incidentId)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(Exception.class, () -> {
            service.getIncidentById(incidentId);
        });

        assertEquals("Incident not found.", exception.getMessage());
    }

}
