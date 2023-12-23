package com.blog.application.payload;

import java.util.List;

import com.blog.application.DTO.CategoryDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {
	
	private List<CategoryDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;//totalElements means how many total contents we have
	private int totalPages;
	private boolean lastPage;
}
