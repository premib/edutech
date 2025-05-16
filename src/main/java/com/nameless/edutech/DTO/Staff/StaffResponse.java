package com.nameless.edutech.DTO.Staff;

import com.nameless.edutech.DTO.Common.HumanDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponse extends HumanDTO {
    private StaffClassDetails classroom;

    private List<StaffRoleDetails> role;

    private List<HumanDTO> guardians;
}
