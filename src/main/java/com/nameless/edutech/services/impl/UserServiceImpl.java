package com.nameless.edutech.services.impl;

import com.nameless.edutech.models.DTO.UserDTO;
import com.nameless.edutech.models.User;
import com.nameless.edutech.repositories.UserRepository;
import com.nameless.edutech.security.services.JWTService;
import com.nameless.edutech.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EntityManager entityManager;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            EntityManager entityManager,
            AuthenticationManager authenticationManager,
            JWTService jwtService) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public boolean createUser(UserDTO userDTO) {
        try {
            User user = convertToEntity(userDTO);
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.password()));
            entityManager.persist(user);

            return true;
        }  catch (Exception e) {
            return false;
        }
    }

    @Override
    public String login(UserDTO userDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password()));

        if (authentication.isAuthenticated())
            return jwtService.generateJWT(userDTO.username());

        return null;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getTheme(),
                user.getStaff()
        );
    }

    private User convertToEntity(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.username())
                .password(userDTO.password())
                .theme(userDTO.theme())
                .staff(userDTO.staff())
                .createdAt(LocalDateTime.now())
                .createdBy(System.getProperty("user.name"))
                .build();
    }
}
