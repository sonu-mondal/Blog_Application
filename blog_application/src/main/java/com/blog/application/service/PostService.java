package com.blog.application.service;

import java.util.List;

import com.blog.application.DTO.PostDTO;
import com.blog.application.payload.PostResponse;

public interface PostService {
	
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	public PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	public void deletePost(Integer postId);
	
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	public PostDTO getPostById(Integer PostId);
	
	public List<PostDTO> getPostByCategory(Integer categoryId);
	
	public List<PostDTO> getPostByUser(Integer userId);
	
	public List<PostDTO> searchPosts(String keyword);
	
}
