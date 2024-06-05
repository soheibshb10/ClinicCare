package org.example.cliniccare1.controller;

import org.example.cliniccare1.model.User;
import org.example.cliniccare1.service.PatientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patients")

public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public List<User> getAllPatients() {
        return patientService.getALLPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getDoctorById(@PathVariable Long id) {
        System.out.println("User with id .....");
        Optional<User> doctor = patientService.getPayientById(id);
        if (doctor.isPresent())
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/new")
    public ResponseEntity<User> createPatient(@RequestBody User patient) {
        System.out.println("patient :"+patient);
        User createdDoctor = patientService.savePatient(patient);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updatePatient(@PathVariable Long id, @RequestBody User patient) {
        User updatedPatient = patientService.updatePatient(patient);
        if (updatedPatient != null)
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.removePatient(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
