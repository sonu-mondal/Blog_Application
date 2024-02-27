package com.blog.application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

//http://localhost:9989/swagger-ui/index.html- url to access swagger ui
@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title="Blog Application Open API",
				version="1.0.0",
				description = "Blog application open api documnetation for Category"
				),
		servers = @Server(
				url="http://localhost:9989",
				description = "Blog application open api url"
				)
		)

public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
