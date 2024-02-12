package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import com.DreamCompany.IDreamedThat.services.ServicePostDream;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

    @PostMapping(value="/user/new_post",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> newPostDream(@RequestParam("title") @NotBlank @Size(min = 3, max = 30) String title,
                                               @RequestParam("story") @NotBlank @Size(min = 100, max = 300) String story,
                                               @RequestParam("anonymous") @NotNull Boolean anonymous,
                                               @RequestParam("idCategory") @NotNull List<Integer> idCategory,
                                               @RequestParam(value = "images", required = false) List<MultipartFile> images) throws IOException {

        return servicePostDream.newPostDream(title, story, anonymous, idCategory, images);
    }

    @GetMapping("/user/get_user_auth")
    public ResponseEntity<Object> getUserAuth(){

        return serviceSocialUser.getUserAuth();

    }

}
