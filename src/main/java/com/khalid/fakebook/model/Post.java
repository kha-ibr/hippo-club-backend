package com.khalid.fakebook.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "post_id")
    private Long post_id;
    private String post_url;
    @Lob
    private String post_description;
    private Instant createdAt;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Post() {
    }

    public Post(Long post_id, String post_url, String post_description, User user, Instant createdAt, User user1) {
        this.post_id = post_id;
        this.post_url = post_url;
        this.post_description = post_description;
        this.createdAt = createdAt;
//        this.user = user;
        this.user = user1;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long id) {
        this.post_id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + post_id +
                ", post_url='" + post_url + '\'' +
                ", post_description='" + post_description + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
