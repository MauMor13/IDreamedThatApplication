package com.DreamCompany.IDreamedThat.services;
import com.DreamCompany.IDreamedThat.models.Person;
import java.util.Optional;

public interface ServicePerson {
    Optional<Person> findByEmail(String email);
    void save(Person person);
    boolean existsByEmail(String email);
}
