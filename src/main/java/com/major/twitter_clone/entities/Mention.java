package com.major.twitter_clone.entities;

import com.major.twitter_clone.entities.id.MentionId;
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

/** tweets N : N users: which users are @mentioned in which tweets. */
@Entity
@Table(name = "mentions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"tweet", "mentionedAppUser"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mention {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private MentionId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("tweetId")
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("mentionedUserId")
    @JoinColumn(name = "mentioned_user_id")
    private AppUser mentionedAppUser;

    public static Mention of(Tweet tweet, AppUser mentionedAppUser) {
        return Mention.builder()
                .id(new MentionId(tweet.getId(), mentionedAppUser.getId()))
                .tweet(tweet)
                .mentionedAppUser(mentionedAppUser)
                .build();
    }
}
