package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.DTOs.NewCommentDTO;
import com.DreamCompany.IDreamedThat.services.ServiceComment;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CommentController {

    private final ServiceComment serviceComment;

    public CommentController(ServiceComment serviceComment) { this.serviceComment = serviceComment; }

    @PostMapping("/user/new_comment")
    public ResponseEntity<Object> newComments(@RequestBody @Valid NewCommentDTO newCommentDTO){
        //crear respuestas de validacion
        return serviceComment.newComment(newCommentDTO);
    }

}
