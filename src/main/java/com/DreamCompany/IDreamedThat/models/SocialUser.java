package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SocialUser extends Person{
    private String nickName;
    private boolean active = true;
    private String imgAvatarUrl;
    private String borderColorImg;
    private LocalDate creationDate = LocalDate.now();

    public SocialUser(String name, String lastName, String password, String email) {
        super(name, lastName, password, email, Role.USER);
    }
}
