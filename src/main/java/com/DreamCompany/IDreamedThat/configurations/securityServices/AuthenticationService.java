package com.DreamCompany.IDreamedThat.configurations.securityServices;

import com.DreamCompany.IDreamedThat.DTOs.PersonLoginDTO;
import com.DreamCompany.IDreamedThat.models.Person;
import com.DreamCompany.IDreamedThat.services.ServicePerson;
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
    private final AuthenticationManager authenticationManager;
    public AuthenticationService(
            ServicePerson servicePerson,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder ) {
        this.authenticationManager = authenticationManager;
        this.servicePerson = servicePerson;
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

}
