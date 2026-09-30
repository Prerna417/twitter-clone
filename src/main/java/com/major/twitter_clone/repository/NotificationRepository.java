package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
