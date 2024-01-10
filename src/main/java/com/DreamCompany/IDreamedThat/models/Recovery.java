package com.DreamCompany.IDreamedThat.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Recovery {
    private String keyGenerated = UUID.randomUUID().toString();
    private LocalDateTime requestedDate = LocalDateTime.now();

}
