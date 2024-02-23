package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RequestMapping("/api")
@RestController
public class SocialUserController {
    private final ServiceSocialUser serviceSocialUser;

    public SocialUserController(ServiceSocialUser serviceSocialUser) {
        this.serviceSocialUser = serviceSocialUser;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerSocialUser(@RequestBody SocialUserSignupDTO socialUserSignupDTO) throws MessagingException, UnsupportedEncodingException {
        return serviceSocialUser.socialUserSignup(socialUserSignupDTO);
    }

    @GetMapping("/confirm-registration")
    public void confirmRegistration(@RequestParam String token, HttpServletResponse response) throws IllegalArgumentException{
        serviceSocialUser.confirmRegistration(token, response);
    }

    @GetMapping("/user/get_user_auth")
    public ResponseEntity<Object> getUserAuth(){ return serviceSocialUser.getUserAuth(); }

    @PatchMapping(value = "/user/attribute_modification", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> patchAttribute(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String lastName,
                                                 @RequestParam(required = false) MultipartFile image,
                                                 @RequestParam(required = false) String borderColorImg) throws IOException {

        return serviceSocialUser.patchAttribute(name, lastName, image, borderColorImg);
    }

}
