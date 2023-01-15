package com.example.querydsl.domain.repository;

import com.example.querydsl.domain.entity.QTodo;
import com.example.querydsl.domain.entity.Todo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TodoRepositoryImpl implements TodoRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<Todo> getTodos(Todo todo) {
    QTodo qtodo = QTodo.todo;
    return jpaQueryFactory
        .selectFrom(qtodo)
        .fetch();
  }
}