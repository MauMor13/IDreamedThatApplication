package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.SocialUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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

    private LocalDate creationDate;

    private String borderColorImg;

    private Set<PostDreamDTO> postDreams;

    private Set<CommentDTO> comments;

    private Set<LikeDreamDTO> likeDreams;

    public SocialUserDTO(SocialUser socialUser){
        this.id = socialUser.getId();
        this.name = socialUser.getName();
        this.lastName = socialUser.getLastName();
        this.email = socialUser.getEmail();
        this.nickName = socialUser.getNickName();
        this.creationDate = socialUser.getCreationDate();
        this.borderColorImg = socialUser.getBorderColorImg();
        this.postDreams = socialUser.getPostDreams().stream().map(PostDreamDTO::new).collect(Collectors.toSet());
        this.comments = socialUser.getComments().stream().map(CommentDTO::new).collect(Collectors.toSet());
        this.likeDreams = socialUser.getLikeDreams().stream().map(LikeDreamDTO::new).collect(Collectors.toSet());
    }
}
