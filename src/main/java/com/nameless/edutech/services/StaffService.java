package com.nameless.edutech.services;

import com.nameless.edutech.models.DTO.StaffDTO;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<StaffDTO> getAllStaffs();
    Optional<StaffDTO> getStaffById(Long id);
    StaffDTO saveStaff(StaffDTO staffDTO);
    StaffDTO updateStaff(Long id, StaffDTO staffDTO);
    void deleteStaff(Long id);
}
