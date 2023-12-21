package com.blog.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.DTO.CategoryDTO;
import com.blog.application.entities.Category;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;//for dto to entity and vice versa conversion
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category cat=this.modelMapper.map(categoryDTO, Category.class);
		Category addedcat=categoryRepository.save(cat);
		return this.modelMapper.map(addedcat, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category cat=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		cat.setCategoryTitle(categoryDTO.getCategoryTitle());
		cat.setCategoryDescription(categoryDTO.getCategoryDescription());
		
		Category updatedcat=categoryRepository.save(cat);
		return this.modelMapper.map(updatedcat, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		categoryRepository.delete(cat);	
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		Category cat=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		return this.modelMapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories=this.categoryRepository.findAll();
		List<CategoryDTO> categoriesdto=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoriesdto;
	}

}
