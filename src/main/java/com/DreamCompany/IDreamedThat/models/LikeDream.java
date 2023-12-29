package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private LocalDate likeDate;
    private LikesType likesType;

    public LikeDream(LocalDate likeDate, LikesType likesType) {
        this.likeDate = likeDate;
        this.likesType = likesType;
    }
}
