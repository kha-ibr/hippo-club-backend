package com.khalid.fakebook.Repo;

import com.khalid.fakebook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);
}
