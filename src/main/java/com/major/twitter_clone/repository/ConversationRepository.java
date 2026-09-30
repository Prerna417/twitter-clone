package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
