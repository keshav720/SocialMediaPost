package com.SocialMediaPost.SocialMediaPost.Service;

import com.SocialMediaPost.SocialMediaPost.model.PostAnalysis;
import com.SocialMediaPost.SocialMediaPost.model.SocialMediaPost;
import com.SocialMediaPost.SocialMediaPost.repository.SocialMediaPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaService {
    @Autowired
    private SocialMediaPostRepository postRepository;

    @Cacheable("postAnalysisCache")
    public PostAnalysis analyzePost(String postId) throws ChangeSetPersister.NotFoundException {
        SocialMediaPost post = postRepository.findById(postId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        String textContent = post.getTextContent();
        int wordCount = countWords(textContent);
        double averageWordLength = calculateAverageWordLength(textContent);

        return new PostAnalysis(wordCount, averageWordLength);
    }

    public SocialMediaPost createPost(String textContent) {
        SocialMediaPost post = new SocialMediaPost();
        post.setTextContent(textContent);

        return postRepository.save(post);
    }

    private int countWords(String text) {
        // Implement word count logic
        // This is a simple example, you may need to consider punctuation and other factors
        String[] words = text.split("\\s+");
        return words.length;
    }

    private double calculateAverageWordLength(String text) {
        // Implement average word length logic
        String[] words = text.split("\\s+");
        int totalCharacters = text.length();
        int totalWords = words.length;

        if (totalWords == 0) {
            return 0.0;
        }

        return (double) totalCharacters / totalWords;
    }
}

