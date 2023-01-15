package com.example.querydsl.domain.repository;

import com.example.querydsl.domain.entity.Todo;
import java.util.List;

public interface TodoRepositoryCustom {

  /**
   * QueryDSL을 사용하여 To-Do 목록 조회
   */
  List<Todo> getTodos(Todo todo);
}