package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.DTOs.NewPostDreamDTO;
import com.DreamCompany.IDreamedThat.models.PostDream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;

@RepositoryRestResource
public interface RepositoryPostDream extends JpaRepository <PostDream, Long> {
    ResponseEntity<Object> newPostDream (NewPostDreamDTO newPostDreamDTO);
}
