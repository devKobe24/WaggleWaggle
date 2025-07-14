package com.kobe.wagglewaggle.dto.request;

import com.kobe.wagglewaggle.domain.Comment;
import com.kobe.wagglewaggle.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {

	private String content;
	private String author;

	public Comment toEntity(Post post) {
		return Comment.builder()
			.content(content)
			.author(author)
			.post(post)
			.build();
	}
}
