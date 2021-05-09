package com.khalid.fakebook.Repo;

import com.khalid.fakebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
//    Optional<Object> findUserByName(String firstName, String lastName);

    void deleteById(Long id);

    Optional<User> findById(Long id);

    User findByEmail(String email);
}
