package com.kobe.wagglewaggle.dto.request;

import com.kobe.wagglewaggle.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequest {

	private String title;
	private String content;
	private String author;

	public Post toEntity() {
		return Post.builder()
			.title(title)
			.content(content)
			.author(author)
			.build();
	}
}
