package com.DreamCompany.IDreamedThat.DTOs;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewPostDreamDTO {
    private String title;
    @Lob
    private String story;
    private boolean anonymous;
}
