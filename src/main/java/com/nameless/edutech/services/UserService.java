package com.nameless.edutech.services;

import com.nameless.edutech.DTO.User.LoginRequest;
import com.nameless.edutech.DTO.User.UserDTO;
import com.nameless.edutech.models.User;

public interface UserService {
    public User getUser(String username);
    public boolean createUser(UserDTO userDTO);

    public String login(LoginRequest loginRequest);
}
