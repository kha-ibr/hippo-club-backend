package com.khalid.fakebook.Repo;

import com.khalid.fakebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long id);

    Optional<User> findUserByUserId(Long id);
    User findByEmail(String email);
}
