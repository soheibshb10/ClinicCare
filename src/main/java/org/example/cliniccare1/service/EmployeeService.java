package org.example.cliniccare1.service;


import org.example.cliniccare1.model.User;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<User> getALLEmployees();

    //to save a company
    public User saveEmployee(User user);

    // to get clinic using id
    public Optional<User> getEmployeeById(Long Id);

    // to update clinic id
    public User updateEmployee(Long id, User updatedEmployee);

    // to remove a clinic
    void removeEmployee(Long id);
    User saveEmployeeWithRole(User employee);
}
