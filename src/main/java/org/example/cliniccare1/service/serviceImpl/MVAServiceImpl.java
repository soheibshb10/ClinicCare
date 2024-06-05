package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.cliniccare1.model.Medical_Visit_Appointment;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.Medical_Visit_AppointmentRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.MVAService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional


public class MVAServiceImpl implements MVAService {

    @Autowired

    private Medical_Visit_AppointmentRepository medicalVisitAppointmentRepository;
    @Autowired

    private UserRepository userRepository;
    @Autowired

    private ModelMapper modelMapper;
    @Override
    public List<Medical_Visit_Appointment> getAllMVA() {
        return medicalVisitAppointmentRepository.findAll();
    }

    @Override
    public boolean removeMVA(Long userId, Long mvaId) {
        // Retrieve the medical visit appointment by its ID
        Optional<Medical_Visit_Appointment> optionalMVA = medicalVisitAppointmentRepository.findById(mvaId);

        // Check if the MVA exists and belongs to the specified user
        if (optionalMVA.isPresent()) {
            Medical_Visit_Appointment mva = optionalMVA.get();
            if (mva.getUser().getId().equals(userId)) {
                // Delete the MVA since it belongs to the specified user
                medicalVisitAppointmentRepository.delete(mva);
            } else {
                // MVA exists but does not belong to the specified user
                throw new ResourceNotFoundException("Medical Visit Appointment with ID " + mvaId + " does not belong to user with ID " + userId);
            }
        } else {
            // MVA with the given ID not found
            throw new ResourceNotFoundException("Medical Visit Appointment with ID " + mvaId + " not found");
        }
        return false;
    }

    @Override
    public List<Medical_Visit_Appointment> getMVAById(Long userId) {
          return userRepository.findById(userId)
                .map(User::getMedicalVisitAppointments)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }



    @Override
    public Medical_Visit_Appointment updateMVA(Long userId, Medical_Visit_Appointment updatedMVA) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        // Check if medical file exists and belongs to the user
            Medical_Visit_Appointment mva = medicalVisitAppointmentRepository.findById(updatedMVA.getId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicalFile with id " + updatedMVA.getId() + " not found"));

        if (!mva.getUser().getId().equals(userId))
            throw new ResourceNotFoundException("MedicalFile with id " + updatedMVA.getId() + " does not belong to user with id " + userId);


        // Map properties from updatedMF to existing medical file using ModelMapper
        modelMapper.map(updatedMVA, mva);

        // Save the updated medical file
        return medicalVisitAppointmentRepository.save(mva);    }


    @Override
    public Medical_Visit_Appointment saveMVA(Long userId, Medical_Visit_Appointment updatedMVA) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        // Check if medical file exists and belongs to the user
        Medical_Visit_Appointment existingMVA = medicalVisitAppointmentRepository.findById(updatedMVA.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Medical Visit Appointment  with id " + updatedMVA.getId() + " not found"));

        if (!existingMVA.getUser().getId().equals(userId))
            throw new ResourceNotFoundException("MedicalFile with id " + existingMVA.getId() + " does not belong to user with id " + userId);


        // Map properties from updatedMF to existing medical file using ModelMapper
        modelMapper.map(updatedMVA, existingMVA);

        // Save the updated medical file
        return medicalVisitAppointmentRepository.save(existingMVA);
    }

    public List<Medical_Visit_Appointment>getAllMedicalMVA(){
        return medicalVisitAppointmentRepository.findAll();
    }
}
