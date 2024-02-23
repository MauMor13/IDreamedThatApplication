package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.DTOs.NewCommentDTO;
import com.DreamCompany.IDreamedThat.models.Comment;
import com.DreamCompany.IDreamedThat.models.PostDream;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.repositories.RepositoryComment;
import com.DreamCompany.IDreamedThat.services.ServiceComment;
import com.DreamCompany.IDreamedThat.services.ServicePostDream;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCommentImpl implements ServiceComment {
    private final RepositoryComment repositoryComment;
    private final ServiceSocialUser serviceSocialUser;
    private final ServicePostDream servicePostDream;

    public ServiceCommentImpl(RepositoryComment repositoryComment,
                              ServiceSocialUser serviceSocialUser,
                              ServicePostDream servicePostDream) {
        this.repositoryComment = repositoryComment;
        this.serviceSocialUser = serviceSocialUser;
        this.servicePostDream = servicePostDream;
    }

    @Override
    public  ResponseEntity<Object> newComment(NewCommentDTO newCommentDTO){

        Optional<PostDream> postDream = servicePostDream.findById(newCommentDTO.getIdPost());
        if (postDream.isEmpty()){
            return new ResponseEntity<>("Post dream not exist", HttpStatus.BAD_REQUEST);
        }

        SocialUser socialUser = serviceSocialUser.findById(newCommentDTO.getIdUser());
        if (socialUser == null){
            return new ResponseEntity<>("Social User not exist", HttpStatus.BAD_REQUEST);
        }

        Comment newComment = new Comment(newCommentDTO.getTextComment());

        socialUser.addComment(newComment);
        postDream.get().addComment(newComment);

        repositoryComment.save(newComment);
        serviceSocialUser.save(socialUser);
        servicePostDream.save(postDream.get());

        return new ResponseEntity<>("create new comments", HttpStatus.CREATED);
    }
}
