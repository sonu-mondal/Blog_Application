package com.blog.application.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.DTO.PostDTO;
import com.blog.application.entities.Category;
import com.blog.application.entities.Post;
import com.blog.application.entities.User;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.repository.CategoryRepository;
import com.blog.application.repository.PostRepository;
import com.blog.application.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		Post post=this.modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepository.save(post);
		return this.modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		
		Post updatePost=this.postRepository.save(post);
		return this.modelMapper.map(updatePost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		this.postRepository.delete(post);
		
	}

	@Override
	public List<PostDTO> getAllPost() {
		List<Post> posts= postRepository.findAll();
		List<PostDTO> postDTOs=posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public PostDTO getPostById(Integer PostId) {
		Post post=this.postRepository.findById(PostId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", PostId));
		PostDTO postDTO=this.modelMapper.map(post, PostDTO.class);		
		return postDTO;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
	
		Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts=this.postRepository.findByCategory(cat);
		List<PostDTO> postDTOs=posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts=this.postRepository.findByUser(user);
		List<PostDTO> postDTOs=posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		
		return null;
	}

}
