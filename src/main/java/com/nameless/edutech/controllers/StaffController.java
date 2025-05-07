package com.nameless.edutech.controllers;

import com.nameless.edutech.models.DTO.StaffDTO;
import com.nameless.edutech.services.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staffs")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<StaffDTO> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable Long id) {
        Optional<StaffDTO> staff = staffService.getStaffById(id);
        return staff.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StaffDTO createStaff(@RequestBody StaffDTO staffDTO) {
        return staffService.saveStaff(staffDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable Long id, @RequestBody StaffDTO staffDTO) {
        try {
            StaffDTO updatedStaff = staffService.updateStaff(id, staffDTO);
            return ResponseEntity.ok(updatedStaff);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}
