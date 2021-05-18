package com.khalid.fakebook.Service;

import com.khalid.fakebook.Exception.UserNotFoundException;
import com.khalid.fakebook.Repo.PostRepo;
import com.khalid.fakebook.Repo.UserRepo;
import com.khalid.fakebook.dto.PostReq;
import com.khalid.fakebook.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public Post addPost(PostReq req, Long userId) {
        Post post = new Post();
        post.setPostImgUrl(req.getImgUrl());
        post.setPostDescription(req.getPostDescription());
        post.setCreatedAt(Instant.now());

        return userRepo.findById(userId).map(user -> {
            post.setUser(user);
            return postRepo.save(post);
        })
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<Post> findAllPosts() {
        return postRepo.findAll();
    }

    public void updatePost(PostReq req, Long postId) {

        postRepo.findById(postId).map(post -> {
            post.setPostImgUrl(req.getImgUrl());
            post.setPostDescription(req.getPostDescription());
            post.setCreatedAt(Instant.now());
            return postRepo.save(post);
        })
        .orElseThrow(() -> new UserNotFoundException("post id not found"));
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

}
