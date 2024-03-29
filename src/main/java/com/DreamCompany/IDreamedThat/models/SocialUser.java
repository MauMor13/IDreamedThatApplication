package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "socialUser", fetch = FetchType.EAGER)
    private Set<PostDream> postDreams = new HashSet<>();

    @OneToMany(mappedBy = "socialUser", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "socialUser", fetch = FetchType.EAGER)
    private Set<LikeDream> likeDreams = new HashSet<>();

    public SocialUser(String name, String lastName, String password, String email,String nickName) {
        super(name, lastName, password, email, Role.USER);
        this.nickName = nickName;
    }

    public SocialUser(String email, String nickName, String password){
        super(password, email,Role.USER);
        this.nickName = nickName;
    }

    public void addComment(Comment comment){
        comment.setSocialUser(this);
        this.comments.add(comment);
    }
    public void addLikeDream(LikeDream likeDream){
        likeDream.setSocialUser(this);
        this.likeDreams.add(likeDream);
    }
}
