package com.khalid.fakebook.Controller;

import com.khalid.fakebook.Repo.SessionRepo;
import com.khalid.fakebook.Service.PostService;
import com.khalid.fakebook.dto.PostReq;
import com.khalid.fakebook.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final SessionRepo sessionRepo;

    @PostMapping("/api/upload")
    public ResponseEntity<String> upload(@RequestBody PostReq req, @RequestHeader(value = "session") String validateSession) {

        if (sessionRepo.findBySession(validateSession) == null)
            return new ResponseEntity<>("Access denied", HttpStatus.FORBIDDEN);
        postService.addPost(req, validateSession);
        return new ResponseEntity<>("Post created", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts(@RequestHeader(value = "session") String validateSession) {
        if (sessionRepo.findBySession(validateSession) == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(postService.findAllPosts(), HttpStatus.OK);
    }
}
