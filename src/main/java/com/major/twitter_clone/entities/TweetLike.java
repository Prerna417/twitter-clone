package com.major.twitter_clone.entities;

import com.major.twitter_clone.entities.id.LikeId;
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

/** Table "likes". Named TweetLike because LIKE is a JPQL keyword.
 * users N : N tweets. The composite PK means one like per appUser per tweet. */
@Entity
@Table(name = "likes", indexes = @Index(name = "ix_likes_tweet", columnList = "tweet_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"appUser", "tweet"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TweetLike {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private LikeId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("tweetId")
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public static TweetLike of(AppUser appUser, Tweet tweet) {
        return TweetLike.builder()
                .id(new LikeId(appUser.getId(), tweet.getId()))
                .appUser(appUser)
                .tweet(tweet)
                .build();
    }
}
