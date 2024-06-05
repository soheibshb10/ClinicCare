package org.example.cliniccare1.service;

import org.example.cliniccare1.model.User;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    public List<User> getALLDoctors();

    //to save a company
    public User saveDoctor(User user);

    // to get clinic using id
    public Optional<User> getDoctorById(Long Id);
    // save doctor with role
    User saveDoctorWithRole(User doctor);

    // to update clinic id

    User updateDoctor(User updatedDoctor);

    // to remove a clinic
    void removeDoctor(Long id);
}
