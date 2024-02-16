package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.models.PostDream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RepositoryPostDream extends JpaRepository <PostDream, Long> {
    PostDream findById(long id);
}
