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
    private String post_url;
    @Lob
    private String post_description;
    private Instant createdAt;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
