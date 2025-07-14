package com.kobe.wagglewaggle.repository;

import com.kobe.wagglewaggle.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
