package com.blog.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
