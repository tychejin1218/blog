package com.example.datasourcereplication.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.datasourcereplication.dto.TodoRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class TodoServiceTest {

  @Resource
  TodoService todoService;

  @DisplayName("insertTodo_@Transactional(readOnly = true)일 때 한건 저장")
  @Test
  void testInsertTodoReadOnlyFalse() {

    // Given
    TodoRequestDto todoRequest = TodoRequestDto.builder()
        .title("Title Junit Test Insert 01")
        .description("Description Junit Test Insert 02")
        .completed(false)
        .build();

    // When
    int result = todoService.insertTodoReadOnlyFalse(todoRequest);
    log.info("result:{}", result);

    // Then
    Assertions.assertEquals(1, result);
  }

  @DisplayName("insertTodoReadOnlyTrue_@Transactional(readOnly = true)일 때 한건 저장")
  @Test
  void testInsertTodoReadOnlyTrue() {

    // Given
    TodoRequestDto todoRequest = TodoRequestDto.builder()
        .title("Title Junit Test")
        .description("Description Junit Test")
        .completed(false)
        .build();

    // When & Then
    Exception exception = assertThrows(Exception.class,
        () -> todoService.insertTodoReadOnlyTrue(todoRequest));
    log.info("exception:{}", exception);
  }

  @DisplayName("insertTodos_To-Do 다건 저장")
  @Test
  void testInsertTodos() {

    // Given
    List<TodoRequestDto> todoRequests = new ArrayList<>();
    todoRequests.add(TodoRequestDto.builder()
        .title("Title Junit Test 01")
        .description("Description Junit Test 02")
        .completed(false)
        .build());
    todoRequests.add(TodoRequestDto.builder()
        .title("Title Junit Test 02")
        .description("Description Junit Test 02")
        .completed(false)
        .build());
    todoRequests.add(TodoRequestDto.builder()
        .title("Title Junit Test 03")
        .description("Description Junit Test 03")
        .completed(false)
        .build());

    // When
    int result = 0;
    try {
      result = todoService.insertTodos(todoRequests);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Then
    Assertions.assertEquals(3, result);
  }

  @DisplayName("insertTodosFailed_To-Do 다건 저장 시 title이 #으로 시작하는 경우 RuntimeException 발생")
  @Test
  void testInsertTodosFailed() {

    // Given
    List<TodoRequestDto> todoRequests = new ArrayList<>();
    todoRequests.add(TodoRequestDto.builder()
        .title("Title Junit Test 01")
        .description("Description Junit Test 02")
        .completed(false)
        .build());
    todoRequests.add(TodoRequestDto.builder()
        .title("Title Junit Test 02")
        .description("Description Junit Test 02")
        .completed(false)
        .build());
    todoRequests.add(TodoRequestDto.builder()
        .title("#Title Junit Test 03")
        .description("Description Junit Test 03")
        .completed(false)
        .build());

    // When
    int result = 0;
    try {
      result = todoService.insertTodosFailed(todoRequests);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Then
    Assertions.assertEquals(0, result);
  }
}