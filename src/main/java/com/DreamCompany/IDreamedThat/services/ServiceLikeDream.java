package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.DTOs.NewLikeDreamDTO;
import com.DreamCompany.IDreamedThat.models.LikeDream;
import org.springframework.http.ResponseEntity;

public interface ServiceLikeDream {
    void save (LikeDream likeDream);
    ResponseEntity<Object> likeDream(NewLikeDreamDTO newLikeDreamDTO);
}
