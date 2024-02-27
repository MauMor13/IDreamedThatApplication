package com.DreamCompany.IDreamedThat;

import com.DreamCompany.IDreamedThat.models.Admin;
import com.DreamCompany.IDreamedThat.models.Category;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.services.*;
import com.DreamCompany.IDreamedThat.utils.Utilitis;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.DreamCompany.IDreamedThat.utils.Utilitis.createPosts;

@SpringBootApplication
public class IDreamedThatApplication {

	public static void main(String[] args) {
		SpringApplication.run(IDreamedThatApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:3000");
			}
		};
	}
	@Bean
	public CommandLineRunner initDataBase( ServicePerson servicePerson,
										   ServiceCategory serviceCategory,
										   ServiceSocialUser serviceSocialUser,
										   ServicePostDream servicePostDream,
										   PasswordEncoder passwordEncoder){
		return (args) -> {

			//create admin for application
			servicePerson.save(new Admin("Mauricio", "Mores", passwordEncoder.encode("Mauri1312"), "mauri.f.mores@gmail.com"));

			//create users
			SocialUser userOne = new SocialUser("Marcos","Perez", passwordEncoder.encode("12345678"),"marcos12@gmail.com","marcos12");
			SocialUser userTwo = new SocialUser("Pedro","Ramos", passwordEncoder.encode("12345678"),"pedro12@gmail.com","pedro12");
			SocialUser userThree = new SocialUser("Rolando","Gomez", passwordEncoder.encode("12345678"),"rolando12@gmail.com","rolando12");
			SocialUser userFour = new SocialUser("Carina","Sanchez", passwordEncoder.encode("12345678"),"carina12@gmail.com","carina12");
			SocialUser userFive = new SocialUser("Soledad","Molina", passwordEncoder.encode("12345678"),"soledad12@gmail.com","soledad12");

			//set the user as active
			userOne.setActive(true);
			userTwo.setActive(true);
			userThree.setActive(true);
			userFour.setActive(true);
			userFive.setActive(true);

			//save users
			serviceSocialUser.save(userOne);
			serviceSocialUser.save(userTwo);
			serviceSocialUser.save(userThree);
			serviceSocialUser.save(userFour);
			serviceSocialUser.save(userFive);

			//create post dream
			createPosts(userOne, 5, servicePostDream);
			createPosts(userTwo, 5, servicePostDream);
			createPosts(userThree, 5, servicePostDream);
			createPosts(userFour, 5, servicePostDream);
			createPosts(userFive, 5, servicePostDream);

			//create and save new categories
			serviceCategory.save(new Category("Fantasy"));
			serviceCategory.save(new Category("Horror"));
			serviceCategory.save(new Category("Recurrent"));
			serviceCategory.save(new Category("Illogical"));
		};
	}
}
