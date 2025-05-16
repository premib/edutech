package com.nameless.edutech.mappers;

import com.nameless.edutech.DTO.Staff.StaffRequest;
import com.nameless.edutech.DTO.Staff.StaffResponse;
import com.nameless.edutech.models.ExternalHuman;
import com.nameless.edutech.models.base.Staff;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.nameless.edutech.repositories.ExternalHumanRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class StaffMapper {

    @Autowired
    public ExternalHumanRepository externalHumanRepository;

    public abstract StaffResponse toStaffResponse(Staff staff);

    public abstract Staff toStaff(StaffResponse staffResponse);

    @Mapping(source = "staffGuardians", target = "guardianIds", qualifiedByName = "mapStaffGuardiansToGuardianIds")
    public abstract StaffRequest toStaffRequest(Staff staff);

    @Mapping(source = "guardianIds", target = "staffGuardians", qualifiedByName = "mapGuardianIdsToStaffGuardians")
    public abstract Staff toStaff(StaffRequest staffRequest);

    @Named("mapStaffGuardiansToGuardianIds")
    public List<Long> mapStaffGuardiansToGuardianIds(List<ExternalHuman> staffGuardians) {
        if (staffGuardians == null || staffGuardians.isEmpty())
            return Collections.emptyList();

        return staffGuardians.stream()
                .map(ExternalHuman::getId)
                .collect(Collectors.toList());
    }

    @Named("mapGuardianIdsToStaffGuardians")
    public List<ExternalHuman> mapGuardianIdsToStaffGuardians(List<Long> guardianIds) {
        if (guardianIds == null || guardianIds.isEmpty())
            return Collections.emptyList();

        return guardianIds.stream()
                .map(externalHumanRepository::findById)
                .map(externalHuman -> externalHuman.orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }

}
