package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface ServiceSocialUser {
    boolean existsByNickName(String nickname);
    boolean emailIsValid(String email);
    ResponseEntity<Object> socialUserSignup(SocialUserSignupDTO socialUserSignupDTO);
    void confirmRegistration(String token, HttpServletResponse response) throws IllegalArgumentException;
}
