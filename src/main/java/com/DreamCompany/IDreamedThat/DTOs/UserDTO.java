package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.Role;
import com.DreamCompany.IDreamedThat.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserDTO {
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
    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.nickName = user.getNickName();
        this.active = user.isActive();
        this.imgAvatarUrl = user.getImgAvatarUrl();
        this.borderColorImg = user.getBorderColorImg();
        this.creationDate = user.getCreationDate();
    }
}
