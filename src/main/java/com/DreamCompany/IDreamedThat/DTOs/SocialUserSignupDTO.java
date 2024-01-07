package com.DreamCompany.IDreamedThat.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SocialUserSignupDTO {
    private String password;
    private String email;
    private String nickName;

    public SocialUserSignupDTO(String password, String email, String nickName) {
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }
}
