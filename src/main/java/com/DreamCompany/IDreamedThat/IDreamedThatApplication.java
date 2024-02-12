package com.DreamCompany.IDreamedThat;

import com.DreamCompany.IDreamedThat.models.Admin;
import com.DreamCompany.IDreamedThat.models.Category;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IDreamedThatApplication {

	public static void main(String[] args) {
		SpringApplication.run(IDreamedThatApplication.class, args);
	}
	@Bean
	public CommandLineRunner initDataBase( ServicePerson servicePerson,
										   ServiceCategory serviceCategory,
										   PasswordEncoder passwordEncoder){
		return (args) -> {
			servicePerson.save(new Admin("Mauricio", "Mores", passwordEncoder.encode("Mauri1312"), "mauri.f.mores@gmail.com"));
			//test user
			SocialUser primeUser = new SocialUser("Marcos","Perez", passwordEncoder.encode("pedro1234"),"pedro14@gmail.com","pedrote12");
			primeUser.setActive(true);
			servicePerson.save(primeUser);

			serviceCategory.save(new Category("Fantasy"));
			serviceCategory.save(new Category("Horror"));
			serviceCategory.save(new Category("Recurrent"));
			serviceCategory.save(new Category("Illogical"));

		};
	}
}
