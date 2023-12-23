package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.Admin;
import com.DreamCompany.IDreamedThat.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminDTO {
    private long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private Role role;
    public AdminDTO(Admin admin){
        this.id = admin.getId();
        this.name = admin.getName();
        this.lastName = admin.getLastName();
        this.password = admin.getPassword();
        this.email = admin.getEmail();
        this.role = admin.getRole();
    }
}
