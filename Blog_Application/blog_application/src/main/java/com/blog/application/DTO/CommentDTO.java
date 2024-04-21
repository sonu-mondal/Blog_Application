package com.blog.application.DTO;

//@NoArgsConstructor
//@Getter
//@Setter
public class CommentDTO {

	private int id;
	
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentDTO(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
