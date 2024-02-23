package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.ImageUrl;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageUrlDTO {

    private long id;

    private String imgUrl;

    private boolean allowed;

    public ImageUrlDTO(ImageUrl imageUrl) {
        this.id = imageUrl.getId();
        this.imgUrl = imageUrl.getImgUrl();
        this.allowed = imageUrl.isAllowed();
    }
}
