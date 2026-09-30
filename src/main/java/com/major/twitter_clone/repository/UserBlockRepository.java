package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.UserBlock;
import com.major.twitter_clone.entities.id.UserBlockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockRepository extends JpaRepository<UserBlock, UserBlockId> {
}
