package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Recovery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String codeGenerated;
    private LocalDateTime requestedDate;

    public Recovery(String codeGenerated, LocalDateTime requestedDate) {
        this.codeGenerated = codeGenerated;
        this.requestedDate = requestedDate;
    }
}
