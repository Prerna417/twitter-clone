package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.TweetLike;
import com.major.twitter_clone.entities.id.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetLikeRepository extends JpaRepository<TweetLike, LikeId> {
}
