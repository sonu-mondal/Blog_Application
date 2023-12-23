package com.blog.application.service;

import java.util.List;

import com.blog.application.DTO.CategoryDTO;
import com.blog.application.payload.CategoryResponse;

public interface CategoryService {
	
	public CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
	
	public void deleteCategory(Integer categoryId);
	
	public CategoryDTO getCategory(Integer categoryId);
	
	public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);

}
