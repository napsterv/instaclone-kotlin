package io.instagram.instagram.post;

import javax.persistence.*;

@Entity
@Table(name = "post_media")
public class PostMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;

//    @Basic
//    private Utils utils;

    public PostMedia() {
    }

    public PostMedia(String url, Post post) {
        this.url = url;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


