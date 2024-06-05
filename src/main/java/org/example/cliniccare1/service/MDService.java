package org.example.cliniccare1.service;

import org.example.cliniccare1.model.Medical_Directory;
import org.example.cliniccare1.model.Medical_Directory;

import java.util.List;
import java.util.Optional;

public interface MDService {
    public List<Medical_Directory> getALLMedicalDirectories();


    //To save Medical Directory
  Medical_Directory saveMD(Long userId, Medical_Directory medicalDirectory);

    // to get clinic using id
    public Optional<Medical_Directory> getMDById(Long Id);

    // to update clinic id
    public Medical_Directory updateMD(Long id, Medical_Directory updatedMD);

    // to remove a clinic
    boolean removeMD(Long id);
}
