package com.kobe.wagglewaggle.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kobe.wagglewaggle.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

	private Long id;
	private String content;
	private String author;
	private Long postId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static CommentResponse from(Comment comment) {
		if (comment == null) {
			return null;
		}

		return CommentResponse.builder()
			.id(comment.getId())
			.content(comment.getContent())
			.author(comment.getAuthor())
			.postId(comment.getPost() != null ? comment.getPost().getId() : null)
			.createdAt(comment.getCreatedAt())
			.updatedAt(comment.getUpdatedAt())
			.build();
	}

	@Override
	public String toString() {
		return "CommentResponse{" +
			"id=" + id +
			", content='" + content + '\'' +
			", author='" + author + '\'' +
			", postId=" + postId +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			'}';
	}
}
