package com.khalid.fakebook.model;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long post_id;
    private String post_url;
    private String post_description;
    private Long owner_id;

    public Post() {
    }

    public Post(Long id, String post_url, String post_description, Long owner_id) {
        this.post_id = post_id;
        this.post_url = post_url;
        this.post_description = post_description;
        this.owner_id = owner_id;
    }

    public Long getId() {
        return post_id;
    }

    public void setId(Long post_id) {
        this.post_id = post_id;
    }

    public String getPost_url() {
        return post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + post_id +
                ", post_url='" + post_url + '\'' +
                ", post_description='" + post_description + '\'' +
                ", owner_id=" + owner_id +
                '}';
    }
}
