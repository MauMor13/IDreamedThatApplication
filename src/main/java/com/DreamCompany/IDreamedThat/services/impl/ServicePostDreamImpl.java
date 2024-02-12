package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.DTOs.NewPostDreamDTO;
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
    public ResponseEntity<Object> newPostDream(NewPostDreamDTO newPostDreamDTO) throws IOException {
        PostDream newPost = new PostDream(newPostDreamDTO.getTitle(), newPostDreamDTO.getStory(), newPostDreamDTO.isAnonymous());
        for( Integer categoryId : newPostDreamDTO.getIdCategory()){
            Optional<Category> category = serviceCategory.findById(Long.valueOf(categoryId));
            category.ifPresent(newPost::addCategory);
        }
        if (!newPostDreamDTO.getImages().isEmpty()){
            for (MultipartFile image : newPostDreamDTO.getImages()){
                ImageUrl imageUrl = new ImageUrl();
                imageUrl.setImgUrl("imagePostDream" + imageUrl.getId());
                newPost.addImageDream(imageUrl);
                serviceImageUrl.save(imageUrl);
                s3Service.createObject(imageUrl.getImgUrl(), image);
            }
        }
        Authentication authenticationUser = SecurityContextHolder.getContext().getAuthentication();
        SocialUser socialUserAuth = serviceSocialUser.findByEmail(authenticationUser.getName());
        newPost.addSocialUser(socialUserAuth);
        this.save(newPost);
        servicePerson.save(socialUserAuth);
       return new ResponseEntity<>("Create new post dream", HttpStatus.CREATED);
    }
}
