package com.blog.application.service;

import java.util.List;

import com.blog.application.DTO.UserDTO;

public interface UserService {
	
	public UserDTO createUser(UserDTO user);
	
	public UserDTO updateUser(UserDTO user, Integer userId);
	
	public UserDTO getUserById(Integer userId);
	
	public List<UserDTO> getAllUsers();
	
	public void deleteUser(Integer userId);

}
