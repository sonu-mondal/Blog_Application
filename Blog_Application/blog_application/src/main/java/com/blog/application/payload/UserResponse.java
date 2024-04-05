package com.blog.application.payload;

import java.util.List;

import com.blog.application.DTO.UserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

	private List<UserDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;//totalElements means how many total contents we have
	private int totalPages;
	private boolean lastPage;
}
