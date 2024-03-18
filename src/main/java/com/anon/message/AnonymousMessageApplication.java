package com.anon.message;

import com.anon.message.auth.AuthenticationService;
import com.anon.message.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.anon.message.user.Role.ADMIN;
import static com.anon.message.user.Role.MANAGER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AnonymousMessageApplication {
	@Value("${application.base-url}")
	private String baseUrl;

	public static void main(String[] args) {
		SpringApplication.run(AnonymousMessageApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service) {
		String adminEmail = "admin@mail.com";
		String managerEmail = "manager@mail.com";
		String url = baseUrl + "/message/?userEmail=";
		return args -> {

			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email(adminEmail)
					.password("password")
					.role(ADMIN)
					.messageUrl(url + adminEmail)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email(managerEmail)
					.password("password")
					.role(MANAGER)
					.messageUrl(url + managerEmail)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
