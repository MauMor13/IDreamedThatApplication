package com.DreamCompany.IDreamedThat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PostDream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    private boolean active = true;

    @Lob
    @NotBlank
    private String story;

    private boolean edited = false;

    @NotNull
    private boolean anonymous;

    private LocalDateTime creationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "postDream", fetch = FetchType.EAGER)
    private Set<ImageUrl> imagesDream = new HashSet<>();

    @OneToMany(mappedBy = "postDream", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "postDream", fetch = FetchType.EAGER)
    private Set<LikeDream> likeDreams = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "social_user_id")
    private SocialUser socialUser;

    @ManyToMany
    @JoinTable(
            name = "post_dream_category",
            joinColumns = @JoinColumn(name = "post_dream_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public PostDream(String title, String story, boolean anonymous) {
        this.title = title;
        this.story = story;
        this.anonymous = anonymous;
    }

    public void addImageDream (ImageUrl imageUrl){
        imageUrl.setPostDream(this);
        imagesDream.add(imageUrl);
    }

    public void addCategory(Category category){
        category.getPostDreams().add(this);
        categories.add(category);
    }

    public void addComment(Comment comment){
        comment.setPostDream(this);
        this.comments.add(comment);
    }
    public void addSocialUser(SocialUser socialUser){
        socialUser.getPostDreams().add(this);
        this.setSocialUser(socialUser);
    }
    public void addLikeDream(LikeDream likeDream){
        likeDream.setPostDream(this);
        this.likeDreams.add(likeDream);
    }
}
