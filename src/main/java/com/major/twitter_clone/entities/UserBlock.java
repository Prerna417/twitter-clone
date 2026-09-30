package com.major.twitter_clone.entities;

import com.major.twitter_clone.entities.enums.BlockType;
import com.major.twitter_clone.entities.id.UserBlockId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/** users N : N users: blocker blocks or mutes blocked. */
@Entity
@Table(name = "user_blocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"blocker", "blocked"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserBlock {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private UserBlockId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("blockerId")
    @JoinColumn(name = "blocker_id")
    private AppUser blocker;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("blockedId")
    @JoinColumn(name = "blocked_id")
    private AppUser blocked;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private BlockType type;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public static UserBlock of(AppUser blocker, AppUser blocked, BlockType type) {
        return UserBlock.builder()
                .id(new UserBlockId(blocker.getId(), blocked.getId()))
                .blocker(blocker)
                .blocked(blocked)
                .type(type)
                .build();
    }
}
