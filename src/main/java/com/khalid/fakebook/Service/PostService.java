package com.khalid.fakebook.Service;

import com.khalid.fakebook.Repo.PostRepo;
import com.khalid.fakebook.Repo.SessionRepo;
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
    private final SessionRepo sessionRepo;

    public void addPost(PostReq req, String validateSession) {
        Post post = new Post();

        Long sessionId = sessionRepo.findBySession(validateSession).getUserId();
        post.setPostImgUrl(req.getImgUrl());
        post.setPostDescription(req.getPostDescription());
        post.setCreatedAt(Instant.now());
        post.setUserId(sessionId);
        postRepo.save(post);
    }

    public List<Post> findAllPosts() {
        return postRepo.findAll();
    }

    public Post updatePost(Post post) {
        return postRepo.save(post);
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

}
