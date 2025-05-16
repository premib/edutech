package com.nameless.edutech;

import com.nameless.edutech.DTO.User.UserDTO;
import com.nameless.edutech.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class EdutechApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdutechApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserService userService) {
		return args -> {
			userService.createUser(new UserDTO("admin", "admin", null, null));
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
