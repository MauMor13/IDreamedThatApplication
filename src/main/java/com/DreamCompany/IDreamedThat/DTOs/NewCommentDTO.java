package com.DreamCompany.IDreamedThat.DTOs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewCommentDTO {

    @NotNull
    private long idUser;

    @NotNull
    private long idPost;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 400)
    private String textComment;

    public NewCommentDTO(long idUser, long idPost, String textComment) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.textComment = textComment;
    }
}
