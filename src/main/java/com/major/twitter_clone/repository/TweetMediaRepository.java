package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.TweetMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetMediaRepository extends JpaRepository<TweetMedia, Long> {
}
