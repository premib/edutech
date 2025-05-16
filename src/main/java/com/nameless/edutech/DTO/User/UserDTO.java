package com.nameless.edutech.DTO.User;

import com.nameless.edutech.models.base.Staff;
import com.nameless.edutech.models.enums.Theme;

public record UserDTO(
        String username,
        String password,
        Theme theme,
        Staff staff
) {
}
