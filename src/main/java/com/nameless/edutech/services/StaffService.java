package com.nameless.edutech.services;

import com.nameless.edutech.DTO.Staff.StaffRequest;
import com.nameless.edutech.DTO.Staff.StaffResponse;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<StaffResponse> getAllStaffs();
    Optional<StaffResponse> getStaffById(Long id);
    StaffResponse saveStaff(StaffRequest staffResponse);
    StaffResponse updateStaff(Long id, StaffRequest staffResponse);
    void deleteStaff(Long id);
}
