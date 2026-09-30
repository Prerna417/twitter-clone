package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.Follow;
import com.major.twitter_clone.entities.id.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
}
