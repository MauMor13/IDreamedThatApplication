package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.models.PostDream;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ServicePostDream {
    PostDream findById(long id);
    void save(PostDream postDream);
    ResponseEntity<Object> newPostDream(String title, String story, boolean anonymous, List<Integer> idCategory, List<MultipartFile> images) throws IOException;
}
