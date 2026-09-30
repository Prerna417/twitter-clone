package com.major.twitter_clone.entities.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MentionId implements Serializable {

    @Column(name = "tweet_id")
    private Long tweetId;

    @Column(name = "mentioned_user_id")
    private Long mentionedUserId;
}
