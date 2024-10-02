# [JAVA] Optional 클래스 사용 방법

## 소개

Java 8에서 도입된 `Optional` 클래스는 값이 있을 수도, 없을 수도 있는 상황을 표현하는 컨테이너 객체입니다. 이를 활용하면 `null`을 직접 다루는 대신 명시적으로 존재 여부를 처리할 수 있습니다. 이번 포스트에서는 `Optional` 클래스의 다양한 메서드를 활용한 테스트 예제를 살펴보겠습니다.

### 주요 메서드

- **`Optional.ofNullable(T value)`**: 주어진 값이 `null`일 수 있는 경우 이 메서드를 사용하여 `Optional` 인스턴스를 생성할 수 있습니다. 값이 `null`인 경우 비어 있는 `Optional`을 반환합니다.
- **`Optional.orElse(T other)`**: `Optional`이 비어 있을 때 대체값을 반환합니다. 값이 존재하면 해당 값을 반환하고, 그렇지 않으면 제공한 대체값을 반환합니다.
- **`Optional.orElseThrow(Supplier<? extends X> exceptionSupplier)`**: `Optional`이 비어 있을 때 지정된 예외를 던집니다. 값이 존재하면 값을 반환하고, 비어 있으면 제공한 예외를 던집니다.
- **`Optional.ifPresent(Consumer<? super T> consumer)`**: 값이 존재하는 경우 지정된 동작을 수행합니다. 값이 없는 경우 아무 일도 발생하지 않습니다.
- **`Optional.filter(Predicate<? super T> predicate)`**: 값이 존재하고 주어진 조건을 만족하는 경우 그 값을 포함하는 `Optional`을 반환합니다. 그렇지 않으면 비어 있는 `Optional`을 반환합니다.
- **`Optional.map(Function<? super T, ? extends U> mapper)`**: 값이 존재하는 경우 이를 매핑 함수에 적용하여 새로운 값을 포함하는 `Optional`을 반환합니다. 값이 없으면 비어 있는 `Optional`을 반환합니다.
- **`Optional.isPresent()`**: 값이 존재하는지 확인합니다. `true`면 값이 존재하고, `false`면 그렇지 않습니다.
- **`Optional.isEmpty()`**: 값이 없는지 확인합니다. `true`면 값이 없고, `false`면 값이 존재합니다.

이제 이러한 메서드들을 실제로 어떻게 사용하는지 살펴보기 위해 `Optional` 클래스의 다양한 메서드를 활용한 테스트 예제를 살펴보겠습니다.

### 1. Optional.ofNullable 테스트: 값이 있는 경우
주어진 객체에 이름 `홍길동`이 존재할 때, `Optional.ofNullable`을 이용해 Optional로 감싸고 그 값이 존재함을 검증합니다.

```java
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
```

### 2. Optional.ofNullable 테스트: 값이 없는 경우
이름이 없는 객체에 대해 `Optional.ofNullable`을 호출하여 Optional의 값이 비어있음을 검증합니다.

```java
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
```

### 3. Optional.orElse 테스트
이름이 없는 경우 기본값을 제공하는 `orElse` 메서드를 테스트합니다.

```java
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
```

### 4. Optional.orElseThrow 테스트: 값이 없는 경우 예외 발생
이름이 없는 경우 예외를 발생시키는 `orElseThrow` 메서드를 테스트합니다.

```java
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
```

### 5. Optional.orElseThrow 테스트: 값이 있는 경우
이름이 존재하는 경우 예외를 발생시키지 않고 값을 반환하는 `orElseThrow` 메서드를 테스트합니다.

```java
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
```

### 6. Optional.ifPresent 테스트: 값을 더하는 경우
이름이 존재하는 경우 문자열 빌더에 값을 추가하는 `ifPresent` 메서드를 테스트합니다.

```java
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
```

### 7. Optional.filter 테스트: 조건을 만족하는 경우
이름이 "김"으로 시작하는 경우만 필터링하는 `filter` 메서드를 테스트합니다.

```java
  @Order(7)
  @DisplayName("Optional.filter 테스트: 조건을 만족하는 경우")
  @Test
  void testFilterPresent() {

    // Given
    UserDto user = UserDto.builder()
        .name("김철수")
        .email("cheolsu_kim@gmail.com")
        .age(25)
        .build();
    Optional<String> optName = Optional.ofNullable(user.getName());

    // When
    Optional<String> filteredName = optName.filter(name -> name.startsWith("김"));

    // Then
    assertAll(
        () -> assertTrue(filteredName.isPresent(), "조건에 맞는 Optional 값이 존재합니다"),
        () -> assertEquals("김철수", filteredName.get(), "필터링 된 이름이 일치합니다")
    );
  }
```

### 8. Optional.filter 테스트: 값이 있는 경우 조건을 만족하지 않음
이름이 "홍"으로 시작하지 않는 경우 필터링된 값이 없음을 검증합니다.

```java
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
```

### 9. Optional.map 테스트: 값이 있는 경우 문자열 길이 변환
이름이 존재할 때, 그 문자열의 길이를 계산하여 Optional로 반환하는 `map` 메서드를 테스트합니다.

```java
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
```

### 10. Optional.map 테스트: 값이 없는 경우
이름이 없는 경우 `map` 메서드를 호출해도 값이 없음을 검증합니다.

```java
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
```

## 요약

Java 8의 `Optional` 클래스를 사용하여 값의 존재 여부를 명시적으로 처리하고 다양한 상황에 따라 적절하게 대응할 수 있습니다. 이 예제는 `Optional` 클래스의 다양한 메서드 활용법을 실제 테스트 코드로 보여줌으로써, 코드의 안정성과 가독성을 높이는 방법을 제시합니다.

## 추가 자료

- [Java 8 공식 문서 - java.util.Optional](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
- [Baeldung: Java 8 Optional 사용법](https://www.baeldung.com/java-optional)
