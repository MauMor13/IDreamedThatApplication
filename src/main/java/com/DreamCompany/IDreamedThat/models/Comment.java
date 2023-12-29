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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String text;
    private LocalDateTime dateTime;

    public Comment(String text, LocalDateTime dateTime) {
        this.text = text;
        this.dateTime = dateTime;
    }
}
