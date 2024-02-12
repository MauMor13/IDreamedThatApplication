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

    private String email;

    private String nickName;

    private String imgAvatarUrl;

    private String borderColorImg;
    public SocialUserDTO(SocialUser socialUser){
        this.id = socialUser.getId();
        this.name = socialUser.getName();
        this.lastName = socialUser.getLastName();
        this.email = socialUser.getEmail();
        this.nickName = socialUser.getNickName();
        this.imgAvatarUrl = socialUser.getImgAvatarUrl();
        this.borderColorImg = socialUser.getBorderColorImg();
    }
}
