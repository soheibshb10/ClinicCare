package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.cliniccare1.model.Medical_Directory;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.Medical_DirectoryRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.MDService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional

public class MDServiceImpl implements MDService {
    @Autowired

    private Medical_DirectoryRepository medicalDirectoryRepository;
    @Autowired

    private UserRepository userRepository;
    @Autowired

    private ModelMapper modelMapper;




    //To get all Medical Directories
    @Override
    public List<Medical_Directory> getALLMedicalDirectories() {
        return medicalDirectoryRepository.findAll();
    }




    //To save Medical Directory
    @Override
    public Medical_Directory saveMD(Long userId, Medical_Directory medicalDirectory) {
        // Check if user exists
            User user=userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        if (!medicalDirectory.getUser().getId().equals(userId))
            throw new ResourceNotFoundException("Medical Directory with id " + medicalDirectory.getMD_id() + " does not belong to user with id " + userId);



        // Associate the user with the medical directory
        medicalDirectory.setUser(user);

        // Save the medical directory
        return medicalDirectoryRepository.save(medicalDirectory);
    }


    //To get Medical Directory with id
    @Override
    public Optional<Medical_Directory> getMDById(Long Id) {
        return medicalDirectoryRepository.findById(Id);
    }

    //To update Medical Directory
    @Override
    public Medical_Directory updateMD(Long id, Medical_Directory updatedMD) {
        Optional<Medical_Directory> optionalMD = medicalDirectoryRepository.findById(id);

        if (optionalMD.isPresent()) {
            Medical_Directory medicalDirectory = optionalMD.get();

            // Map properties from updatedMF to existing medical file using ModelMapper
            modelMapper.map(optionalMD, medicalDirectory);

            // Save the updated medical file
            return medicalDirectoryRepository.save(medicalDirectory);
        } else
            throw new ResourceNotFoundException("Medical Directory with id " + id + " not found");    }


    //To delete Medical Directory
    @Override
    public boolean removeMD(Long id) {
        if (medicalDirectoryRepository.existsById(id))
            medicalDirectoryRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("Medical Directory with id " + id + " not found");
        return false;
    }





}
