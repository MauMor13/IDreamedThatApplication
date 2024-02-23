package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.LikesType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewLikeDreamDTO {

    @NotNull
    private long postId;

    @NotNull
    private LikesType likeType;

    public NewLikeDreamDTO(long postId, LikesType likeType) {
        this.postId = postId;
        this.likeType = likeType;
    }
}
