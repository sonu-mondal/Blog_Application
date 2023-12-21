package com.blog.application.DTO;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

	@NotEmpty(message="{Title should not be empty}")
	private String title;
	
	@NotEmpty(message="{Content should not be empty}")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
	private CategoryDTO category;

	private UserDTO user;
	
	
	
}


