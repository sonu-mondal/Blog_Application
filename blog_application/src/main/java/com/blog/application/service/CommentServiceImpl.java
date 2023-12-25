package com.blog.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.DTO.CommentDTO;
import com.blog.application.entities.Comment;
import com.blog.application.entities.Post;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.repository.CommentRepository;
import com.blog.application.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	//create/give comment for/on particular ppost identified by post Id
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		
		comment.setPost(post);
		
		Comment saveComment=this.commentRepository.save(comment);
		return this.modelMapper.map(saveComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepository.delete(comment);
		
	}

}
