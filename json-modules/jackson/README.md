# Jackson 라이브러리를 사용한 JSON 데이터 처리
Java에서 JSON 데이터를 처리할 때 가장 널리 사용되는 라이브러리 중 하나가 바로 Jackson 입니다. Jackson은 빠르고 유연하며 강력한 JSON 파싱 및 직렬화/역직렬화 기능을 제공합니다. 이번 글에서는 Jackson 라이브러리를 사용하여 JSON 데이터를 처리하는 다양한 방법을 예제를 통해 설명하겠습니다.

## Jackson 라이브러리 설정

우선, Gradle 프로젝트에서 Jackson 라이브러리를 사용하기 위해 `build.gradle` 파일에 다음과 같이 Jackson 라이브러리를 추가합니다.

```gradle
dependencies {
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.18.0'
    implementation 'com.fasterxml.jackson.core:jackson-core:2.18.0'
}
```

## Jackson에서 자주 사용하는 메서드
Jackson 라이브러리를 사용할 때 자주 사용하는 주요 메서드는 다음과 같습니다.

### `ObjectMapper.readValue`
- JSON 문자열을 지정된 Java 객체로 역직렬화합니다.

### `ObjectMapper.writeValueAsString`
- Java 객체를 JSON 문자열로 직렬화합니다.

### `ObjectMapper.readTree`
- JSON 문자열을 `JsonNode` 트리 구조로 파싱합니다.

### `JsonNode.path`
- 지정된 경로의 하위 노드를 반환합니다. 해당 경로에 존재하지 않으면 "missing node"를 반환합니다.

### `ObjectNode.put`
- `ObjectNode`에 새로운 필드를 추가하거나 기존 필드 값을 업데이트합니다.

### `ObjectNode.setAll`
- 주어진 `ObjectNode`의 모든 필드를 현재 `ObjectNode`에 추가합니다.

### `ObjectNode.remove`
- `ObjectNode`에서 지정된 필드를 제거합니다.

### `ObjectMapper.valueToTree`
- Java 객체를 `JsonNode`로 변환합니다. 주로 JSON 배열을 처리할 때 유용합니다.

이 메서드들은 Jackson을 사용할 때 데이터의 직렬화 및 역직렬화, JSON 데이터 수정 및 탐색 등에 매우 유용합니다. 다음 부분에서는 이러한 메서드를 사용하여 JSON 데이터를 처리하는 다양한 예제를 소개하겠습니다.

## JSON 파일 예제: `store.json`
이번 글에서 사용할 `store.json` 파일의 예시는 다음과 같습니다:

```json
{
  "store": {
    "book": [
      {
        "category": "reference",
        "author": "Nigel Rees",
        "title": "Sayings of the Century",
        "price": 8.95
      },
      {
        "category": "fiction",
        "author": "Evelyn Waugh",
        "title": "Sword of Honour",
        "price": 12.99
      },
      {
        "category": "fiction",
        "author": "Herman Melville",
        "title": "Moby Dick",
        "isbn": "0-553-21311-3",
        "price": 8.99
      },
      {
        "category": "fiction",
        "author": "J. R. R. Tolkien",
        "title": "The Lord of the Rings",
        "isbn": "0-395-19395-8",
        "price": 22.99
      }
    ],
    "bicycle": {
      "color": "red",
      "price": 19.95
    }
  },
  "expensive": 10
}

```

## Jackson 라이브러리를 사용한 JSON 처리 방법

### 1. JSON 문자열을 객체로 역직렬화
Jackson을 사용하면 `ObjectMapper`의 `readValue` 메서드를 통해 JSON 문자열을 쉽게 Java 객체로 변환할 수 있습니다.

```java
@DisplayName("JSON 문자열을 객체로 역직렬화")
@Test
public void testDeserializeJsonStringToObject() throws IOException {

  // Given & When
  StoreWrapper storeWrapper = objectMapper.readValue(json, StoreWrapper.class);

  // Then
  log.debug("storeWrapper: {}", storeWrapper);
  assertAll(
      () -> assertNotNull(storeWrapper),
      () -> assertNotNull(storeWrapper.getStore()),
      () -> assertNotNull(storeWrapper.getStore().getBook()),
      () -> assertNotNull(storeWrapper.getStore().getBicycle())
  );
}
```

### 2. 객체를 JSON 문자열로 직렬화
Java 객체를 JSON 문자열로 변환(직렬화)하는 방법입니다. 이를 위해 `ObjectMapper`의 `writeValueAsString` 메서드를 사용합니다.

```java
@DisplayName("객체를 JSON 문자열로 직렬화")
@Test
public void testSerializeObjectToJsonString() throws IOException {

  // Given
  StoreWrapper storeWrapper = objectMapper.readValue(json, StoreWrapper.class);

  // When
  String serializedJson = objectMapper.writeValueAsString(storeWrapper);

  // Then
  log.debug("serializedJson : {}", serializedJson);
  assertAll(
      () -> assertNotNull(serializedJson),
      () -> assertTrue(serializedJson.contains("store"))
  );
}
```

### 3. JSON 데이터 수정
특정 JSON 노드의 값을 수정하는 예제입니다. 이를 위해 `ObjectNode`의 `put` 메서드를 사용합니다.

```java
@DisplayName("JSON 데이터 수정")
@Test
public void testModifyJsonData() throws IOException {

  // Given
  JsonNode rootNode = objectMapper.readTree(json);
  ObjectNode modifiedRoot = (ObjectNode) rootNode;

  // When
  // 특정 노드 값 수정
  modifiedRoot.put("expensive", 15);
  JsonNode modifiedNode = modifiedRoot.path("expensive");

  // JSON 문자열로 변환
  ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
  String modifiedJson = writer.writeValueAsString(modifiedRoot);

  // Then
  log.debug("modifiedJson : {}", modifiedJson);
  assertAll(
      () -> assertEquals(15, modifiedNode.asInt()),
      () -> assertNotNull(modifiedJson),
      () -> assertTrue(modifiedJson.contains("\"expensive\" : 15"))
  );
}
```

### 4. JSON 데이터 병합
두 개의 JSON 객체를 병합하는 방법입니다. `ObjectNode`의 `setAll` 메서드를 사용하면 병합할 수 있습니다.

```java
@DisplayName("JSON 데이터 병합")
@Test
public void testMergeJsonData() throws IOException {

  // Given
  String additionalJson = "{\"newProperty\":\"newValue\"}";
  JsonNode originalNode = objectMapper.readTree(json);
  JsonNode additionalNode = objectMapper.readTree(additionalJson);
  ObjectNode mergedNode = (ObjectNode) ((ObjectNode) originalNode)
      .putAll((ObjectNode) additionalNode);

  // When
  String mergedJson = objectMapper.writeValueAsString(mergedNode);

  // Then
  log.debug("mergedJson : {}", mergedJson);
  assertAll(
      () -> assertNotNull(mergedJson),
      () -> assertTrue(mergedJson.contains("\"newProperty\":\"newValue\""))
  );
}
```

### 5. JSON 노드 추출
특정 JSON 노드를 추출하여 사용하는 방법입니다. `JsonNode`의 `path` 메서드를 사용합니다.

```java
@DisplayName("JSON 노드 추출")
@Test
public void testExtractJsonNode() throws IOException {

  // Given & When
  JsonNode rootNode = objectMapper.readTree(json);
  JsonNode expensiveNode = rootNode.path("expensive");

  // Then
  log.debug("expensive : {}", expensiveNode.asInt());
  assertAll(
      () -> assertNotNull(expensiveNode),
      () -> assertEquals(10, expensiveNode.asInt())
  );
}
```

### 6. JSON 노드 추가
새로운 JSON 노드를 추가하는 방법입니다. `ObjectNode`의 `put` 메서드를 사용합니다.

```java
@DisplayName("JSON 노드 추가")
@Test
public void testAddJsonNode() throws IOException {

  // Given
  JsonNode rootNode = objectMapper.readTree(json);
  ObjectNode modifiedRoot = (ObjectNode) rootNode;

  // When
  // 노드 추가
  modifiedRoot.put("newProperty", "newValue");

  // JSON 문자열로 변환
  ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
  String modifiedJson = writer.writeValueAsString(modifiedRoot);

  // Then
  log.debug("modifiedJson : {}", modifiedJson);
  assertAll(
      () -> assertNotNull(modifiedRoot),
      () -> assertEquals("newValue", modifiedRoot.path("newProperty").asText())
  );
}
```

### 7. JSON 노드 삭제
특정 JSON 노드를 삭제하는 방법입니다. `ObjectNode`의 `remove` 메서드를 사용합니다.

```java
@DisplayName("JSON 노드 삭제")
@Test
public void testRemoveJsonNode() throws IOException {

  // Given
  JsonNode rootNode = objectMapper.readTree(json);
  ObjectNode modifiedRoot = (ObjectNode) rootNode;

  // When
  // 노드 삭제
  modifiedRoot.remove("expensive");

  // JSON 문자열로 변환
  ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
  String modifiedJson = writer.writeValueAsString(modifiedRoot);

  // Then
  log.debug("modifiedJson : {}", modifiedJson);
  assertAll(
      () -> assertNotNull(modifiedRoot),
      () -> assertTrue(modifiedRoot.path("expensive").isMissingNode())
  );
}
```

### 8. JSON 배열 처리
JSON 배열을 처리하는 방법을 알아보겠습니다. `ObjectMapper`의 `valueToTree` 메서드를 사용하여 객체를 `ArrayNode`로 변환할 수 있습니다.

```java
@DisplayName("JSON 배열 처리")
@Test
public void testHandleJsonArray() throws IOException {

  // Given
  StoreWrapper storeWrapper = objectMapper.readValue(json, StoreWrapper.class);
  List<Book> books = storeWrapper.getStore().getBook();

  // When
  ArrayNode bookArray = objectMapper.valueToTree(books);

  // Then
  assertTrue(bookArray.isArray());
  for (JsonNode bookNode : bookArray) {
    log.debug("Book: {}", bookNode);
    assertNotNull(bookNode.get("category"));
    assertNotNull(bookNode.get("author"));
    assertNotNull(bookNode.get("title"));
    assertNotNull(bookNode.get("price"));
    assertNotNull(bookNode.get("isbn"));
  }
}
```

## 결론
Jackson 라이브러리를 사용하여 JSON 데이터를 처리하는 다양한 방법에 대해 알아보았습니다. Jackson 라이브러리는 매우 강력하면서도 사용하기 쉬운 JSON 처리를 위한 도구입니다.

## 참고 자료
- [Jackson Databind GitHub](https://github.com/FasterXML/jackson-databind)
- [Baeldung Jackson Tutorials](https://www.baeldung.com/jackson)
