package com.blog.application.service;

import java.util.List;

import com.blog.application.DTO.CategoryDTO;

public interface CategoryService {
	
	public CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
	
	public void deleteCategory(Integer categoryId);
	
	public CategoryDTO getCategory(Integer categoryId);
	
	public List<CategoryDTO> getAllCategories();

}
