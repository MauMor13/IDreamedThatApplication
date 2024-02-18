package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.services.ServicePostDream;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api")
@RestController
public class PostDreamController {
    private final ServicePostDream servicePostDream;

    public PostDreamController(ServicePostDream servicePostDream) {
        this.servicePostDream = servicePostDream;
    }

    @Transactional
    @PostMapping(value="/user/new_post",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> newPostDream(@RequestParam("title") @NotBlank @Size(min = 3, max = 30) String title,
                                               @RequestParam("story") @NotBlank @Size(min = 100, max = 300) String story,
                                               @RequestParam("anonymous") @NotNull Boolean anonymous,
                                               @RequestParam("idCategory") @NotNull List<Integer> idCategory,
                                               @RequestParam(value = "images", required = false) List<MultipartFile> images) throws IOException {

        return servicePostDream.newPostDream(title, story, anonymous, idCategory, images);
    }

    @GetMapping("/user/user_post")
    public ResponseEntity<Object> getPostsUser(){ return servicePostDream.getPostsUser(); }

    @GetMapping("/user/post_dream/{id}")
    public ResponseEntity<Object> getPostDreamDTOId(@PathVariable long id){
        return servicePostDream.getPostDreamId(id);
    }

    @GetMapping("/user/latest_posts")
    public ResponseEntity<Object> getLatestPosts(){
        return servicePostDream.getLatestPosts();
    }


}
