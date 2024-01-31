package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.DTOs.NewPostDreamDTO;
import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import com.DreamCompany.IDreamedThat.services.ServicePostDream;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;

@RequestMapping("/api")
@RestController
public class SocialUserController {
    private final ServiceSocialUser serviceSocialUser;
    private final ServicePostDream servicePostDream;

    public SocialUserController(ServiceSocialUser serviceSocialUser, ServicePostDream servicePostDream) {
        this.serviceSocialUser = serviceSocialUser;
        this.servicePostDream = servicePostDream;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerSocialUser(@RequestBody SocialUserSignupDTO socialUserSignupDTO) throws MessagingException, UnsupportedEncodingException {
        return serviceSocialUser.socialUserSignup(socialUserSignupDTO);
    }

    @GetMapping("/confirm-registration")
    public void confirmRegistration(@RequestParam String token, HttpServletResponse response) throws IllegalArgumentException{
        serviceSocialUser.confirmRegistration(token, response);
    }

    @PostMapping("/new_post")
    public ResponseEntity<Object> newPostDream(@ModelAttribute @Valid NewPostDreamDTO newPostDreamDTO, BindingResult result){
        if (result.hasErrors()) {
            return new ResponseEntity<>("Validation error in parameters",HttpStatus.BAD_REQUEST);
        }
        return servicePostDream.newPostDream(newPostDreamDTO);
    }
}
