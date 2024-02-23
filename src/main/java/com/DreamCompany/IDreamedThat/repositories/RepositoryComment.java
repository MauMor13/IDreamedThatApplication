package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryComment extends JpaRepository <Comment, Long> {
}
