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
    private boolean active;
    private String imgAvatarUrl;
    private String borderColorImg;
    private LocalDate creationDate;

    public SocialUser(String name, String lastName, String password, String email, Role role, String nickName, boolean active, String imgAvatarUrl, String borderColorImg, LocalDate creationDate) {
        super(name, lastName, password, email, role);
        this.nickName = nickName;
        this.active = active;
        this.imgAvatarUrl = imgAvatarUrl;
        this.borderColorImg = borderColorImg;
        this.creationDate = creationDate;
    }
}
