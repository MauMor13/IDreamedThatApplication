package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonDTO {
    private long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String role;
    public  PersonDTO (Person person){
        this.id = person.getId();
        this.name = person.getName();
        this.lastName = person.getLastName();
        this.password = person.getPassword();
        this.email = person.getEmail();
        this.role = String.valueOf(person.getRole());
    }
}
