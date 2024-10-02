package optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class OptionalTest {

  @Order(1)
  @DisplayName("Optional.ofNullable 테스트: 값이 있는 경우")
  @Test
  void testGetNamePresent() {

    // Given
    UserDto user = UserDto.builder()
        .name("홍길동")
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();

    // When
    Optional<String> optName = Optional.ofNullable(user.getName());

    // Then
    assertAll(
        () -> assertTrue(optName.isPresent(), "Optional 값이 존재합니다"),
        () -> assertEquals("홍길동", optName.get(), "이름이 일치합니다")
    );
  }

  @Order(2)
  @DisplayName("Optional.ofNullable 테스트: 값이 없는 경우")
  @Test
  void testGetNameNotPresent() {

    // Given
    UserDto user = UserDto.builder()
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();

    // When
    Optional<String> optName = Optional.ofNullable(user.getName());

    // Then
    assertAll(
        () -> assertTrue(optName.isEmpty(), "Optional 값이 존재하지 않습니다")
    );
  }

  @Order(3)
  @DisplayName("Optional.orElse 테스트")
  @Test
  void testGetNameOrDefault() {

    // Given
    UserDto user = UserDto.builder()
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When
    String result = optName.orElse("Default Name");

    // Then
    assertEquals("Default Name", result, "기본 이름이 일치합니다");
  }

  @Order(4)
  @DisplayName("Optional.orElseThrow 테스트: 값이 없는 경우 예외 발생")
  @Test
  void testGetNameOrThrowThrowsException() {

    // Given
    UserDto user = UserDto.builder()
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When-Then
    assertThrows(IllegalArgumentException.class,
        () -> optName.orElseThrow(() -> new IllegalArgumentException("이름이 존재하지 않습니다")),
        "예외가 발생해야 합니다");
  }

  @Order(5)
  @DisplayName("Optional.orElseThrow 테스트: 값이 있는 경우")
  @Test
  void testGetNameOrThrow() {

    // Given
    UserDto user = UserDto.builder()
        .name("홍길동")
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When
    String result = optName.orElseThrow(() -> new IllegalArgumentException("이름이 존재하지 않습니다"));

    // Then
    assertEquals("홍길동", result, "이름이 일치합니다");
  }

  @Order(6)
  @DisplayName("Optional.ifPresent 테스트: 값을 더하는 경우")
  @Test
  void testIfPresent() {

    // Given
    UserDto user = UserDto.builder()
        .name("홍길동")
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When
    StringBuilder nameBuilder = new StringBuilder("이름: ");
    optName.ifPresent(name -> nameBuilder.append(name).append("님"));

    // Then
    String formattedName = nameBuilder.toString();
    assertEquals("이름: 홍길동님", formattedName, "이름이 존재해서 처리되었습니다");
  }

  @Order(7)
  @DisplayName("Optional.filter 테스트: 값이 있는 경우 조건을 만족")
  @Test
  void testFilterPresent() {

    // Given
    UserDto user = UserDto.builder()
        .name("홍길동")
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When
    Optional<String> filteredName = optName.filter(name -> name.startsWith("홍"));

    // Then
    assertAll(
        () -> assertTrue(filteredName.isPresent(), "Optional 값이 존재합니다"),
        () -> assertEquals("홍길동", filteredName.get(), "이름이 일치합니다")
    );
  }

  @Order(8)
  @DisplayName("Optional.filter 테스트: 값이 있는 경우 조건을 만족하지 않음")
  @Test
  void testFilterNotPresent() {

    // Given
    UserDto user = UserDto.builder()
        .name("김철수")
        .email("cheolsu_kim@gmail.com")
        .age(35)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When
    Optional<String> filteredName = optName.filter(name -> name.startsWith("홍"));

    // Then
    assertTrue(filteredName.isEmpty(), "조건을 만족하지 않아 값이 없습니다");
  }

  @Order(9)
  @DisplayName("Optional.map 테스트: 값이 있는 경우 문자열 길이 변환")
  @Test
  void testMapPresent() {

    // Given
    UserDto user = UserDto.builder()
        .name("홍길동")
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When
    Optional<Integer> nameLength = optName.map(String::length);

    // Then
    assertAll(
        () -> assertTrue(nameLength.isPresent(), "Optional 값이 존재합니다"),
        () -> assertEquals(3, nameLength.get(), "이름의 길이가 일치합니다")
    );
  }

  @Order(10)
  @DisplayName("Optional.map 테스트: 값이 없는 경우")
  @Test
  void testMapNotPresent() {

    // Given
    UserDto user = UserDto.builder()
        .email("gildong_hong@gmail.com")
        .age(30)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When
    Optional<Integer> nameLength = optName.map(String::length);

    // Then
    assertTrue(nameLength.isEmpty(), "Optional 값이 없습니다");
  }

  @Builder
  @Getter
  @Setter
  @ToString
  static class UserDto {

    private String name;
    private String email;
    private int age;
  }
}
