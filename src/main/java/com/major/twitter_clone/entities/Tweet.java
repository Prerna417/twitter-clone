package com.major.twitter_clone.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
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
import java.util.ArrayList;
import java.util.List;

/**
 * A post. Replies, retweets and quote tweets are also rows here:
 *  - reply     -> replyTo is set
 *  - retweet   -> retweetOf is set, text is null
 *  - quote     -> quoteOf is set and text is present
 *
 * The search_vector column (TSVECTOR, GIN index) is a generated column defined in the
 * Flyway migration and is deliberately not mapped here; query it with native SQL.
 * Deleted tweets keep their row (deletedAt set) and must be filtered out in queries.
 */
@Entity
@Table(name = "tweets", indexes = {
        @Index(name = "ix_tweets_author_created", columnList = "author_id, created_at DESC, id DESC"),
        @Index(name = "ix_tweets_reply_to_created", columnList = "reply_to_id, created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"author", "replyTo", "retweetOf", "quoteOf", "media"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /** users 1 : N tweets */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private AppUser author;

    /** Null only for a plain retweet. */
    @Column(length = 280)
    private String text;

    /** tweets 1 : N replies (self-reference) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_to_id")
    private Tweet replyTo;

    /** tweets 1 : N retweets (self-reference) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retweet_of_id")
    private Tweet retweetOf;

    /** tweets 1 : N quotes (self-reference) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_of_id")
    private Tweet quoteOf;

    /** tweets 1 : N tweet_media (up to 4, ordered by position) */
    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    @Builder.Default
    private List<TweetMedia> media = new ArrayList<>();

    @Column(name = "like_count", nullable = false)
    @Builder.Default
    private int likeCount = 0;

    @Column(name = "reply_count", nullable = false)
    @Builder.Default
    private int replyCount = 0;

    @Column(name = "retweet_count", nullable = false)
    @Builder.Default
    private int retweetCount = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /** Null = visible. Set this instead of deleting the row. */
    @Column(name = "deleted_at")
    private Instant deletedAt;

    public boolean isDeleted() {
        return deletedAt != null;
    }

    /** Keeps both sides of the tweet <-> media relation in sync. */
    public void addMedia(TweetMedia item) {
        media.add(item);
        item.setTweet(this);
    }

    public void removeMedia(TweetMedia item) {
        media.remove(item);
        item.setTweet(null);
    }
}
