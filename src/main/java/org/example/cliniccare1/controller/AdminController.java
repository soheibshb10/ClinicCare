package org.example.cliniccare1.controller;


import org.example.cliniccare1.model.User;
import org.example.cliniccare1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admins")

public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public List<User> getAllAdmins() {
        return adminService.getALLAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getAdminById(@PathVariable Long id) {
        Optional<User> user = adminService.getAdminById(id);
        if (user.isPresent())
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
         else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/new")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        System.out.println("Admin :" + user);
        User createdAdmin = adminService.saveAdmin(user);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateAdmin(@PathVariable Long id, @RequestBody User user) {
        User updatedAdmin = adminService.updateAdmin(id, user);
        if (updatedAdmin != null)
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        adminService.removeAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
