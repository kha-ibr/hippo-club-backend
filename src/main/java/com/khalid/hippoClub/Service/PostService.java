package com.khalid.hippoClub.Service;

import com.khalid.hippoClub.Exception.UserNotFoundException;
import com.khalid.hippoClub.Repo.PostRepo;
import com.khalid.hippoClub.Repo.UserRepo;
import com.khalid.hippoClub.dto.PostReq;
import com.khalid.hippoClub.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    @Transactional
    public void addPost(PostReq req, Long userId) {
        Post post = new Post();
        System.out.println(req);
        userRepo.findByUserId(userId).map(user -> {
            post.setPostImgUrl(req.getImgUrl());
            post.setPostDescription(req.getPostDescription());
            post.setCreatedAt(Instant.now());
            post.setUser(user);
            return postRepo.save(post);
        })
                .orElseThrow(() -> new UserNotFoundException("User not found"));

    }
    @Transactional(readOnly = true)
    public Stream<Object> findAllPosts() {
        return postRepo.findAll()
                .stream().map(post -> {
                    Map<String, Object> postInfo = new HashMap<>();
                    postInfo.put("postId", post.getPostId());
                    postInfo.put("firstname", post.getUser().getFirstname());
                    postInfo.put("lastname", post.getUser().getLastname());
                    postInfo.put("avatar", post.getUser().getAvatar());
                    postInfo.put("image", post.getPostImgUrl());
                    postInfo.put("postDescription", post.getPostDescription());
                    postInfo.put("createdAt", post.getCreatedAt().toString());
                    return postInfo;
                });
    }

    @Transactional(readOnly = true)
    public Stream<?> findAllPostByUser(Long userId) {
        return postRepo.findAllPostAndUserId(userId)
                .stream().map(post -> {
            Map<String, Object> postInfo = new HashMap<>();
            postInfo.put("postId", post.getPostId());
            postInfo.put("firstname", post.getUser().getFirstname());
            postInfo.put("lastname", post.getUser().getLastname());
            postInfo.put("avatar", post.getUser().getAvatar());
            postInfo.put("image", post.getPostImgUrl());
            postInfo.put("postDescription", post.getPostDescription());
            postInfo.put("createdAt", post.getCreatedAt().toString());
            return postInfo;
        });
    }

    public Optional<Post> findPostById(Long postId) {
        return postRepo.findPostByPostId(postId);
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
        postRepo.findPostByPostId(postId).map(post -> {
            postRepo.delete(post);
            return post.getPostId();
        })
        .orElseThrow(() -> new UserNotFoundException("post not found"));
    }
}
