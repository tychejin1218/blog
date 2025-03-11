package com.example.datasourcereplication.service;

import com.example.datasourcereplication.domain.entity.TodoEntity;
import com.example.datasourcereplication.domain.repository.TodoRepository;
import com.example.datasourcereplication.dto.TodoDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoService {

  private final TodoRepository todoRepository;
  private final ModelMapper modelMapper;

  /**
   * 한 건의 할 일을 추가 (쓰기 작업)
   *
   * @param todoRequest 추가할 할 일의 요청 값 (TodoDto.Request)
   * @return 성공적으로 저장된 경우 1 반환
   */
  @Transactional(readOnly = false)
  public boolean insertTodo(TodoDto.Request todoRequest) {
    return todoRepository.save(modelMapper.map(todoRequest, TodoEntity.class)).getId() > 0;
  }

  /**
   * 한 건의 할 일을 추가 (읽기 작업)
   *
   * @param todoRequest 추가할 할 일의 요청 값 (TodoDto.Request)
   * @return 성공적으로 저장된 경우 1 반환
   */
  @Transactional(readOnly = true)
  public boolean insertTodoWithReadOnly(TodoDto.Request todoRequest) {
    return todoRepository.save(modelMapper.map(todoRequest, TodoEntity.class)).getId() > 0;
  }

  /**
   * 여러 건의 할 일을 추가
   *
   * @param todoRequestList 추가할 할 일의 요청 값 (List&lt;TodoDto.Request&gt;)
   * @return 성공적으로 완료된 건수
   */
  @Transactional(readOnly = false)
  public int insertTodo(List<TodoDto.Request> todoRequestList) {

    int savedCount = 0;

    for (TodoDto.Request todoRequest : todoRequestList) {
      todoRepository.save(modelMapper.map(todoRequest, TodoEntity.class));
      savedCount++;
    }

    return savedCount;
  }

  /**
   * 여러 건의 할 일을 추가
   *
   * @param todoRequestList 추가할 할 일의 요청 값 (List&lt;TodoDto.Request&gt;)
   * @return 성공적으로 완료된 건수
   */
  @Transactional(readOnly = false)
  public int insertTodoWithValidation(List<TodoDto.Request> todoRequestList) {

    int savedCount = 0;

    for (TodoDto.Request todoRequest : todoRequestList) {

      if (todoRequest.getTitle().startsWith("#")) {
        throw new RuntimeException("title이 #으로 시작");
      }

      todoRepository.save(modelMapper.map(todoRequest, TodoEntity.class));
      savedCount++;
    }

    return savedCount;
  }
}
