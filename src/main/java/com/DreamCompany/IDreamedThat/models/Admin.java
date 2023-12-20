package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Admin extends Person{
    public Admin(String name, String lastName, String password, String email) {
        super(name, lastName, password, email, Role.ADMIN);
    }
}
