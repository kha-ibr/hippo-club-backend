package com.khalid.hippoClub.Repo;

import com.khalid.hippoClub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long id);
    User findByEmail(String email);
}
