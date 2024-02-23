package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.models.PostDream;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ServicePostDream {
    void save(PostDream postDream);
    Optional<PostDream> findById(long id);
    ResponseEntity<Object> newPostDream(String title, String story, boolean anonymous, List<Integer> idCategory, List<MultipartFile> images) throws IOException;
    ResponseEntity<Object> getPostsUser();
    ResponseEntity<Object> getPostDreamId(long id);
    ResponseEntity<Object> getLatestPosts();
    List<PostDream> findLatestActivePosts();
}
