# Jackson 라이브러리를 이용한 형변환
Jackson 라이브러리를 사용하여 JSON 데이터를 Java 객체로 변환하거나, Java 객체를 JSON 데이터로 변환하는 유틸리티 메서드를 제공합니다. 이 클래스는 다양한 형식의 JSON 데이터를 처리할 수 있는 간편하고 확장 가능한 기능을 제공합니다.

## Jackson 라이브러리 설정
우선, Gradle 프로젝트에서 Jackson 라이브러리를 사용하기 위해 `build.gradle` 파일에 다음과 같이 Jackson 라이브러리를 추가합니다.

```gradle
dependencies {
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.18.0'
    implementation 'com.fasterxml.jackson.core:jackson-core:2.18.0'
}
```

## JsonUtils 메서드 설명
### 1. JSON 문자열을 객체로 변환
`readValue` 메서드는 JSON 문자열을 파싱하여 지정된 클래스 타입의 Java 객체로 변환합니다. 해당 메서드는 `jsonString`을 받아 `clazz` 타입의 객체로 변환하고 있습니다.

```java
/**
 * JSON 문자열을 객체로 변환
 *
 * @param jsonString 변환할 JSON 문자열
 * @param clazz      변환할 객체의 클래스 타입
 * @param <T>        변환할 객체의 타입
 * @return JSON 문자열을 변환한 객체
 * @throws IOException JSON 처리를 위한 IO 예외
 */
public static <T> T fromJsonString(String jsonString, Class<T> clazz) throws IOException {
  return objectMapper.readValue(jsonString, clazz);
}
```

테스트:

```java
@DisplayName("JSON 문자열을 객체로 변환")
@Test
public void testFromJsonString() throws IOException {

  // Given
  String personJson = "{\"name\":\"홍길동\",\"age\":30}";

  // When
  Person result = JsonUtils.fromJsonString(personJson, Person.class);

  // Then
  assertAll(
      () -> assertNotNull(result),
      () -> assertEquals("홍길동", result.getName()),
      () -> assertEquals(30, result.getAge())
  );
}
```

### 2. 객체를 JSON 문자열로 변환
`writeValueAsString` 메서드는 Java 객체를 JSON 문자열로 직렬화합니다. 해당 메서드는 `object`를 받아 JSON 문자열로 변환하고 있습니다.

```java
/**
 * 객체를 JSON 문자열로 변환
 *
 * @param object 변환할 객체
 * @param <T>    변환할 객체의 타입
 * @return 객체를 변환한 JSON 문자열
 * @throws JsonProcessingException JSON 처리 예외
 */
public static <T> String toJsonString(T object) throws JsonProcessingException {
  return objectMapper.writeValueAsString(object);
}
```

테스트:

```java
@DisplayName("객체를 JSON 문자열로 변환")
@Test
public void testToJsonString() throws JsonProcessingException {

  // Given
  Person person = new Person("홍길동", 30);

  // When
  String jsonString = JsonUtils.toJsonString(person);

  // Then
  assertAll(
      () -> assertNotNull(jsonString),
      () -> assertTrue(jsonString.contains("\"name\":\"홍길동\"")),
      () -> assertTrue(jsonString.contains("\"age\":30"))
  );
}
```

### 3. Map을 JSON 문자열로 변환
`writeValueAsString` 메서드는 `Map` 객체를 JSON 문자열로 직렬화합니다. 해당 메서드는 `map` 객체를 받아 JSON 문자열로 변환하고 있습니다.

```java
/**
 * Map을 JSON 문자열로 변환
 *
 * @param map 변환할 Map 객체
 * @return Map을 변환한 JSON 문자열
 * @throws JsonProcessingException JSON 처리 예외
 */
public static String mapToJsonString(Map<String, Object> map) throws JsonProcessingException {
  return objectMapper.writeValueAsString(map);
}
```

테스트:

```java
@DisplayName("Map을 JSON 문자열로 변환")
@Test
public void testMapToJsonString() throws JsonProcessingException {

  // Given
  Map<String, Object> personMap = new HashMap<>();
  personMap.put("name", "홍길동");
  personMap.put("age", 30);

  // When
  String jsonString = JsonUtils.mapToJsonString(personMap);

  // Then
  assertAll(
      () -> assertNotNull(jsonString),
      () -> assertTrue(jsonString.contains("\"name\":\"홍길동\"")),
      () -> assertTrue(jsonString.contains("\"age\":30"))
  );
}
```

### 4. List<Map>을 JSON 문자열로 변환
`writeValueAsString` 메서드는 `List<Map>` 객체를 JSON 문자열로 직렬화합니다. 해당 메서드는 `list` 객체를 받아 JSON 문자열로 변환하고 있습니다.

```java
/**
 * List<Map>을 JSON 문자열로 변환
 *
 * @param list 변환할 List<Map> 객체
 * @return List<Map>을 변환한 JSON 문자열
 * @throws JsonProcessingException JSON 처리 예외
 */
public static String listOfMapToJsonString(List<Map<String, Object>> list)
    throws JsonProcessingException {
  return objectMapper.writeValueAsString(list);
}
```

테스트:

```java
@DisplayName("List<Map>을 JSON 문자열로 변환")
@Test
public void testListOfMapToJsonString() throws JsonProcessingException {

  // Given
  Map<String, Object> personMap = new HashMap<>();
  personMap.put("name", "홍길동");
  personMap.put("age", 30);
  List<Map<String, Object>> givenListOfMap = List.of(personMap);

  // When
  String jsonString = JsonUtils.listOfMapToJsonString(givenListOfMap);

  // Then
  assertAll(
      () -> assertNotNull(jsonString),
      () -> assertTrue(jsonString.contains("\"name\":\"홍길동\"")),
      () -> assertTrue(jsonString.contains("\"age\":30"))
  );
}
```

### 5. JSON 문자열을 Map으로 변환
`readValue` 메서드는 JSON 문자열을 파싱하여 `Map<String, Object>` 객체로 변환합니다. 해당 메서드는 `jsonString`을 받아 `Map<String, Object>` 타입의 객체로 변환하고 있습니다.

```java
/**
 * JSON 문자열을 Map으로 변환
 *
 * @param jsonString 변환할 JSON 문자열
 * @return JSON 문자열을 변환한 Map 객체
 * @throws IOException JSON 처리를 위한 IO 예외
 */
public static Map<String, Object> jsonStringToMap(String jsonString) throws IOException {
  return objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
}
```

테스트:

```java
@DisplayName("JSON 문자열을 Map으로 변환")
@Test
public void testJsonStringToMap() throws IOException {

  // Given
  String personJson = "{\"name\":\"홍길동\",\"age\":30}";

  // When
  Map<String, Object> result = JsonUtils.jsonStringToMap(personJson);

  // Then
  assertAll(
      () -> assertNotNull(result),
      () -> assertEquals("홍길동", result.get("name")),
      () -> assertEquals(30, result.get("age"))
  );
}
```

### 6. JSON 문자열을 List로 변환
`readValue` 메서드는 JSON 문자열을 파싱하여 지정된 클래스 타입의 객체로 변환합니다. 해당 메서드는 `jsonString`을 받아 `List<T>` 타입으로 변환합니다.

```java
/**
 * JSON 문자열을 List로 변환
 *
 * @param jsonString 변환할 JSON 문자열
 * @param clazz      변환할 객체의 클래스 타입
 * @param <T>        변환할 객체의 타입
 * @return JSON 문자열을 변환한 List<T> 객체
 * @throws IOException JSON 처리를 위한 IO 예외
 */
public static <T> List<T> jsonStringToList(String jsonString, Class<T> clazz) throws IOException {
  return objectMapper.readValue(
      jsonString,
      objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
}
```

테스트:

```java
@DisplayName("JSON 문자열을 List로 변환")
@Test
public void testJsonStringToList() throws IOException {

  // Given
  String jsonArrayString = "[{\"name\":\"홍길동\",\"age\":30}, {\"name\":\"이순신\",\"age\":45}]";

  // When
  List<Person> result = JsonUtils.jsonStringToList(jsonArrayString, Person.class);

  // Then
  assertAll(
      () -> assertNotNull(result),
      () -> assertEquals(2, result.size()),
      () -> {
        Person person1 = result.get(0);
        assertEquals("홍길동", person1.getName());
        assertEquals(30, person1.getAge());
      },
      () -> {
        Person person2 = result.get(1);
        assertEquals("이순신", person2.getName());
        assertEquals(45, person2.getAge());
      }
  );
}
```

### 7. JSONObject를 Map<String, String>으로 변환
`collect(Collectors.toMap())` 메서드를 사용하여 각 엔트리의 값을 문자열로 변환하여 새 `Map<String, String>` 객체를 생성합니다.

```java
/**
 * JSONObject를 Map<String, String>으로 변환
 *
 * @param jsonObject 변환할 Map<String, Object> 객체
 * @return Map<String, Object>을 변환한 Map<String, String> 객체
 */
public static Map<String, String> jsonObjectToMap(Map<String, Object> jsonObject) {
  return jsonObject.entrySet().stream()
      .collect(Collectors.toMap(
          Map.Entry::getKey, entry -> entry.getValue().toString()
      ));
}
```

테스트:

```java
@DisplayName("JSONObject를 Map<String, String>으로 변환")
@Test
public void testJsonObjectToMap() {

  // Given
  Map<String, Object> personMap = new HashMap<>();
  personMap.put("name", "홍길동");
  personMap.put("age", 30);

  // When
  Map<String, String> resultMap = JsonUtils.jsonObjectToMap(personMap);

  // Then
  assertAll(
      () -> assertNotNull(resultMap),
      () -> assertEquals("홍길동", resultMap.get("name")),
      () -> assertEquals("30", resultMap.get("age"))
  );
}
```

### 8. JSON 배열을 List<Map<String, String>>으로 변환
`readValue` 메서드는 JSON 배열 문자열을 파싱하여 `List<Map<String, String>>` 객체로 변환합니다. 해당 메서드는 `jsonArrayString`을 받아 `List<Map<String, String>>` 타입의 객체로 변환하고 있습니다.

```java
/**
 * JSON 배열을 List<Map<String, String>>으로 변환
 *
 * @param jsonArrayString 변환할 JSON 배열 문자열
 * @return JSON 배열 문자열을 변환한 List<Map<String, String>> 객체
 * @throws IOException JSON 처리를 위한 IO 예외
 */
public static List<Map<String, String>> jsonArrayToListOfMap(String jsonArrayString)
    throws IOException {
  return objectMapper.readValue(
      jsonArrayString,
      TypeFactory.defaultInstance().constructCollectionType(List.class,
          TypeFactory.defaultInstance().constructMapType(Map.class, String.class, String.class))
  );
}
```

테스트:

```java
@DisplayName("JSON 배열을 List<Map<String, String>>으로 변환")
@Test
public void testJsonArrayToListOfMap() throws IOException {

  // Given
  String givenJsonArrayString = "[{ \"name\": \"홍길동\", \"age\": 30 }]";

  // When
  List<Map<String, String>> result = JsonUtils.jsonArrayToListOfMap(givenJsonArrayString);

  // Then
  assertAll(
      () -> assertNotNull(result),
      () -> assertEquals(1, result.size()),
      () -> assertEquals("홍길동", result.get(0).get("name")),
      () -> assertEquals("30", result.get(0).get("age"))
  );
}
```
