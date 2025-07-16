package com.kobe.wagglewaggle.repository;

import com.kobe.wagglewaggle.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Query("SELECT c FROM Comment c JOIN FETCH c.post WHERE c.post.id = :postId ORDER BY c.createdAt ASC")
	List<Comment> findAllWithPostByPostId(@Param("postId") Long postId);
}
