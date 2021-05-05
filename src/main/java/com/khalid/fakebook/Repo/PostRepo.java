package com.khalid.fakebook.Repo;

import com.khalid.fakebook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
    void deletePostById(Long id);
}