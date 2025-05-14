package ru.igap.projects.business.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BusinessBotApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusinessBotApplication.class, args);
	}

}
