package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.DTOs.NewLikeDreamDTO;
import com.DreamCompany.IDreamedThat.models.LikeDream;
import com.DreamCompany.IDreamedThat.models.PostDream;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.repositories.RepositoryLikeDream;
import com.DreamCompany.IDreamedThat.services.ServiceLikeDream;
import com.DreamCompany.IDreamedThat.services.ServicePostDream;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceLikeDreamImpl implements ServiceLikeDream {
    private final RepositoryLikeDream repositoryLikeDream;
    private final ServiceSocialUser serviceSocialUser;
    private final ServicePostDream servicePostDream;

    public ServiceLikeDreamImpl(RepositoryLikeDream repositoryLikeDream,
                                ServicePostDream servicePostDream,
                                ServiceSocialUser serviceSocialUser) {
        this.repositoryLikeDream = repositoryLikeDream;
        this.serviceSocialUser = serviceSocialUser;
        this.servicePostDream = servicePostDream;
    }

    @Override
    public void save (LikeDream likeDream){ repositoryLikeDream.save(likeDream); }

    @Override
    public ResponseEntity<Object> likeDream(NewLikeDreamDTO newLikeDreamDTO){
        SocialUser socialUserAuth = serviceSocialUser.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (socialUserAuth == null){
            return new ResponseEntity<>("User not found", HttpStatus.CONFLICT);
        }

        Optional<PostDream> postDreamOptional = servicePostDream.findById(newLikeDreamDTO.getPostId());
        if (postDreamOptional.isEmpty()){
            return new ResponseEntity<>("Post dream not found", HttpStatus.CONFLICT);
        }

        LikeDream likeDreamNew = new LikeDream(newLikeDreamDTO.getLikeType());

        socialUserAuth.addLikeDream(likeDreamNew);
        postDreamOptional.get().addLikeDream(likeDreamNew);

        this.save(likeDreamNew);
        serviceSocialUser.save(socialUserAuth);
        servicePostDream.save(postDreamOptional.get());

        return new ResponseEntity<>("like assigned", HttpStatus.CREATED);
    }

}
