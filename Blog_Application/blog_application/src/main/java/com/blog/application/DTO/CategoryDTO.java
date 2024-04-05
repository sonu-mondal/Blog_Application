package com.blog.application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
	
	private Integer categoryId;
	@NotBlank(message="categoryTitle can not be null")
	@Size(min=4, message="Minimum size of category Title is 4")
	private String categoryTitle;
	@NotBlank(message="categoryDescription can not be null")
	@Size(min=10,message="Minimum size of category Title is 10")
	private String categoryDescription;

}
