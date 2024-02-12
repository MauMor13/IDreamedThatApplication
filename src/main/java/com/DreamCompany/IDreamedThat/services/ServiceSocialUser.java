package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface ServiceSocialUser {
    boolean existsByNickName(String nickname);
    boolean emailIsValid(String email);
    ResponseEntity<Object> socialUserSignup(SocialUserSignupDTO socialUserSignupDTO) throws MessagingException, UnsupportedEncodingException;
    void confirmRegistration(String token, HttpServletResponse response) throws IllegalArgumentException;
    SocialUser findByEmail(String email);
    ResponseEntity<Object> getUserAuth();
}
