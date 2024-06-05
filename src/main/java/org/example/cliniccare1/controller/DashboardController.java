package org.example.cliniccare1.controller;

import org.apache.kafka.common.errors.ResourceNotFoundException;

import org.example.cliniccare1.model.Medical_Directory;
import org.example.cliniccare1.model.Medical_File;
import org.example.cliniccare1.model.Profile;
import org.example.cliniccare1.service.AdminService;
import org.example.cliniccare1.service.MDService;
import org.example.cliniccare1.service.MFService;
import org.example.cliniccare1.service.MVAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    private MFService mfService;



    private MDService mdService;
    private MVAService mvaService;
    private AdminService adminService;

    @Autowired
    public DashboardController(MFService mfService, MDService mdService, MVAService mvaService, AdminService adminService) {
        this.mfService = mfService;
        this.mdService = mdService;
        this.mvaService = mvaService;
        this.adminService = adminService;
    }
    @PostMapping("/roles/new_role")
    public ResponseEntity<Profile>createRole(@RequestBody Profile profile){
          adminService.addRole(profile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }
    @DeleteMapping("/roles/delete_role/{id}")
    public ResponseEntity<Profile>deleteRole(@PathVariable Long profile_id){
        adminService.removeRole(profile_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/roles/all")
    public ResponseEntity<List<Profile>> getRoles() {
        List<Profile> profiles = adminService.getAllRoles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
    @PutMapping("/roles/update")
    public ResponseEntity<List<Profile>> updateRole(@RequestBody Profile profile) {
        adminService.updateRole(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/users/user/{user_id}/medicaldirectories/{md_id}")
    public ResponseEntity<Medical_Directory> getMD(@PathVariable("user_id") Long userId,
                                                   @PathVariable("md_id") Long mfId) {
        try {
            Optional<Medical_Directory> medicalDirectory = mdService.getMDById(mfId);
            return medicalDirectory.map(file -> new ResponseEntity<>(file, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/user/{user_id}/medicaldirectories/new_md")
    public ResponseEntity<Medical_Directory> createMD(@PathVariable("user_id") Long userId,
                                                      @RequestBody Medical_Directory medicalDirectory ) {
        try {
            mdService.saveMD(userId,medicalDirectory);
            return new ResponseEntity<>(medicalDirectory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/users/user/{user_id}/medicaldirectories/all")
    public ResponseEntity<List<Medical_Directory>>getAllMD(){
        List<Medical_Directory>medicalDirectories=mdService.getALLMedicalDirectories();
        return new ResponseEntity<>(medicalDirectories, HttpStatus.OK);
    }
    @DeleteMapping("/users/user/{user_id}/medicaldirectories/{md_id}")
    public ResponseEntity<String> deleteMD(@PathVariable("user_id") Long userId, @PathVariable("md_id") Long medicalDirId) {
        try {

            boolean isDeleted = mdService.removeMD(medicalDirId);
            if (isDeleted)
                return new ResponseEntity<>("File successfully deleted", HttpStatus.OK);
            else
                return new ResponseEntity<>("File deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/users/user/{user_id}/medicaldirectories/update")
    public ResponseEntity<String> updateMD(@PathVariable("user_id") Long userId,
                                           @RequestBody Medical_Directory updatedMedicalDirectory) {
        try {
            mdService.updateMD(userId,  updatedMedicalDirectory);
            return ResponseEntity.ok("Medical file updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating medical file: " + e.getMessage());
        }
    }





    @GetMapping("/users/user/{user_id}/medicalfiles/all")
    public ResponseEntity<List<Medical_File>>getAllMF(@PathVariable("user_id") Long userId){
        List<Medical_File>medicalFiles=mfService.getMedicalFilesByUserId(userId);
        return new ResponseEntity<>(medicalFiles, HttpStatus.OK);
    }




    @PostMapping("/users/user/{user_id}/medicalfiles/new_mf")
    public ResponseEntity<Medical_File> createMF(@PathVariable("user_id") Long userId,
                                                 @RequestBody Medical_File medicalFile) {
        try {
            mfService.saveMF(userId,medicalFile);
            return new ResponseEntity<>(medicalFile, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/users/user/{user_id}/medicalfiles/{mf_id}")
    public ResponseEntity<Medical_File> getMF(@PathVariable("user_id") Long userId,
                                              @PathVariable("mf_id") Long mfId) {
        try {
            Optional<Medical_File> medicalFile = mfService.getMFById(mfId);
            return medicalFile.map(file -> new ResponseEntity<>(file, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/users/user/{user_id}/medicalfiles/update")
    public ResponseEntity<String> updateMF(@PathVariable("user_id") Long userId,
                                           @RequestBody Medical_File updatedMedicalFile) {
        try {
            mfService.updateMF(userId,  updatedMedicalFile);
            return ResponseEntity.ok("Medical file updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating medical file: " + e.getMessage());
        }
    }





    @DeleteMapping("/users/user/{user_id}/medicalfiles/{mf_id}")
    public ResponseEntity<String> deleteMF(@PathVariable("user_id") Long userId, @PathVariable("mf_id") Long medicalFileId) {
        try {
            boolean isDeleted = mfService.removeMF(userId, medicalFileId);
            if (isDeleted)
                return new ResponseEntity<>("File successfully deleted", HttpStatus.OK);
            else
                return new ResponseEntity<>("File deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }





}
