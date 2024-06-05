package org.example.cliniccare1.service;

import org.example.cliniccare1.model.User;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    public List<User> getALLPatients();

    //to save a company
    public User savePatient(User user);

    // to get clinic using id
    public Optional<User> getPayientById(Long Id);

    // to update clinic id
    public User updatePatient(User updatedPatient);

    // to remove a clinic
    void removePatient(Long id);
    User savePatientWithRole(User patient, String role);


}
