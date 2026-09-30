package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.ConversationParticipant;
import com.major.twitter_clone.entities.id.ConversationParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationParticipantRepository extends JpaRepository<ConversationParticipant, ConversationParticipantId> {
}
