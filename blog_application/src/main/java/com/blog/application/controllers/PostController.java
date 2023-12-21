package com.blog.application.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.DTO.PostDTO;
import com.blog.application.payload.ApiResponse;
import com.blog.application.service.PostServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDTO createPost=this.postServiceImpl.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}
	
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId){
		PostDTO postDTO2=postServiceImpl.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(postDTO2,HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postServiceImpl.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully",true), HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPost(){
		List<PostDTO> posts=this.postServiceImpl.getAllPost();
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO posts=this.postServiceImpl.getPostById(postId);
		return new ResponseEntity<PostDTO>(posts, HttpStatus.OK);
	}

	//get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
		List<PostDTO> posts=this.postServiceImpl.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	//get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDTO> posts=this.postServiceImpl.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	
	
	
}
