package com.kobe.wagglewaggle.controller;

import com.kobe.wagglewaggle.dto.request.PostCreateRequest;
import com.kobe.wagglewaggle.dto.request.PostUpdateRequest;
import com.kobe.wagglewaggle.dto.response.PostResponse;
import com.kobe.wagglewaggle.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<Void> createPost(@RequestBody final PostCreateRequest request) {
		Long postId = postService.createPost(request);
		return ResponseEntity.created(URI.create("/api/v1/posts/" + postId)).build();
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostResponse> findPostById(@PathVariable final Long postId) {
		PostResponse response = postService.findPostById(postId);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<PostResponse>> findAllPosts() {
		List<PostResponse> responses = postService.findAllPosts();
		return ResponseEntity.ok(responses);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<Void> updatePost(@PathVariable final Long postId, @RequestBody final PostUpdateRequest request) {
		postService.updatePost(postId, request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable final Long postId) {
		postService.deletePost(postId);
		return ResponseEntity.noContent().build();
	}
}
