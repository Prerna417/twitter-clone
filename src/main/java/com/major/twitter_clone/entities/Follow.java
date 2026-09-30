package com.major.twitter_clone.entities;

import com.major.twitter_clone.entities.id.FollowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
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

/**
 * users N : N users. "follower follows following".
 * The migration adds: CHECK (follower_id <> following_id).
 */
@Entity
@Table(name = "follows", indexes = @Index(name = "ix_follows_following", columnList = "following_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"follower", "following"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Follow {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private FollowId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("followerId")
    @JoinColumn(name = "follower_id")
    private AppUser follower;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("followingId")
    @JoinColumn(name = "following_id")
    private AppUser following;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public static Follow of(AppUser follower, AppUser following) {
        return Follow.builder()
                .id(new FollowId(follower.getId(), following.getId()))
                .follower(follower)
                .following(following)
                .build();
    }
}
