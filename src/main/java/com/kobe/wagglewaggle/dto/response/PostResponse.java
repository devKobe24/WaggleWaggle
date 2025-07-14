package com.kobe.wagglewaggle.dto.response;

import com.kobe.wagglewaggle.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

	private final Long id;
	private final String title;
	private final String content;
	private final String author;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public PostResponse(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.author = post.getAuthor();
		this.createdAt = post.getCreatedAt();
		this.updatedAt = post.getUpdatedAt();
	}
}
