package com.khalid.hippoClub.Repo;

import com.khalid.hippoClub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    Optional<Post> findPostByPostId(Long postId);

    @Query(value = "select * from posts where user_id = ?", nativeQuery = true)
    List<Post> findAllPostAndUserId(Long userId);
}
