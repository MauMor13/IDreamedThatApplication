package com.DreamCompany.IDreamedThat.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Recovery {
    private String emailVerification ;
    private LocalDateTime requestedDate = LocalDateTime.now();

    public Recovery(String emailVerification) {
        this.emailVerification = emailVerification;
    }
}
