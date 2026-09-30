package com.major.twitter_clone.entities;

import com.major.twitter_clone.entities.id.TweetHashtagId;
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

/** tweets N : N hashtags. */
@Entity
@Table(name = "tweet_hashtags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"tweet", "hashtag"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TweetHashtag {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private TweetHashtagId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("tweetId")
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("hashtagId")
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    public static TweetHashtag of(Tweet tweet, Hashtag hashtag) {
        return TweetHashtag.builder()
                .id(new TweetHashtagId(tweet.getId(), hashtag.getId()))
                .tweet(tweet)
                .hashtag(hashtag)
                .build();
    }
}
