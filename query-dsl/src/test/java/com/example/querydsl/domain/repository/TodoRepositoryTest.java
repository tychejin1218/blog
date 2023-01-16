package com.example.querydsl.domain.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.querydsl.domain.entity.Todo;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
@Disabled
class TodoRepositoryTest {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  EntityManager entityManager;

  @BeforeEach
  void init() {
    setUpTodos();
  }

  @Transactional
  @DisplayName("getTodos_QueryDSL을 사용하여 To-Do 목록 조회")
  @Test
  void testGetTodos() {

    // Given
    Todo todo = Todo.builder()
        .title("Title Test")
        .description("Description Test")
        .completed(true)
        .build();

    // When
    List<Todo> todos = todoRepository.getTodos(todo);

    // Then
    log.info("todos:[{}]", todos);
    assertTrue(!todos.isEmpty());
  }

  /**
   * To-Do 목록을 설정
   */
  @Disabled
  void setUpTodos() {

    String title;
    String description;
    Boolean completed;

    for (int a = 1; a <= 100; a++) {

      title = "Title Test" + a;
      description = "Description Test" + a;
      if (a % 2 == 0) {
        completed = true;
      } else {
        completed = false;
      }
      insertTodo(title, description, completed);
    }

    entityManager.flush();
    entityManager.clear();
  }

  /**
   * To-Do 한 건을 저장
   */
  @Disabled
  void insertTodo(
      String title,
      String description,
      Boolean completed) {
    todoRepository.save(
        Todo.builder()
            .title(title)
            .description(description)
            .completed(completed)
            .build());
  }
}