package com.SocialMediaPost.SocialMediaPost.repository;

import com.SocialMediaPost.SocialMediaPost.model.SocialMediaPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SocialMediaPostRepository extends MongoRepository<SocialMediaPost, String> {
    @Override
    Optional<SocialMediaPost> findById(String s);
}
