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
import com.blog.application.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
@Tag(name="CategoryController")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	
	@Operation(
			summary = "Post operation for Category Controller",
			description = "It is used to add Category details in the database"
			)
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO categoryDTO2=this.categoryServiceImpl.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(categoryDTO2,HttpStatus.CREATED);
	}

	@Operation(
			summary = "Put operation for Category Controller",
			description = "Updating the category details based on categoryId"
			)
	@PutMapping("/category/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
		CategoryDTO categoryDTO2=this.categoryServiceImpl.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<CategoryDTO>(categoryDTO2,HttpStatus.OK);
	}

	@Operation(
			summary = "Delete operation for Category Controller",
			description = "Deleting the category details based on the categoryId"
			)
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryServiceImpl.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!!", true),HttpStatus.OK);
	}
	

	@Operation(
			summary = "Get operation for Category Controller",
			description = "Fetching the category details based on categoryId"
			)
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
		CategoryDTO categoryDTO=this.categoryServiceImpl.getCategory(categoryId);
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
	}
	
	//url: http://localhost:9989/api/categories?pageNumber=1&pageSize=5
	//http://localhost:9989/api/categories?pageNumber=0&pageSize=10&sortBy=categoryId&sortOrder=asc

	@Operation(
			summary = "Get all operation for Category Controller",
			description = "Fetching all the category details"
			)
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponse> getAllCategory(@RequestParam(value="pageNumber", defaultValue =AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue =AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue ="categoryId", required = false) String sortBy,
			@RequestParam(value="sortOrder", defaultValue =AppConstants.SORT_ORDER, required = false) String sortOrder){
		CategoryResponse response=this.categoryServiceImpl.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
		return new ResponseEntity<CategoryResponse>(response, HttpStatus.OK);
	}

	
}
