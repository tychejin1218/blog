package com.example.datasourcereplication.domain.repository;

import com.example.datasourcereplication.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{


}