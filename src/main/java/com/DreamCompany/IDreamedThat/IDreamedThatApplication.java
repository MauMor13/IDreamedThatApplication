package com.DreamCompany.IDreamedThat;

import com.DreamCompany.IDreamedThat.models.Admin;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.services.ServicePerson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IDreamedThatApplication {

	public static void main(String[] args) {
		SpringApplication.run(IDreamedThatApplication.class, args);
	}
	@Bean
	public CommandLineRunner initDataBase(ServicePerson servicePerson){
		return (args) -> {
			servicePerson.save(new Admin("Mauricio", "Mores", "Mauri1312", "mauri.f.mores@gmail.com"));
		};
	}
}
