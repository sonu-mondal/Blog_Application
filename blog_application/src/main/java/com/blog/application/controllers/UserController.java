package com.blog.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.DTO.UserDTO;
import com.blog.application.payload.ApiResponse;
import com.blog.application.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	//add new user
	@PostMapping("/create")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO userDtos=userServiceImpl.createUser(userDTO);
		return new ResponseEntity<>(userDtos, HttpStatus.CREATED);
	}
	
	//update user details
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId){
		UserDTO updatUserDTO= this.userServiceImpl.updateUser(userDTO, userId);
		return ResponseEntity.ok(updatUserDTO);
	}
	
	//delete single user
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId){
		this.userServiceImpl.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully!!",true), HttpStatus.OK);
	}
	
	//Get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return new ResponseEntity<List<UserDTO>>(this.userServiceImpl.getAllUsers(), HttpStatus.OK);
	}
	
	//get single user
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getSingleUsers(@PathVariable Integer id){
		return new ResponseEntity<UserDTO>(this.userServiceImpl.getUserById(id), HttpStatus.OK);
	}
	
}
