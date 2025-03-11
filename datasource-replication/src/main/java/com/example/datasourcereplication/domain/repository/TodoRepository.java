package com.example.datasourcereplication.domain.repository;

import com.example.datasourcereplication.domain.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>{


}
