package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import com.DreamCompany.IDreamedThat.models.Recovery;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.repositories.RepositorySocialUser;
import com.DreamCompany.IDreamedThat.services.ServicePerson;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ServiceSocialUserImpl implements ServiceSocialUser {
    private final RepositorySocialUser repositorySocialUser;
    private final ServicePerson servicePerson;
    private final PasswordEncoder passwordEncoder;

    public ServiceSocialUserImpl(RepositorySocialUser repositorySocialUser, ServicePerson servicePerson, PasswordEncoder passwordEncoder) {
        this.repositorySocialUser = repositorySocialUser;
        this.servicePerson = servicePerson;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existsByNickName(String nickname){ return  this.repositorySocialUser.existsByNickName(nickname); }

    @Override
    public boolean emailIsValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public ResponseEntity<Object> socialUserSignup(SocialUserSignupDTO socialUserSignupDTO) {
        if (!this.emailIsValid(socialUserSignupDTO.getEmail())){
            return new ResponseEntity<>("Your email is invalid", HttpStatus.BAD_REQUEST);
        }
        if(servicePerson.existsByEmail(socialUserSignupDTO.getEmail())){
            return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
        }
        if(this.existsByNickName(socialUserSignupDTO.getNickName())){
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

    @Override
    public void confirmRegistration(String token, HttpServletResponse response) throws IllegalArgumentException{
        Optional<Recovery> tokenValidation = null;//buscar el token guardado

        if(tokenValidation.isPresent()){
            //buscar el cliente con el token modificar si esta activo y guardarlo
            response.setHeader("Location", "/web/email-verificado.html");
            response.setStatus(HttpServletResponse.SC_FOUND);
        }
        else{
            throw new IllegalArgumentException("Token inválido");
        }
    }

}
