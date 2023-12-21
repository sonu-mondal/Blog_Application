package com.blog.application.service;

import java.util.List;

import com.blog.application.DTO.PostDTO;
import com.blog.application.entities.Post;

public interface PostService {
	
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	public PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	public void deletePost(Integer postId);
	
	public List<PostDTO> getAllPost();
	
	public PostDTO getPostById(Integer PostId);
	
	public List<PostDTO> getPostByCategory(Integer categoryId);
	
	public List<PostDTO> getPostByUser(Integer userId);
	
	public List<Post> searchPosts(String keyword);
	
}
