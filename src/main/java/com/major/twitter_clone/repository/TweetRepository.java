package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
