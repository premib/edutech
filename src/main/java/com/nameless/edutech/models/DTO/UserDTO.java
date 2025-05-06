package com.nameless.edutech.models.DTO;

import com.nameless.edutech.models.Staff;
import com.nameless.edutech.models.enums.Theme;

public record UserDTO(
        String username,
        String password,
        Theme theme,
        Staff staff
) {
}
