package com.khalid.fakebook.Service;

import com.khalid.fakebook.Exception.UserNotFoundException;
import com.khalid.fakebook.Repo.PostRepo;
import com.khalid.fakebook.Repo.UserRepo;
import com.khalid.fakebook.dto.PostReq;
import com.khalid.fakebook.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final AuthServise authServise;

    @Transactional
    public void addPost(PostReq req, Long userId) {
        Post post = new Post();
        userRepo.findUserByUserId(userId).map(user -> {
            post.setPostImgUrl(req.getImgUrl());
            post.setPostDescription(req.getPostDescription());
            post.setCreatedAt(Instant.now());
            post.setUser(user);
            return postRepo.save(post);
        })
                .orElseThrow(() -> new UserNotFoundException("User not found"));

    }
    @Transactional(readOnly = true)
    public Stream<?> findAllPosts() {
        return postRepo.findAll()
                .stream().map(post -> {
                    Map<String, String> userinfo = new HashMap<>();
                    userinfo.put("Firstname", post.getUser().getFirstname());
                    userinfo.put("Lastname", post.getUser().getLastname());
                    userinfo.put("Avatar", post.getUser().getAvatar());
                    userinfo.put("Image", post.getPostImgUrl());
                    userinfo.put("Image description", post.getPostDescription());
                    userinfo.put("Date created", post.getCreatedAt().toString());
                    return userinfo;
                });
    }

    @Transactional
    public void updatePost(PostReq req, Long postId) {

        postRepo.findById(postId).map(post -> {
            post.setPostImgUrl(req.getImgUrl());
            post.setPostDescription(req.getPostDescription());
            post.setCreatedAt(Instant.now());
            return postRepo.save(post);
        })
        .orElseThrow(() -> new UserNotFoundException("post id not found"));
    }
    @Transactional
    public void deletePost(Long postId) {
        postRepo.findByPostId(postId).map(post -> {
            postRepo.delete(post);
            return post.getPostId();
        })
        .orElseThrow(() -> new UserNotFoundException("post not found"));
    }

}
