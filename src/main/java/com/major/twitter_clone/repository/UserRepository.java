package com.major.twitter_clone.repository;

import com.major.twitter_clone.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
