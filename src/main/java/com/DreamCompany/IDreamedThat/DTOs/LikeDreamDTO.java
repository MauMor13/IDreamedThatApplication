package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.LikeDream;
import com.DreamCompany.IDreamedThat.models.LikesType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class LikeDreamDTO {

    private long id;

    private LocalDate likeDate;

    private LikesType likesType;

    public LikeDreamDTO(LikeDream likeDream) {
        this.id = likeDream.getId();
        this.likeDate = likeDream.getLikeDate();
        this.likesType = likeDream.getLikesType();
    }
}
