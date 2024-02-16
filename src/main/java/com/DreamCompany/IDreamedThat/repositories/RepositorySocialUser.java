package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.models.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RepositorySocialUser extends JpaRepository<SocialUser, Long> {
    boolean existsByNickName(String nickname);
    SocialUser findByEmail(String email);
    SocialUser findById(long id);
}
