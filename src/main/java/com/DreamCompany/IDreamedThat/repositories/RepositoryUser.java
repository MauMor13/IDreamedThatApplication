package com.DreamCompany.IDreamedThat.repositories;

import com.DreamCompany.IDreamedThat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryUser extends JpaRepository<User, Long> {

}
