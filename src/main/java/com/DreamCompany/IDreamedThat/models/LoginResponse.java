package com.DreamCompany.IDreamedThat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private long expiresIn;
}
