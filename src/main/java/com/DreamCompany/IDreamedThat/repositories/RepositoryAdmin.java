package com.DreamCompany.IDreamedThat.repositories;
import com.DreamCompany.IDreamedThat.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryAdmin extends JpaRepository<Admin, Long> {
}
