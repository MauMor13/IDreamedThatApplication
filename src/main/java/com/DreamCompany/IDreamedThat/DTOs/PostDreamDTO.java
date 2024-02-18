package com.DreamCompany.IDreamedThat.DTOs;

import com.DreamCompany.IDreamedThat.models.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostDreamDTO {

    private long id;

    private String title;

    private boolean active;

    private String story;

    private boolean edited;

    private boolean anonymous;

    private LocalDateTime creationDate;

    private Set<ImageUrlDTO> imagesDream;

    private Set<CommentDTO> comments;

    private Set<LikeDreamDTO> likeDreams;

    private Set<CategoryDTO> categories;

    public PostDreamDTO(PostDream postDream) {
        this.id = postDream.getId();
        this.title = postDream.getTitle();
        this.active = postDream.isActive();
        this.story = postDream.getStory();
        this.edited = postDream.isEdited();
        this.anonymous = postDream.isAnonymous();
        this.creationDate = postDream.getCreationDate();
        this.imagesDream = postDream.getImagesDream().stream().map(ImageUrlDTO::new).collect(Collectors.toSet());
        this.comments = postDream.getComments().stream().map(CommentDTO::new).collect(Collectors.toSet());
        this.likeDreams = postDream.getLikeDreams().stream().map(LikeDreamDTO::new).collect(Collectors.toSet());
        this.categories = postDream.getCategories().stream().map(CategoryDTO::new).collect(Collectors.toSet());
    }
}
