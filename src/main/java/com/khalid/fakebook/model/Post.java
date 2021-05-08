package com.khalid.fakebook.model;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String post_url;
    private String post_description;
//    @ManyToOne
//    @JoinColumn(nullable = false, name = "id")
//    private User user;

    public Post() {
    }

    public Post(Long id, String post_url, String post_description, User user) {
        this.id = id;
        this.post_url = post_url;
        this.post_description = post_description;
//        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", post_url='" + post_url + '\'' +
                ", post_description='" + post_description + '\'' +
                '}';
    }
}
