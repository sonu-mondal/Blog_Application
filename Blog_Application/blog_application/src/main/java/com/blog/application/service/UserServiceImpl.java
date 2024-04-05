package com.blog.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.application.DTO.UserDTO;
import com.blog.application.repository.UserRepository;
import com.blog.application.entities.User;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payload.UserResponse;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user=userDtoToEntity(userDTO);
		User newUser=userRepository.save(user);
		return entityToUserDTO(newUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		
		User userinfo=userRepository.save(user);
		UserDTO userDTO2=entityToUserDTO(userinfo);
		return userDTO2;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user=this.userRepository.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		return entityToUserDTO(user);
	}

	@Override
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		
		Sort sort=null;
		if(sortOrder.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<User> pageUser=this.userRepository.findAll(p);
		
		List<User> users=pageUser.getContent();
		List<UserDTO> userDtos=users.stream().map(user->this.entityToUserDTO(user)).collect(Collectors.toList());
		UserResponse userResponse=new UserResponse();
		
		userResponse.setContent(userDtos);
		userResponse.setLastPage(pageUser.isLast());
		userResponse.setPageNumber(pageUser.getNumber());
		userResponse.setPageSize(pageUser.getSize());
		userResponse.setTotalElements(pageUser.getNumberOfElements());
		userResponse.setTotalPages(pageUser.getTotalPages());
		
		return userResponse;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);	
	}
	
	public User userDtoToEntity(UserDTO userDTO) {
		User user=modelMapper.map(userDTO, User.class);//OR
		//User user=new User();
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
		return user;		
	}
	
	public UserDTO entityToUserDTO(User user) {
		UserDTO userDTO=modelMapper.map(user, UserDTO.class);//Or
//		UserDTO userDTO=new UserDTO();
//		userDTO.setId(user.getId());
//		userDTO.setName(user.getName());
//		userDTO.setEmail(user.getEmail());
//		userDTO.setPassword(user.getPassword());
//		userDTO.setAbout(user.getAbout());
		return userDTO;
		
	}

}
