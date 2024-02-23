package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.models.PostDream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface RepositoryPostDream extends JpaRepository <PostDream, Long> {
    @Query("SELECT pd FROM PostDream pd WHERE pd.active = true ORDER BY pd.creationDate DESC")
    List<PostDream> findTop20ActivePostDreamsOrderByCreationDateDesc();
}
