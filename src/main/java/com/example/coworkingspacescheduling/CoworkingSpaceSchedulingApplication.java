package com.example.coworkingspacescheduling;

import com.example.coworkingspacescheduling.model.AppUser;
import com.example.coworkingspacescheduling.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CoworkingSpaceSchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoworkingSpaceSchedulingApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveUser(new AppUser(null, "kevin", "kevin@gmail.com", "1234", "11111111"));
			userService.saveUser(new AppUser(null, "lebron", "lebron@gmail.com", "1919", "22222222"));
			userService.saveUser(new AppUser(null, "mj", "mj@gmail.com", "2222", "33333333"));
		};
	}

}
