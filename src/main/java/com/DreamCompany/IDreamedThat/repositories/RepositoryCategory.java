package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryCategory extends JpaRepository <Category, Long> {
}
