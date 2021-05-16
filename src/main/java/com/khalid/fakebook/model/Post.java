package com.khalid.fakebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long post_id;
    @Column(name = "post_img_url")
    private String postImgUrl;
    @Lob
    @Column(name = "post_description")
    private String postDescription;
    private Instant createdAt;
    @Column(nullable = false, updatable = false, name = "owner_id")
    private Long userId;
}
