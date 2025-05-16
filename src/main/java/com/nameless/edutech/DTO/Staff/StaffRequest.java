package com.nameless.edutech.DTO.Staff;

import com.nameless.edutech.DTO.Common.HumanDTO;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffRequest extends HumanDTO {
    private List<String> roles;

    private long classroomId;

    private List<Long> guardianIds;
}
