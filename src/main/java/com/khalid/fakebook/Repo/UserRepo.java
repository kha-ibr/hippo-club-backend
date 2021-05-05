package com.khalid.fakebook.Repo;

import com.khalid.fakebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<Object> findUserByName(String firstName, String lastName);

    void deleteUserById(Long id);

    User findUserById(Long id);
}
