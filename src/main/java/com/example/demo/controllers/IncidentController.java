package com.example.demo.controllers;

import com.example.demo.entities.Incident;
import com.example.demo.entities.SeverityLevel;
import com.example.demo.services.IncidentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/incidents")
public class IncidentController {
    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    //Create new Incident
    @PostMapping
    public Incident createIncident(@RequestBody Incident incident) throws Exception {
        return service.createIncident(incident);
    }

    //Update Incident
    @PutMapping("/{id}")
    public Incident updateIncident(@PathVariable Long id, @RequestBody Incident incident) throws Exception {
        return service.updateIncident(id, incident);
    }

    //get All Incidents
    @GetMapping
    public List<Incident> listIncidents(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) SeverityLevel severity) {
        return service.listAllIncidents(startDate, endDate, severity);
    }

    //Get specific id incident
    @GetMapping("/{id}")
    public Incident getIncident(@PathVariable Long id) throws Exception {
        return service.getIncident(id);
    }
}
