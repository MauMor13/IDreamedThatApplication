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
    private boolean active = false;
    private String imgAvatarUrl;
    private String borderColorImg;
    private LocalDate creationDate = LocalDate.now();

    public SocialUser(String name, String lastName, String password, String email) {
        super(name, lastName, password, email, Role.USER);
    }
    public SocialUser(String email, String nickName, String password){
        super(password, email,Role.USER);
        this.nickName = nickName;
    }
}
