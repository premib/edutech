package com.nameless.edutech.controllers;

import com.nameless.edutech.DTO.Staff.StaffRequest;
import com.nameless.edutech.DTO.Staff.StaffResponse;
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
    public List<StaffResponse> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponse> getStaffById(@PathVariable Long id) {
        Optional<StaffResponse> staff = staffService.getStaffById(id);
        return staff.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StaffResponse createStaff(@RequestBody StaffRequest staffRequest) {
        return staffService.saveStaff(staffRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffResponse> updateStaff(@PathVariable Long id, @RequestBody StaffRequest staffRequest) {
        try {
            StaffResponse updatedStaff = staffService.updateStaff(id, staffRequest);
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
