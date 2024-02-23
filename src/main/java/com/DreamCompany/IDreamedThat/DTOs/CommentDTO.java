package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDTO {

    private long id;

    private String text;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
    }
}
