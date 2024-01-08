package com.DreamCompany.IDreamedThat.configurations.securityServices;

import com.DreamCompany.IDreamedThat.DTOs.PersonLoginDTO;
import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import com.DreamCompany.IDreamedThat.models.Person;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.services.ServicePerson;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {
    private final ServicePerson servicePerson;
    private final ServiceSocialUser serviceSocialUser;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthenticationService(
            ServicePerson servicePerson,
            ServiceSocialUser serviceSocialUser,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder ) {
        this.authenticationManager = authenticationManager;
        this.servicePerson = servicePerson;
        this.serviceSocialUser = serviceSocialUser;
        this.passwordEncoder = passwordEncoder;
    }
    public Person authenticate(PersonLoginDTO personLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        personLoginDTO.getEmail(),
                        personLoginDTO.getLoginKey()
                )
        );
        Person person = servicePerson.findByEmail(personLoginDTO.getEmail()).orElseThrow();
        String role = person.getAuthorities().toString();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                personLoginDTO.getEmail(),
                personLoginDTO.getLoginKey(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        return person;
    }
    public ResponseEntity<Object> SocialUserSignup(SocialUserSignupDTO socialUserSignupDTO) {
        if(servicePerson.existsByEmail(socialUserSignupDTO.getEmail())){
            return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
        }
        if(serviceSocialUser.existsByNickName(socialUserSignupDTO.getNickName())){
            return new ResponseEntity<>("This nickname is already in use", HttpStatus.CONFLICT);
        }
        SocialUser socialUser = new SocialUser(
                socialUserSignupDTO.getEmail(),
                socialUserSignupDTO.getNickName(),
                passwordEncoder.encode(socialUserSignupDTO.getPassword())
                );
        servicePerson.save(socialUser);
        return new ResponseEntity<>("Correctly registered",HttpStatus.CREATED);
    }
}
