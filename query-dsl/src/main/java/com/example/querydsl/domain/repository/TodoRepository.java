package com.example.querydsl.domain.repository;

import com.example.querydsl.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {


}