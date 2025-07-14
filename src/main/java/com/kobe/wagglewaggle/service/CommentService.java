package com.kobe.wagglewaggle.service;

import com.kobe.wagglewaggle.domain.Comment;
import com.kobe.wagglewaggle.domain.Post;
import com.kobe.wagglewaggle.dto.request.CommentCreateRequest;
import com.kobe.wagglewaggle.dto.request.CommentUpdatedRequest;
import com.kobe.wagglewaggle.dto.response.CommentResponse;
import com.kobe.wagglewaggle.repository.CommentRepository;
import com.kobe.wagglewaggle.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	@Transactional
	public Long createComment(final Long postId, final CommentCreateRequest request) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. ID: " + postId));

		Comment comment = request.toEntity(post);

		Comment savedComment = commentRepository.save(comment);
		return savedComment.getId();
	}

	@Transactional(readOnly = true)
	public List<CommentResponse> findAllCommentsByPostId(final Long postId) {
		if (!postRepository.existsById(postId)) {
			throw new IllegalArgumentException("존재하지 않는 게시글입니다. ID: " + postId);
		}

		List<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtAsc(postId);

		return comments.stream()
			.map(CommentResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void updateComment(final Long commentId, final CommentUpdatedRequest request) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다. ID: " + commentId));

		comment.update(request.getContent());
	}

	@Transactional
	public void deleteComment(final Long commentId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다. ID: " + commentId));

		commentRepository.delete(comment);
	}
}
