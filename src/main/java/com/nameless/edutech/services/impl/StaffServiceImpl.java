package com.nameless.edutech.services.impl;

import com.nameless.edutech.DTO.Staff.StaffRequest;
import com.nameless.edutech.DTO.Staff.StaffResponse;
import com.nameless.edutech.mappers.StaffMapper;
import com.nameless.edutech.models.base.Staff;
import com.nameless.edutech.repositories.StaffRepository;
import com.nameless.edutech.services.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    private final StaffMapper staffMapper;

    public StaffServiceImpl(StaffRepository staffRepository, StaffMapper staffMapper) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    public List<StaffResponse> getAllStaffs() {
        return staffRepository.findAll().stream()
                .map(staffMapper::toStaffResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StaffResponse> getStaffById(Long id) {
        return staffRepository.findById(id).map(staffMapper::toStaffResponse);
    }

    @Override
    public StaffResponse saveStaff(StaffRequest staffRequest) {
        Staff staff = staffMapper.toStaff(staffRequest);
        Staff savedStaff = staffRepository.save(staff);

        return staffMapper.toStaffResponse(savedStaff);
    }

    @Override
    public StaffResponse updateStaff(Long id, StaffRequest staffRequest) {
        Staff staff = staffRepository.findById(id).orElseThrow();

        staff.setFirstName(staffRequest.getFirstName());
        staff.setLastName(staffRequest.getLastName());
        staff.setDob(staffRequest.getDob());
        staff.setPhotoUrl(staffRequest.getPhotoUrl());

        Staff updatedStaff = staffRepository.save(staff);

        return staffMapper.toStaffResponse(updatedStaff);
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }
}