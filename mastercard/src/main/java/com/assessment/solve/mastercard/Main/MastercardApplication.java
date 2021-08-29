package com.assessment.solve.mastercard.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.assessment.solve.*")
@EnableJpaRepositories("com.assessment.solve.mastercard.Repository")
@EntityScan("com.assessment.solve.*")
public class MastercardApplication {

	/**
	 * Main class will be used by JVM to start the Master card application.
	 * It will scan all the packages and make the application available to end user.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MastercardApplication.class, args);
	}

}
