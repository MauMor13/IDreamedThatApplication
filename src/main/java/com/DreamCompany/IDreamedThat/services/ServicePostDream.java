package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.DTOs.NewPostDreamDTO;
import org.springframework.http.ResponseEntity;

public interface ServicePostDream {
    ResponseEntity<Object> newPostDream (NewPostDreamDTO newPostDreamDTO);
}
