package org.example.cliniccare1.service;

import org.example.cliniccare1.model.Company;

import java.util.List;
import java.util.Optional;


public interface CompanyService {
    //to get all companies
    public List<Company> getALLCompanies();

    //to save a company
    public Company saveCompany(Company company);

    // to get clinic using id
    public Optional<Company> getCompanyById(Long Id);

    // to update clinic id
    public Company updateCompany(Long id, Company updatedCompany);

    // to remove a clinic
    void removeCompany(Long id);



}
