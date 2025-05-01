package com.nameless.edutech.models.DTO;

import com.nameless.edutech.models.Staff;

public record ClassroomDTO(
        int id,
        int classNumber,
        String section,
        Staff inchargeStaff
) {
}
