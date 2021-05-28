package com.khalid.fakebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "user_id")
    private Long userId;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(updatable = false, unique=true, name = "email")
    private String email;
    @Column(name = "pwd")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "created_at")
    private Instant createdAt;

    // POST
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();
}