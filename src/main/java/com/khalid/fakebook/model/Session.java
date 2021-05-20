package com.khalid.fakebook.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "session_id")
    private Long sessionId;
    @Column(name = "ssid", nullable = false)
    private String session;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;
}
