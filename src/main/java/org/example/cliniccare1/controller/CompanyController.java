package org.example.cliniccare1.controller;



import org.example.cliniccare1.model.Company;
import org.example.cliniccare1.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/companies")

public class CompanyController {


    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getALLCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Company> createClinic(@RequestBody Company company) {
        System.out.println("company :"+company);
        Company createdCompany = companyService.saveCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Company> updateClinic(@PathVariable Long id, @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(id, company);
        if (updatedCompany != null)
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        companyService.removeCompany(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }





}
