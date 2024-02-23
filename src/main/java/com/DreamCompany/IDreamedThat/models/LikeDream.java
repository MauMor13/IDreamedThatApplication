package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LikeDream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate likeDate = LocalDate.now();

    private LikesType likesType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "social_user_id")
    private SocialUser socialUser;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_dream_id")
    private PostDream postDream;

    public LikeDream(LikesType likesType) {
        this.likesType = likesType;
    }
}
