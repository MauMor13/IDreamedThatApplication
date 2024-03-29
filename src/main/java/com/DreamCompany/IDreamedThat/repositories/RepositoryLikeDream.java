package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.models.LikeDream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryLikeDream extends JpaRepository <LikeDream, Long> {
}
