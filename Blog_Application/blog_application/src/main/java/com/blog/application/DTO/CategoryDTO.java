package com.blog.application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//
//@NoArgsConstructor
//@Getter
//@Setter
public class CategoryDTO {
	
	private Integer categoryId;
	@NotBlank(message="categoryTitle can not be null")
	@Size(min=4, message="Minimum size of category Title is 4")
	private String categoryTitle;
	@NotBlank(message="categoryDescription can not be null")
	@Size(min=10,message="Minimum size of category Title is 10")
	private String categoryDescription;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryDTO(Integer categoryId,
			@NotBlank(message = "categoryTitle can not be null") @Size(min = 4, message = "Minimum size of category Title is 4") String categoryTitle,
			@NotBlank(message = "categoryDescription can not be null") @Size(min = 10, message = "Minimum size of category Title is 10") String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	

}
