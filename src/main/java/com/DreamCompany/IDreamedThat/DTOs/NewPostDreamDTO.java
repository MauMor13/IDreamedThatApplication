package com.DreamCompany.IDreamedThat.DTOs;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class NewPostDreamDTO {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    @Lob
    private String story;

    @NotNull
    private boolean anonymous;

    @NotNull
    private List<Integer> categoryIds;

    private List<MultipartFile> images;

    public NewPostDreamDTO(String title, String story, boolean anonymous, List<Integer> idCategory, List<MultipartFile> images){
        this.title = title;
        this.story = story;
        this.anonymous = anonymous;
        this.categoryIds = idCategory;
        this.images = images;
    }
}
