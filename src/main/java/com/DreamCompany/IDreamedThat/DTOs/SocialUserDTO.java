package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.Role;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SocialUserDTO {
    private long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private Role role;
    private String nickName;
    private boolean active;
    private String imgAvatarUrl;
    private String borderColorImg;
    private LocalDate creationDate;
    public SocialUserDTO(SocialUser socialUser){
        this.id = socialUser.getId();
        this.name = socialUser.getName();
        this.lastName = socialUser.getLastName();
        this.password = socialUser.getPassword();
        this.email = socialUser.getEmail();
        this.role = socialUser.getRole();
        this.nickName = socialUser.getNickName();
        this.active = socialUser.isActive();
        this.imgAvatarUrl = socialUser.getImgAvatarUrl();
        this.borderColorImg = socialUser.getBorderColorImg();
        this.creationDate = socialUser.getCreationDate();
    }
}
