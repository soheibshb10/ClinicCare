package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.cliniccare1.model.Medical_File;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.Medical_FileRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.MFService;
import org.example.cliniccare1.model.Medical_File;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional

public class MFServiceImpl implements MFService {
    @Autowired
    private Medical_FileRepository medicalFileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    //To get all Medical Files
    @Override
    public List<Medical_File> getALLMedicalFiles() {
        return medicalFileRepository.findAll();
    }

    //To save Medical File
    public Medical_File saveMF(Long userId, Medical_File updatedMF) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        // Check if medical file exists and belongs to the user
        Medical_File existingMedicalFile = medicalFileRepository.findById(updatedMF.getId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicalFile with id " + updatedMF.getId() + " not found"));

        if (!existingMedicalFile.getUser().getId().equals(userId))
            throw new ResourceNotFoundException("MedicalFile with id " + existingMedicalFile.getId() + " does not belong to user with id " + userId);


        // Map properties from updatedMF to existing medical file using ModelMapper
        modelMapper.map(updatedMF, existingMedicalFile);

        // Save the updated medical file
        return medicalFileRepository.save(existingMedicalFile);
    }


    //To get Medical File By Medical File Id
    @Override
    public Optional<Medical_File> getMFById(Long Id) {
        return medicalFileRepository.findById(Id);
    }


    @Override

    //To update Medical File
    public Medical_File updateMF(Long userId,  Medical_File updatedMF) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        // Check if medical file exists and belongs to the user
        Medical_File medicalFile = medicalFileRepository.findById(updatedMF.getId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicalFile with id " + updatedMF.getId() + " not found"));

        if (!medicalFile.getUser().getId().equals(userId))
            throw new ResourceNotFoundException("MedicalFile with id " + updatedMF.getId() + " does not belong to user with id " + userId);


        // Map properties from updatedMF to existing medical file using ModelMapper
        modelMapper.map(updatedMF, medicalFile);

        // Save the updated medical file
        return medicalFileRepository.save(medicalFile);
    }

    //To remove Medical File
    @Override
    public boolean removeMF(Long userId, Long fileId) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User with id " + userId + " not found");


        Medical_File medicalFile = medicalFileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical_File with id " + fileId + " not found"));

        if (!medicalFile.getUser().getId().equals(userId))
            throw new ResourceNotFoundException("Medical_File with id " + fileId + " does not belong to user with id " + userId);


        medicalFileRepository.deleteById(fileId);
        return true;
    }

    // get Medical Files By user ID
    public List<Medical_File> getMedicalFilesByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getMedicalFiles)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    // get List of Medical Files
    public List<Medical_File>getAllMedicalFiles(){
        return medicalFileRepository.findAll();

    }


}
