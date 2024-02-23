package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.DTOs.NewLikeDreamDTO;
import com.DreamCompany.IDreamedThat.services.ServiceLikeDream;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class LikeDreamController {

    private final ServiceLikeDream serviceLikeDream;

    public LikeDreamController(ServiceLikeDream serviceLikeDream) {
        this.serviceLikeDream = serviceLikeDream;
    }

    @PostMapping("/user/like_dream")
    public ResponseEntity<Object> likeDream(@RequestBody @Valid NewLikeDreamDTO newLikeDreamDTO){
        return serviceLikeDream.likeDream(newLikeDreamDTO);
    }

}
