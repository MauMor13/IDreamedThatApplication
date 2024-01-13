package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;

@RequestMapping("/api")
@RestController
public class SocialUserController {
    private final ServiceSocialUser serviceSocialUser;

    public SocialUserController(ServiceSocialUser serviceSocialUser) { this.serviceSocialUser = serviceSocialUser; }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerSocialUser(@RequestBody SocialUserSignupDTO socialUserSignupDTO) throws MessagingException, UnsupportedEncodingException {
        return serviceSocialUser.socialUserSignup(socialUserSignupDTO);
    }

    @GetMapping("/confirm-registration")
    public void confirmRegistration(@RequestParam String token, HttpServletResponse response) throws IllegalArgumentException{
        serviceSocialUser.confirmRegistration(token, response);
    }

}
