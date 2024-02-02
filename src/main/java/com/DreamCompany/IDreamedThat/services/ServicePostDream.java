package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.DTOs.NewPostDreamDTO;
import com.DreamCompany.IDreamedThat.models.PostDream;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ServicePostDream {
    void save(PostDream postDream);
    ResponseEntity<Object> newPostDream(NewPostDreamDTO newPostDreamDTO) throws IOException;
}
