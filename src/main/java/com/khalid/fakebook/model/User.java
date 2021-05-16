package com.khalid.fakebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "user_id")
    private Long userId;
    private String firstname;
    private String lastname;
    @Column(nullable = false, updatable = false, unique=true)
    private String email;
    private String password;
    private String salt;
    private String avatar;
    private Instant createdDate;
//    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
}
