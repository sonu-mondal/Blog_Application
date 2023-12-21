package com.blog.application.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
	
	private int id;
	
	@NotEmpty(message="{Name should not be empty}")
	@Size(min=4, message="Name should be minimum 4 chaaracters long")
	private String name;
	
	@Email(message="Email format is incorrect")
	private String email;
	
	@NotEmpty(message="password is must")
	@Size(min=3, max=12, message="Password must be 3 to 12 character long")
	private String password;
	
	@NotEmpty(message="About section can not be empty")
	private String about;
	
	public UserDTO(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	
	
}
