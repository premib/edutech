package com.nameless.edutech.services.impl;

import com.nameless.edutech.models.DTO.StaffDTO;
import com.nameless.edutech.models.Staff;
import com.nameless.edutech.repositories.StaffRepository;
import com.nameless.edutech.services.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        return staffRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StaffDTO> getStaffById(Long id) {
        return staffRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public StaffDTO saveStaff(StaffDTO staffDTO) {
        Staff staff = convertToEntity(staffDTO);
        Staff savedStaff = staffRepository.save(staff);

        return convertToDTO(savedStaff);
    }

    @Override
    public StaffDTO updateStaff(Long id, StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(id).orElseThrow();

        staff.setFirstName(staffDTO.firstName());
        staff.setLastName(staffDTO.lastName());
        staff.setDob(staffDTO.dob());
        staff.setPhotoUrl(staffDTO.photoUrl());

        Staff updatedStaff = staffRepository.save(staff);

        return convertToDTO(updatedStaff);
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    private StaffDTO convertToDTO(Staff staff) {
        return new StaffDTO(
                staff.getId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getDob(),
                staff.getPhotoUrl()
        );
    }

    private Staff convertToEntity(StaffDTO dto) {
        return Staff.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .dob(dto.dob())
                .photoUrl(dto.photoUrl())
                .build();
    }
}