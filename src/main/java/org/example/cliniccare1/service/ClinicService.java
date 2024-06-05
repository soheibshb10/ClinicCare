package org.example.cliniccare1.service;


import org.example.cliniccare1.model.Clinic;

import java.util.List;

public interface ClinicService {

    //to get all clinics
    public List<Clinic> getALLClinics();

    //to save a clinic
    public Clinic saveClinic(Clinic clinic);

    // to get clinic using id
    public Clinic getClinicById(Long Id);

    // to update clinic id
    public Clinic updateClinic(Long id, Clinic updatedClinic);

    // to remove a clinic
    void removeClinic(Long id);
}
