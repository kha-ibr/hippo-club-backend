package com.khalid.fakebook.Service;

import com.khalid.fakebook.Repo.PostRepo;
import com.khalid.fakebook.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public Post addPost(Post post) {
        return postRepo.save(post);
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
