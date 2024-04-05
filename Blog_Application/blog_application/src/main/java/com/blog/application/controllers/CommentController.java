package com.blog.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.DTO.CommentDTO;
import com.blog.application.payload.ApiResponse;
import com.blog.application.service.CommentServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/")
@Tag(name="CommentController")
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	//url: http://localhost:9989/api/post/6/comments
	@Operation(
			summary = "Post operation for Comment Controller",
			description = "It is used to add comment in the database"
			)
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDto, @PathVariable Integer postId){
		CommentDTO commentDTO2 = this.commentServiceImpl.createComment(commentDto, postId);
		return new ResponseEntity<CommentDTO>(commentDTO2, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Delete operation for Comment Controller",
			description = "It is used to delete comments based on the commentId"
			)
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentServiceImpl.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully!", true), HttpStatus.OK);
	}
}
