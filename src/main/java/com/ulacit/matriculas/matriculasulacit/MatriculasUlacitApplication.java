package com.ulacit.matriculas.matriculasulacit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MatriculasUlacitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculasUlacitApplication.class, args);
	}
}
