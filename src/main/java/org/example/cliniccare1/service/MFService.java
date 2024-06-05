package org.example.cliniccare1.service;

import org.example.cliniccare1.model.Medical_File;
import org.example.cliniccare1.model.Medical_File;

import java.util.List;
import java.util.Optional;

public interface MFService {
    public List<Medical_File> getALLMedicalFiles();

    //to save a company
    public boolean removeMF(Long userId, Long fileId);

    // to get clinic using id
    public Optional<Medical_File> getMFById(Long Id);
    // to update clinic id
    public Medical_File updateMF(Long id, Medical_File updatedMF);

    Medical_File saveMF(Long userId, Medical_File medicalFile);

    List<Medical_File> getMedicalFilesByUserId(Long userId);
    List<Medical_File>getAllMedicalFiles();

}
