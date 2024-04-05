package com.blog.application.service;

import com.blog.application.DTO.CommentDTO;

public interface CommentService {
	
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId);
	
	public void deleteComment(Integer commentId);

}
