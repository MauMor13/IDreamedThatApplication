package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.SocialUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class SocialUserDTO {

    private long id;

    private String name;

    private String lastName;

    private String email;

    private String nickName;

    private byte[] imageProfile = null;

    private String borderColorImg;
    public SocialUserDTO(SocialUser socialUser){
        this.id = socialUser.getId();
        this.name = socialUser.getName();
        this.lastName = socialUser.getLastName();
        this.email = socialUser.getEmail();
        this.nickName = socialUser.getNickName();
        this.borderColorImg = socialUser.getBorderColorImg();
    }
}
