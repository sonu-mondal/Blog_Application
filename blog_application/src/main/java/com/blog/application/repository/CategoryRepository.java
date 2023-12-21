package com.blog.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
