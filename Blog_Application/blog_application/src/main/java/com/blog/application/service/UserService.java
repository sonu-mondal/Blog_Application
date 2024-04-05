package com.blog.application.service;


import com.blog.application.DTO.UserDTO;
import com.blog.application.payload.UserResponse;

public interface UserService {
	
	public UserDTO createUser(UserDTO user);
	
	public UserDTO updateUser(UserDTO user, Integer userId);
	
	public UserDTO getUserById(Integer userId);
	
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	public void deleteUser(Integer userId);

}
