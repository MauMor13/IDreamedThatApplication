package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.DTOs.NewPostDreamDTO;
import com.DreamCompany.IDreamedThat.models.Category;
import com.DreamCompany.IDreamedThat.models.PostDream;
import com.DreamCompany.IDreamedThat.repositories.RepositoryPostDream;
import com.DreamCompany.IDreamedThat.services.ServiceCategory;
import com.DreamCompany.IDreamedThat.services.ServicePostDream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicePostDreamImpl implements ServicePostDream {
    private final RepositoryPostDream repositoryPostDream;

    private final ServiceCategory serviceCategory;

    public ServicePostDreamImpl(RepositoryPostDream repositoryPostDream, ServiceCategory serviceCategory) {
        this.repositoryPostDream = repositoryPostDream;
        this.serviceCategory = serviceCategory;
    }

    @Override
    public ResponseEntity<Object> newPostDream (NewPostDreamDTO newPostDreamDTO){
        PostDream newPost = new PostDream(newPostDreamDTO.getTitle(), newPostDreamDTO.getStory(), newPostDreamDTO.isAnonymous());
        for( Integer categoryId : newPostDreamDTO.getCategoryIds()){
            Optional<Category> category = serviceCategory.findById(Long.valueOf(categoryId));
            category.ifPresent(newPost::addCategory);
        }
        if (newPostDreamDTO.getImages().isEmpty()){
            //implementar el servicio de guardado de imagenes
            //guardar los nuevos url de imagenes y gestionar la relacion entre el post y los url de las imagenes
        }
        repositoryPostDream.save(newPost);
        return new ResponseEntity<>("Create new post dream", HttpStatus.CREATED);
    }
}
