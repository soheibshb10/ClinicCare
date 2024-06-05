package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.example.cliniccare1.model.Company;
import org.example.cliniccare1.repository.CompanyRepository;
import org.example.cliniccare1.repository.ProfileRepository;
import org.example.cliniccare1.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional

public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Company> getALLCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        return companyRepository.save(updatedCompany);
    }

    @Override
    public void removeCompany(Long id) {
       companyRepository.deleteById(id);
    }
}
