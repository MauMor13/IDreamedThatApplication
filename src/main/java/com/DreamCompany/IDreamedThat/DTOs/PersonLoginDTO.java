package com.DreamCompany.IDreamedThat.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonLoginDTO {
    private String loginKey;
    private String email;
    public PersonLoginDTO(String loginKey, String email) {
        this.loginKey = loginKey;
        this.email = email;
    }
}
