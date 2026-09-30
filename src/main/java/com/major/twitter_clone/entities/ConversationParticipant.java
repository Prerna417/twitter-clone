package com.major.twitter_clone.entities;

import com.major.twitter_clone.entities.id.ConversationParticipantId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/** conversations N : N users. A conversation has 2 or more participants. */
@Entity
@Table(name = "conversation_participants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"conversation", "appUser"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConversationParticipant {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ConversationParticipantId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("conversationId")
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    /** Messages newer than this are unread for this appUser. */
    @Column(name = "last_read_at")
    private Instant lastReadAt;

    public static ConversationParticipant of(Conversation conversation, AppUser appUser) {
        return ConversationParticipant.builder()
                .id(new ConversationParticipantId(conversation.getId(), appUser.getId()))
                .conversation(conversation)
                .appUser(appUser)
                .build();
    }
}
