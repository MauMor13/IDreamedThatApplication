package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PostDream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Lob
    private String story;

    private boolean edited;
    private boolean anonymous;
    private LocalDateTime creationDate = LocalDateTime.now();

}
