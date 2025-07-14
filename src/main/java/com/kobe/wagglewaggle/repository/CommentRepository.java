package com.kobe.wagglewaggle.repository;

import com.kobe.wagglewaggle.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);
}
