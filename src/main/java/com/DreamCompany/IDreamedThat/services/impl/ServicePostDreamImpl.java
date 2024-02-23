package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.DTOs.PostDreamDTO;
import com.DreamCompany.IDreamedThat.awsS3Config.awsS3Services.S3Service;
import com.DreamCompany.IDreamedThat.models.Category;
import com.DreamCompany.IDreamedThat.models.ImageUrl;
import com.DreamCompany.IDreamedThat.models.PostDream;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.repositories.RepositoryPostDream;
import com.DreamCompany.IDreamedThat.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServicePostDreamImpl implements ServicePostDream {
    private final RepositoryPostDream repositoryPostDream;

    private final ServiceCategory serviceCategory;

    private final ServiceImageUrl serviceImageUrl;

    private final ServiceSocialUser serviceSocialUser;

    private final ServicePerson servicePerson;

    private final S3Service s3Service;

    public ServicePostDreamImpl(RepositoryPostDream repositoryPostDream,
                                ServiceCategory serviceCategory,
                                S3Service s3Service,
                                ServiceImageUrl serviceImageUrl,
                                ServiceSocialUser serviceSocialUser,
                                ServicePerson servicePerson) {
        this.repositoryPostDream = repositoryPostDream;
        this.serviceCategory = serviceCategory;
        this.s3Service = s3Service;
        this.serviceImageUrl = serviceImageUrl;
        this.serviceSocialUser = serviceSocialUser;
        this.servicePerson = servicePerson;
    }

    @Override
    public void save(PostDream postDream){
        repositoryPostDream.save(postDream);
    }

    @Override
    public Optional<PostDream> findById(long id){ return repositoryPostDream.findById(id); }

    @Override
    public List<PostDream> findLatestActivePosts() {
        return repositoryPostDream.findTop20ActivePostDreamsOrderByCreationDateDesc();
    }

    @Override
    public ResponseEntity<Object> newPostDream(String title, String story, boolean anonymous, List<Integer> idCategory,List<MultipartFile> images) throws IOException {
        PostDream newPost = new PostDream(title, story, anonymous);
        for( Integer categoryId : idCategory){
            Optional<Category> category = serviceCategory.findById(Long.valueOf(categoryId));
            category.ifPresent(newPost::addCategory);
        }

        if (images != null && !images.isEmpty()){
            for (MultipartFile image : images){
                if (image != null && !image.isEmpty()){
                    ImageUrl imageUrl = new ImageUrl();
                    imageUrl.setImgUrl("imagePostDream" + imageUrl.getId());
                    newPost.addImageDream(imageUrl);
                    serviceImageUrl.save(imageUrl);
                    s3Service.createObject(imageUrl.getImgUrl(), image);
                }
            }
        }
        SocialUser socialUserAuth = serviceSocialUser.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        newPost.addSocialUser(socialUserAuth);
        this.save(newPost);
        servicePerson.save(socialUserAuth);
       return new ResponseEntity<>("Create new post dream", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getPostsUser(){
        SocialUser socialUserAuth = serviceSocialUser.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<PostDreamDTO> postDreamDTO = socialUserAuth.getPostDreams().stream().map(PostDreamDTO::new).toList();

        if(postDreamDTO.isEmpty()){
            return new ResponseEntity<>("No dream post found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(postDreamDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getPostDreamId(long id){
        Optional<PostDream> postDreamOptional = this.findById(id);
        if (postDreamOptional.isEmpty()) {
            return new ResponseEntity<>("No dream post found", HttpStatus.BAD_REQUEST);
        }
        PostDream postDream = postDreamOptional.get();
        PostDreamDTO postDreamDTO = new PostDreamDTO(postDream);
        return new ResponseEntity<>(postDreamDTO, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Object> getLatestPosts(){
        List<PostDreamDTO> latestPosts = this.findLatestActivePosts().stream().map(PostDreamDTO::new).toList();
        return new ResponseEntity<>(latestPosts, HttpStatus.ACCEPTED);
    }
}
