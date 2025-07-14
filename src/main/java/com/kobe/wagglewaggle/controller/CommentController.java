package com.kobe.wagglewaggle.controller;

import com.kobe.wagglewaggle.dto.request.CommentCreateRequest;
import com.kobe.wagglewaggle.dto.request.CommentUpdatedRequest;
import com.kobe.wagglewaggle.dto.response.CommentResponse;
import com.kobe.wagglewaggle.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<Void> createComment(
		@PathVariable final Long postId,
		@RequestBody final CommentCreateRequest request) {
		Long commentId = commentService.createComment(postId, request);
		URI location = URI.create(String.format("/api/v1/posts/%d/comments/%d", postId, commentId));
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public ResponseEntity<List<CommentResponse>> findAllCommentsByPostId(@PathVariable final Long postId) {
		List<CommentResponse> responses = commentService.findAllCommentsByPostId(postId);
		return ResponseEntity.ok(responses);
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<Void> updateComment(
		@PathVariable final Long postId,
		@PathVariable final Long commentId,
		@RequestBody final CommentUpdatedRequest request) {
		commentService.updateComment(commentId, request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteComment(
		@PathVariable final Long postId,
		@PathVariable final Long commentId) {
		commentService.deleteComment(commentId);
		return ResponseEntity.noContent().build();
	}
}
