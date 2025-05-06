package com.nameless.edutech.services;

import com.nameless.edutech.models.DTO.UserDTO;
import com.nameless.edutech.models.User;
import org.springframework.stereotype.Service;

public interface UserService {
    public User getUser(String username);
    public boolean createUser(UserDTO userDTO);

    public String login(UserDTO userDTO);
}
