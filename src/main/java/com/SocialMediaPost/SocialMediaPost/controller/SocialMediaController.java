package com.SocialMediaPost.SocialMediaPost.controller;

import com.SocialMediaPost.SocialMediaPost.Service.SocialMediaService;
import com.SocialMediaPost.SocialMediaPost.model.PostAnalysis;
import com.SocialMediaPost.SocialMediaPost.model.SocialMediaPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class SocialMediaController {
    @Autowired
    private SocialMediaService socialMediaService;

    @PostMapping
    public ResponseEntity<SocialMediaPost> createPost(@RequestBody SocialMediaPost postRequest) {
        SocialMediaPost createdPost = socialMediaService.createPost(postRequest.getTextContent());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/analysis")
    public ResponseEntity<PostAnalysis> getPostAnalysis(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        PostAnalysis analysis = socialMediaService.analyzePost(id);
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }
}

