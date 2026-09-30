package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.Mention;
import com.major.twitter_clone.entities.id.MentionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentionRepository extends JpaRepository<Mention, MentionId> {
}
