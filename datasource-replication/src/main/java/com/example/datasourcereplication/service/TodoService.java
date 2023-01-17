package com.example.datasourcereplication.service;

import com.example.datasourcereplication.domain.entity.Todo;
import com.example.datasourcereplication.domain.repository.TodoRepository;
import com.example.datasourcereplication.dto.TodoRequestDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoService {

  private final TodoRepository todoRepository;

  /**
   * To-Do 한건 저장
   */
  @Transactional(readOnly = false)
  public int insertTodoReadOnlyFalse(TodoRequestDto todoRequest) {

    int result = 0;

    todoRepository.save(Todo.builder()
        .title(todoRequest.getTitle())
        .description(todoRequest.getDescription())
        .completed(todoRequest.getCompleted())
        .build());

    result++;

    return result;
  }

  /**
   * To-Do 한건 저장
   */
  @Transactional(readOnly = true)
  public int insertTodoReadOnlyTrue(TodoRequestDto todoRequest) {

    int result = 0;

    todoRepository.save(Todo.builder()
        .title(todoRequest.getTitle())
        .description(todoRequest.getDescription())
        .completed(todoRequest.getCompleted())
        .build());

    result++;

    return result;
  }

  /**
   * To-Do 다건 저장
   */
  @Transactional(readOnly = false)
  public int insertTodos(List<TodoRequestDto> todoRequests) {

    int result = 0;

    for (TodoRequestDto todoRequest : todoRequests) {

      todoRepository.save(Todo.builder()
          .title(todoRequest.getTitle())
          .description(todoRequest.getDescription())
          .completed(todoRequest.getCompleted())
          .build());

      result++;
    }

    return result;
  }

  /**
   * To-Do 다건 저장
   */
  @Transactional(readOnly = false)
  public int insertTodosFailed(List<TodoRequestDto> todoRequests) {

    int result = 0;

    for (TodoRequestDto todoRequest : todoRequests) {

      if (todoRequest.getTitle().startsWith("#")) {
        throw new RuntimeException("title이 #으로 시작");
      }

      todoRepository.save(Todo.builder()
          .title(todoRequest.getTitle())
          .description(todoRequest.getDescription())
          .completed(todoRequest.getCompleted())
          .build());

      result++;
    }

    return result;
  }
}
