package io.instagram.instagram.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.instagram.instagram.user.AppBaseUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption;
    @ManyToOne
    @JoinColumn(nullable = false)
    private AppBaseUser user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = PostMedia.class, fetch = FetchType.LAZY)
    private List<PostMedia> postMediaList = new ArrayList<>();

    public Post() {
    }

    public Post(String caption) {
        this.caption = caption;
    }

    public Post(String caption, AppBaseUser user) {
        this.caption = caption;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @JsonIgnore
    public AppBaseUser getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(AppBaseUser user) {
        this.user = user;
    }

    public List<PostMedia> getPostMediaList() {
        return postMediaList;
    }

    public void setPostMediaList(List<PostMedia> postMediaList) {
        this.postMediaList = postMediaList;
    }
}
