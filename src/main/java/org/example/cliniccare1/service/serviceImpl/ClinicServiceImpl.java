package org.example.cliniccare1.service.serviceImpl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.cliniccare1.model.Clinic;
import org.example.cliniccare1.model.Profile;
import org.example.cliniccare1.model.SecurityUser;
import org.example.cliniccare1.repository.ClinicRepository;
import org.example.cliniccare1.repository.ProfileRepository;
import org.example.cliniccare1.repository.SecurityUserRepository;
import org.example.cliniccare1.service.ClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional

public class ClinicServiceImpl implements ClinicService {


    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private SecurityUserRepository securityUserRepository;




    @Override
    public List<Clinic> getALLClinics() {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic saveClinic(Clinic clinic) {
        // Check if a clinic with the same email already exists
        List<Clinic> existingClinics = clinicRepository.findByEmail(clinic.getEmail());
        Clinic existingClinic = null;

        if (!existingClinics.isEmpty()) {
            // If clinic exists, use the first existing clinic found
            existingClinic = existingClinics.get(0);
        } else {
            // If clinic does not exist, use the new clinic
            existingClinic = clinic;
        }

        // Find the profile by role
        Profile profile = profileRepository.findByRole("CLINIC");

        if (profile == null) {
            throw new IllegalArgumentException("Role not found: " + "CLINIC");
        }

        // Add the profile to the clinic's profiles if not already added
        if (!existingClinic.getProfiles().contains(profile)) {
            existingClinic.getProfiles().add(profile);
        }

        // Map Clinic to SecurityUser and save
        SecurityUser securityUser = modelMapper.map(existingClinic, SecurityUser.class);
        securityUserRepository.save(securityUser);

        // Save the clinic entity
        existingClinic = clinicRepository.save(existingClinic);

        // Return the saved clinic entity
        return existingClinic;
    }






    @Override
    public Clinic getClinicById(Long Id) {
        Optional<Clinic>result= clinicRepository.findById(Id);
        Clinic clinic=null;

        if(result.isPresent()){
            clinic=result.get();
        }
        else{
            throw new RuntimeException("Did not find Employee id "+Id);
        }
        return clinic;    }

    @Override
    public Clinic updateClinic(Long id, Clinic updatedClinic) {
        return clinicRepository.save(updatedClinic);
    }

    @Override
    public void removeClinic(Long id) {
        Optional<Clinic> result= clinicRepository.findById(id);

        if(result.isPresent()){
            clinicRepository.deleteById(id);

        }
    }


}
