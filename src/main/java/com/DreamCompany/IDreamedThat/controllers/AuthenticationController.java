package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.DTOs.PersonLoginDTO;
import com.DreamCompany.IDreamedThat.configurations.securityServices.AuthenticationService;
import com.DreamCompany.IDreamedThat.configurations.securityServices.JwtService;
import com.DreamCompany.IDreamedThat.models.LoginResponse;
import com.DreamCompany.IDreamedThat.models.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody PersonLoginDTO personLoginDTO) {
        Person authenticatedPerson = authenticationService.authenticate(personLoginDTO);
        String jwtToken = jwtService.generateToken(authenticatedPerson);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
