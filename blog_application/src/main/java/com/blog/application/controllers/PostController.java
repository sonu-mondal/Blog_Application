package com.blog.application.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.application.DTO.PostDTO;
import com.blog.application.payload.ApiResponse;
import com.blog.application.payload.PostResponse;
import com.blog.application.service.FileServiceImpl;
import com.blog.application.service.PostServiceImpl;
import com.blog.application.utils.AppConstants;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@Autowired
	private FileServiceImpl fileServiceImpl;
	
	@Value("${project.image}")
	private String path;
	
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
	
	//url: http://localhost:9989/api/posts?pageNumber=0&pageSize=5
	//http://localhost:9989/api/posts?pageNumber=0&pageSize=10&sortBy=content&sortOrder=desc
	//here pageNumber will start from 0 and we can modify that and pageSize as well
	//if we take pageSize 2 then 2 number of contents/data will be shown/fetched
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value ="pageNumber", defaultValue =AppConstants.PAGE_NUMBER,required =false ) Integer pageNumber,
			@RequestParam(value ="pageSize", defaultValue =AppConstants.PAGE_SIZE,required =false ) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue =AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortOrder", defaultValue =AppConstants.SORT_ORDER, required = false) String sortOrder){
		PostResponse allPost = this.postServiceImpl.getAllPost(pageNumber, pageSize, sortBy, sortOrder);//ctrl+1 to variable get data type
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
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
	
	//Search post
	//url: http://localhost:9989/api/posts/search/What
	//url: http://localhost:9989/api/posts/search/programming lang
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keyword){
		List<PostDTO> searchPosts = this.postServiceImpl.searchPosts(keyword);
		return new ResponseEntity<List<PostDTO>>(searchPosts, HttpStatus.OK);
	}
	
	
	//Post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		String fileName = this.fileServiceImpl.uploadImage(path, image);
		PostDTO postDto = this.postServiceImpl.getPostById(postId);
		postDto.setImageName(fileName);
		PostDTO updatePost = this.postServiceImpl.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}
	
	//to download image/post
	//http://localhost:9989/api/post/image/75caa246-f3f2-4f81-a428-baf97cc6f3b2.jpg  - copy image name from database and paste at last of this url to get another image
	@GetMapping(value="/post/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource=this.fileServiceImpl.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
	
	
}
