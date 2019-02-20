package com.mark.rest.RestMiniService;

import com.mark.rest.RestMiniService.domain.Role;
import com.mark.rest.RestMiniService.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages={"com.mark.rest.RestMiniService.service", "com.mark.rest.RestMiniService.domain"})
@EnableMongoRepositories("com.mark.rest.RestMiniService.repository")
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.mark.rest.RestMiniService"})
@EnableTransactionManagement
@EntityScan(basePackages="com.mark.rest.RestMiniService.domain")
public class RestMiniServiceApplication {

	public static void main(String[] args) { SpringApplication.run(RestMiniServiceApplication.class, args); }

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {

			Role adminRole = roleRepository.findByRole("ADMIN");
			if (adminRole == null) {
				Role newAdminRole = new Role();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}

			Role userRole = roleRepository.findByRole("USER");
			if (userRole == null) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}
		};
	}
}

