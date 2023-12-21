package com.blog.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
