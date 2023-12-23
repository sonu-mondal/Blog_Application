package com.blog.application.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.DTO.CategoryDTO;
import com.blog.application.payload.ApiResponse;
import com.blog.application.payload.CategoryResponse;
import com.blog.application.service.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO categoryDTO2=this.categoryServiceImpl.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(categoryDTO2,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
		CategoryDTO categoryDTO2=this.categoryServiceImpl.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<CategoryDTO>(categoryDTO2,HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryServiceImpl.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!!", true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
		CategoryDTO categoryDTO=this.categoryServiceImpl.getCategory(categoryId);
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
	}
	//url: http://localhost:9989/api/categories?pageNumber=1&pageSize=5
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponse> getAllCategory(@RequestParam(value="pageNumber", defaultValue ="0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue ="5", required = false) Integer pageSize){
		CategoryResponse response=this.categoryServiceImpl.getAllCategories(pageNumber, pageSize);
		return new ResponseEntity<CategoryResponse>(response, HttpStatus.OK);
	}

	
}
