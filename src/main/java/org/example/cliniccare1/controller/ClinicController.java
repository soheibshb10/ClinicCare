package org.example.cliniccare1.controller;

import org.example.cliniccare1.model.Clinic;
import org.example.cliniccare1.service.ClinicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")

public class ClinicController {

    private ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }


    @GetMapping("/clinic")
    public ResponseEntity<Clinic> authenticatedClinic() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Clinic currentClinic = (Clinic) authentication.getPrincipal();

        return ResponseEntity.ok(currentClinic);
    }
    @GetMapping("/all")
    public List<Clinic> getAllClinics() {
        return clinicService.getALLClinics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable Long id) {
        Clinic clinic = clinicService.getClinicById(id);
        if (clinic != null)
            return new ResponseEntity<>(clinic, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/new")
    public ResponseEntity<Clinic> createClinic(@RequestBody Clinic clinic) {
        System.out.println("clinic :"+clinic);
        Clinic createdClinic = clinicService.saveClinic(clinic);
        return new ResponseEntity<>(createdClinic, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Clinic> updateClinic(@PathVariable Long id, @RequestBody Clinic clinic) {
        Clinic updatedClinic = clinicService.updateClinic(id, clinic);
        if (updatedClinic != null)
            return new ResponseEntity<>(updatedClinic, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        clinicService.removeClinic(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
