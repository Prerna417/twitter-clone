package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.Bookmark;
import com.major.twitter_clone.entities.id.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
}
