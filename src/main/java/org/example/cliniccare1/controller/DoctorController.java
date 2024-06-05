package org.example.cliniccare1.controller;



import org.example.cliniccare1.model.User;
import org.example.cliniccare1.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctors")

public class DoctorController {
    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService=doctorService;
    }

    @GetMapping("/all")
    public List<User> getAllDoctors() {
        return doctorService.getALLDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getDoctorById(@PathVariable Long id) {
        Optional<User> doctor = doctorService.getDoctorById(id);
        if (doctor.isPresent())
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
         else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/new")
    public ResponseEntity<User> createDoctor(@RequestBody User doctor) {
        System.out.println("company :"+doctor);
        User createdDoctor = doctorService.saveDoctorWithRole(doctor);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/update/{role}")
    public ResponseEntity<User> updateDoctor(@PathVariable String role, @RequestBody User doctor) {
        User updatedDoctor = doctorService.updateDoctor(doctor);
        if (updatedDoctor != null)
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.removeDoctor(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
