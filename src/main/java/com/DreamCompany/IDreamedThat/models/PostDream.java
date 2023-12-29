package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(mappedBy = "postDream", fetch = FetchType.EAGER)
    private Set<ImageUrl> imagesDream = new HashSet<>();

    public PostDream(String title, String story, Set<ImageUrl> imagesDream, boolean edited, boolean anonymous) {
        this.title = title;
        this.story = story;
        this.imagesDream = imagesDream;
        this.edited = edited;
        this.anonymous = anonymous;
    }
}
