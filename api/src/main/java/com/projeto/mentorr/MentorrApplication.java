package com.projeto.mentorr;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import jakarta.annotation.PostConstruct;

@EnableMethodSecurity
@SpringBootApplication
public class MentorrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentorrApplication.class, args);
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Fortaleza"));
	}

}
