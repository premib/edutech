package com.nameless.edutech.controllers;

import com.nameless.edutech.models.DTO.LoginResponse;
import com.nameless.edutech.models.DTO.UserDTO;
import com.nameless.edutech.services.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        boolean userRegistered;

        try {
            userRegistered = userService.createUser(userDTO);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Username already exists!", HttpStatus.CONFLICT);
        }

        if (userRegistered) {
            return new ResponseEntity<>("Registered User Successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Exception occurred", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserDTO userDTO) {
        String token = userService. login(userDTO);

        if (token != null) {
            return new ResponseEntity<>(new LoginResponse(token, null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new LoginResponse(null, "Unauthorized Login"), HttpStatus.UNAUTHORIZED);
    }
}
