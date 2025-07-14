package com.kobe.wagglewaggle.dto.response;

import com.kobe.wagglewaggle.domain.Comment;

import java.time.LocalDateTime;

public class CommentResponse {

	private final Long id;
	private final String content;
	private final String author;
	private final Long postId;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public CommentResponse(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.author = comment.getAuthor();
		this.postId = comment.getPost().getId();
		this.createdAt = comment.getCreatedAt();
		this.updatedAt = comment.getUpdatedAt();
	}
}
