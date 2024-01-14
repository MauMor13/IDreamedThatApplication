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
    private boolean edited = false;
    private boolean anonymous;
    private LocalDateTime creationDate = LocalDateTime.now();

    //relacion con imagenes en el sueño
    @OneToMany(mappedBy = "postDream", fetch = FetchType.EAGER)
    private Set<ImageUrl> imagesDream = new HashSet<>();

    //relacion con usuario
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "social_user_id", nullable = false)
    SocialUser socialUser;

    //relacion con categorias
    @ManyToMany
    @JoinTable(
            name = "postdream_category",
            joinColumns = @JoinColumn(name = "postdream_id"),
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

}
