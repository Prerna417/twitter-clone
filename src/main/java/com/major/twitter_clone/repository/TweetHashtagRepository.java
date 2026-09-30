package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.TweetHashtag;
import com.major.twitter_clone.entities.id.TweetHashtagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetHashtagRepository extends JpaRepository<TweetHashtag, TweetHashtagId> {
}
