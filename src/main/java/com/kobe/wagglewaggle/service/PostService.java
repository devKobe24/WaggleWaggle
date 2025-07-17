package com.kobe.wagglewaggle.service;

import com.kobe.wagglewaggle.domain.Post;
import com.kobe.wagglewaggle.dto.request.PostCreateRequest;
import com.kobe.wagglewaggle.dto.request.PostUpdateRequest;
import com.kobe.wagglewaggle.dto.response.PostResponse;
import com.kobe.wagglewaggle.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	@Transactional
	public Long createPost(final PostCreateRequest request) {
		Post post = request.toEntity();
		Post savedPost = postRepository.save(post);
		return savedPost.getId();
	}

	@Transactional(readOnly = true)
	public PostResponse findPostById(final Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. ID: " + postId));
		return PostResponse.from(post);
	}

	@Transactional(readOnly = true)
	public List<PostResponse> findAllPosts() {
		return postRepository.findAll().stream()
			.map(PostResponse::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public Long updatePost(final Long postId, final PostUpdateRequest request) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. ID: " + postId));

		post.update(request.getTitle(), request.getContent());

		return postId;
	}

	@Transactional
	public void deletePost(final Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. ID: " + postId));
		postRepository.delete(post);
	}
}
