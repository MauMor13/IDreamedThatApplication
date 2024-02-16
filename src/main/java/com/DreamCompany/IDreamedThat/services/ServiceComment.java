package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.DTOs.NewCommentDTO;
import org.springframework.http.ResponseEntity;

public interface ServiceComment {
   ResponseEntity<Object> newComment(NewCommentDTO newCommentDTO);
}
