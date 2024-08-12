package com.example.modelmapper;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.modelmapper.common.converter.UsersListConverter;
import com.example.modelmapper.domain.User;
import com.example.modelmapper.domain.UserList;
import com.example.modelmapper.dto.UserListDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ModelMapper를 사용한 목록 매핑
 *
 * <p>https://www.baeldung.com/java-modelmapper-lists</p>
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class UserModelMapperTest {

  @Autowired
  ModelMapper modelMapper;

  private List<User> users;

  @BeforeEach
  void init() {

    modelMapper = new ModelMapper();

    TypeMap<UserList, UserListDto> typeMap =
        modelMapper.createTypeMap(UserList.class, UserListDto.class);

    typeMap.addMappings(mapper -> mapper.using(new UsersListConverter())
        .map(UserList::getUsers, UserListDto::setUserNames));

    users = new ArrayList<>();
    users.add(new User("1", "adminName", "admin@naver.com", "010-1234-5678", "ADMIN"));
    users.add(new User("2", "userName", "user@gmail.com", "010-4321-8765", "USER"));
    users.add(new User("3", "testerName", "tester@daum.net", "010-1357-2468", "TESTER"));
  }

  @DisplayName("Integer 객체가 Character 객체로 매핑되는지 테스트")
  @Test
  void convertIntegerToCharacter() {

    // Given
    List<Integer> integers = List.of(1, 2, 3);

    // When
    List<Character> characters = modelMapper.map(integers,
        new TypeToken<List<Character>>() {
        }.getType());

    // Then
    assertAll(
        () -> assertTrue(characters.containsAll(Arrays.asList('1', '2', '3')))
    );
  }
}
