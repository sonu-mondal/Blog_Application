package com.blog.application.payload;

import java.util.List;

import com.blog.application.DTO.PostDTO;
//so we created this postResponse class as we will send all this data to client, will use it where we 
//implemented pagination concept

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;//totalElements means how many total contents we have
	private int totalPages;
	private boolean lastPage;

}
