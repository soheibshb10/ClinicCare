package org.example.cliniccare1.service;

import org.example.cliniccare1.model.Medical_Visit_Appointment;

import java.util.List;

public interface MVAService {
    public List<Medical_Visit_Appointment> getAllMVA();

    //to save a company
    public boolean removeMVA(Long userId, Long mvaId);

    // to get clinic using id
    List<Medical_Visit_Appointment> getMVAById(Long userId);
    // to update clinic id
    Medical_Visit_Appointment updateMVA(Long userId, Medical_Visit_Appointment updatedMVA);

    // to save MVA
    public Medical_Visit_Appointment saveMVA(Long userId, Medical_Visit_Appointment updatedMVA) ;

   //Medical Visit Appointment Lis
    List<Medical_Visit_Appointment>getAllMedicalMVA();
}
