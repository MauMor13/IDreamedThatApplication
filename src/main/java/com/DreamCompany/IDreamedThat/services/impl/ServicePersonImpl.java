package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.models.Person;
import com.DreamCompany.IDreamedThat.repositories.RepositoryPerson;
import com.DreamCompany.IDreamedThat.services.ServicePerson;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ServicePersonImpl implements ServicePerson {
    private final RepositoryPerson repositoryPerson;
    public ServicePersonImpl(RepositoryPerson repositoryPerson) {
        this.repositoryPerson = repositoryPerson;
    }
    @Override
    public Optional<Person> findByEmail(String email) {
        return repositoryPerson.findByEmail(email);
    }
    @Override
    public void save(Person person) {
        repositoryPerson.save(person);
    }
    @Override
    public boolean existsByEmail(String email) {
        return repositoryPerson.existsByEmail(email);
    }
}
