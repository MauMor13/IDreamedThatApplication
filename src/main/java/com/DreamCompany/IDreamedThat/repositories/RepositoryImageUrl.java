package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.models.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryImageUrl extends JpaRepository <ImageUrl, Long> {
}
