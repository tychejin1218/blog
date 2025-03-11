package com.example.datasourcereplication.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.datasourcereplication.dto.TodoDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class TodoServiceTest {

  @Autowired
  TodoService todoService;

  @DisplayName("@Transactional(readOnly = false)로 한 건 저장 성공")
  @Test
  void testInsertTodoReadOnlyFalse() {

    // Given
    TodoDto.Request todoRequest = TodoDto.Request.of("할 일", "할 일 설명", false);

    // When
    boolean isSaved = todoService.insertTodo(todoRequest);
    log.info("isSaved: {}", isSaved);

    // Then
    Assertions.assertTrue(isSaved);
  }

  @DisplayName("@Transactional(readOnly = true) 트랜잭션으로 저장 실패")
  @Test
  void testInsertTodoReadOnlyTrue() {

    // Given
    TodoDto.Request todoRequest = TodoDto.Request.of("할 일", "할 일 설명", false);

    // When & Then
    Exception exception = assertThrows(Exception.class,
        () -> todoService.insertTodoWithReadOnly(todoRequest));
    log.info("exception: {}", exception);
  }

  @DisplayName("insertTodos: 3건의 할 일 저장 성공")
  @Test
  void testInsertTodos() {

    // Given
    List<TodoDto.Request> todoRequestList = new ArrayList<>();
    todoRequestList.add(TodoDto.Request.of("할 일 1", "할 일 설명 1", false));
    todoRequestList.add(TodoDto.Request.of("할 일 2", "할 일 설명 2", false));
    todoRequestList.add(TodoDto.Request.of("할 일 3", "할 일 설명 3", false));

    // When
    int savedCount = todoService.insertTodo(todoRequestList);

    // Then
    log.info("Saved count: {}", savedCount);
    Assertions.assertEquals(3, savedCount);
  }

  @DisplayName("insertTodosWithValidation: 제목이 '#'으로 시작하는 경우 RuntimeException 발생")
  @Test
  void testInsertTodosWithValidation() {

    // Given
    List<TodoDto.Request> todoRequestList = new ArrayList<>();
    todoRequestList.add(TodoDto.Request.of("할 일 1", "할 일 설명 1", false));
    todoRequestList.add(TodoDto.Request.of("할 일 2", "할 일 설명 2", false));
    todoRequestList.add(TodoDto.Request.of("#할 일 3", "할 일 설명 3", false));

    // When & Then
    Exception exception = assertThrows(RuntimeException.class,
        () -> todoService.insertTodoWithValidation(todoRequestList));
    log.info("Validation exception: {}", exception.getMessage());
  }
}
