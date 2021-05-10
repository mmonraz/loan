package com.kasasa.loan;

import com.kasasa.loan.model.security.User;
import com.kasasa.loan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class LoanApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

	@Override
	public void run(String... args){
		List<User> userList = userRepository.findAll();

		if(userList.isEmpty()){
			User user1 = new User();

			user1.setUserName("test");
			user1.setPassword("password");
			user1.setRoles("ROLE_USER");
			user1.setActive(true);

			userRepository.save(user1);

			User user2 = new User();

			user2.setUserName("admin");
			user2.setPassword("admin");
			user2.setRoles("ROLE_USER, ROLE_ADMIN");
			user2.setActive(true);

			userRepository.save(user2);

			User user3 = new User();

			user3.setUserName("support");
			user3.setPassword("support");
			user3.setRoles("ROLE_SUPPORT");
			user3.setActive(true);

			userRepository.save(user3);

		}
	}
}
