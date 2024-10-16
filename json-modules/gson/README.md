# Gson 라이브러리를 사용한 JSON 데이터 처리
Java에서 JSON 데이터를 처리할 때 가장 널리 사용되는 라이브러리 중 하나가 바로 Gson입니다. Google에서 개발한 Gson은 유연하고 사용이 간편한 JSON 파싱 및 직렬화/역직렬화 라이브러리입니다. 이번 글에서는 Gson 라이브러리를 사용하여 JSON 데이터를 처리하는 다양한 방법을 예제로 설명하겠습니다.

## Gson 라이브러리 설정
우선 Gradle 프로젝트에서 Gson 라이브러리를 사용하기 위해 `build.gradle` 파일에 다음과 같이 Gson 라이브러리를 추가합니다.

```gradle
dependencies {
    implementation 'com.google.code.gson:gson:2.10'
}
```

## Gson에서 자주 사용하는 메서드
Gson 라이브러리를 사용할 때 자주 사용하는 주요 메서드는 다음과 같습니다.

### `Gson.fromJson`
- JSON 문자열을 지정된 Java 객체로 역직렬화합니다.

### `Gson.toJson`
- Java 객체를 JSON 문자열로 직렬화합니다.

### `JsonParser.parseString`
- JSON 문자열을 `JsonElement`로 파싱합니다.

### `JsonElement.getAsJsonObject`
- `JsonElement`를 `JsonObject`로 변환합니다.

### `JsonObject.addProperty`
- `JsonObject`에 새로운 속성을 추가하거나 기존 속성 값을 업데이트합니다.

### `JsonObject.add`
- `JsonObject`에 `JsonElement`를 추가합니다.

### `JsonObject.remove`
- `JsonObject`에서 지정된 속성을 제거합니다.

### `Gson.toJsonTree`
- Java 객체를 `JsonElement`로 변환합니다. 주로 JSON 배열을 처리할 때 유용합니다.

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

## Gson 라이브러리를 사용한 JSON 처리 방법

### 1. Gson 라이브러리로 JSON 문자열을 객체로 역직렬화
Gson을 사용하면 `Gson`의 `fromJson` 메서드를 통해 JSON 문자열을 쉽게 Java 객체로 변환할 수 있습니다.

```java
@DisplayName("JSON 문자열을 객체로 역직렬화")
@Test
public void testDeserializeJsonStringToObject() {

  // Given & When
  StoreWrapper storeWrapper = gson.fromJson(json, StoreWrapper.class);

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
Java 객체를 JSON 문자열로 변환(직렬화)하는 방법입니다. 이를 위해 `Gson`의 `toJson` 메서드를 사용합니다.

```java
@DisplayName("객체를 JSON 문자열로 직렬화")
@Test
public void testSerializeObjectToJsonString() {

  // Given
  StoreWrapper storeWrapper = gson.fromJson(json, StoreWrapper.class);

  // When
  String serializedJson = gson.toJson(storeWrapper);

  // Then
  log.debug("serializedJson : {}", serializedJson);
  assertAll(
      () -> assertNotNull(serializedJson),
      () -> assertTrue(serializedJson.contains("store"))
  );
}
```

### 3. JSON 데이터 수정
특정 JSON 속성의 값을 수정하는 예제입니다. 이를 위해 `JsonObject`의 `addProperty` 메서드를 사용합니다.

```java
@DisplayName("JSON 데이터 수정")
@Test
public void testModifyJsonData() {

  // Given
  JsonElement rootElement = JsonParser.parseString(json);
  JsonObject modifiedRoot = rootElement.getAsJsonObject();

  // When
  // 특정 속성 값 수정
  modifiedRoot.addProperty("expensive", 15);
  JsonElement modifiedElement = modifiedRoot.get("expensive");

  // JSON 문자열로 변환
  String modifiedJson = gson.toJson(modifiedRoot);

  // Then
  log.debug("modifiedJson : {}", modifiedJson);
  assertAll(
      () -> assertEquals(15, modifiedElement.getAsInt()),
      () -> assertNotNull(modifiedJson),
      () -> assertTrue(modifiedJson.contains("\"expensive\":15"))
  );
}
```

### 4. JSON 데이터 병합
두 개의 JSON 객체를 병합하는 방법입니다. `JsonObject`의 `add` 메서드를 사용합니다.

```java
@DisplayName("JSON 데이터 병합")
@Test
public void testMergeJsonData() {

  // Given
  String additionalJson = "{\"newProperty\":\"newValue\"}";
  JsonElement originalElement = JsonParser.parseString(json);
  JsonElement additionalElement = JsonParser.parseString(additionalJson);
  JsonObject mergedNode = originalElement.getAsJsonObject();

  // When
  mergedNode.add("newProperty", additionalElement.getAsJsonObject().get("newProperty"));

  // JSON 문자열로 변환
  String mergedJson = gson.toJson(mergedNode);

  // Then
  log.debug("mergedJson : {}", mergedJson);
  assertAll(
      () -> assertNotNull(mergedJson),
      () -> assertTrue(mergedJson.contains("\"newProperty\":\"newValue\""))
  );
}
```

### 5. JSON 속성 추출
특정 JSON 속성을 추출하여 사용하는 방법입니다. `JsonObject`의 `get` 메서드를 사용합니다.

```java
@DisplayName("JSON 속성 추출")
@Test
public void testExtractJsonProperty() {

  // Given & When
  JsonElement rootElement = JsonParser.parseString(json);
  JsonElement expensiveElement = rootElement.getAsJsonObject().get("expensive");

  // Then
  log.debug("expensive : {}", expensiveElement.getAsInt());
  assertAll(
      () -> assertNotNull(expensiveElement),
      () -> assertEquals(10, expensiveElement.getAsInt())
  );
}
```

### 6. JSON 속성 추가
새로운 JSON 속성을 추가하는 방법입니다. `JsonObject`의 `addProperty` 메서드를 사용합니다.

```java
@DisplayName("JSON 속성 추가")
@Test
public void testAddJsonProperty() {

  // Given
  JsonElement rootElement = JsonParser.parseString(json);
  JsonObject modifiedRoot = rootElement.getAsJsonObject();

  // When
  // 속성 추가
  modifiedRoot.addProperty("newProperty", "newValue");

  // JSON 문자열로 변환
  String modifiedJson = gson.toJson(modifiedRoot);

  // Then
  log.debug("modifiedJson : {}", modifiedJson);
  assertAll(
      () -> assertNotNull(modifiedRoot),
      () -> assertEquals("newValue", modifiedRoot.get("newProperty").getAsString())
  );
}
```

### 7. JSON 속성 삭제
특정 JSON 속성을 삭제하는 방법입니다. `JsonObject`의 `remove` 메서드를 사용합니다.

```java
@DisplayName("JSON 속성 삭제")
@Test
public void testRemoveJsonProperty() {

  // Given
  JsonElement rootElement = JsonParser.parseString(json);
  JsonObject modifiedRoot = rootElement.getAsJsonObject();

  // When
  // 속성 삭제
  modifiedRoot.remove("expensive");

  // JSON 문자열로 변환
  String modifiedJson = gson.toJson(modifiedRoot);

  // Then
  log.debug("modifiedJson : {}", modifiedJson);
  assertAll(
      () -> assertNotNull(modifiedRoot),
      () -> assertFalse(modifiedRoot.has("expensive"))
  );
}
```

### 8. JSON 배열 처리
JSON 배열을 처리하는 방법을 알아보겠습니다. `Gson`의 `toJsonTree` 메서드를 사용하여 객체를 `JsonArray`로 변환할 수 있습니다.

```java
@DisplayName("JSON 배열 처리")
@Test
public void testHandleJsonArray() {

  // Given
  StoreWrapper storeWrapper = gson.fromJson(json, StoreWrapper.class);
  List<Book> books = storeWrapper.getStore().getBook();

  // When
  JsonArray bookArray = gson.toJsonTree(books).getAsJsonArray();

  // Then
  assertTrue(bookArray.isJsonArray());
  for (JsonElement bookElement : bookArray) {
    JsonObject bookObject = bookElement.getAsJsonObject();
    log.debug("Book: {}", bookObject);
    assertNotNull(bookObject.get("category"));
    assertNotNull(bookObject.get("author"));
    assertNotNull(bookObject.get("title"));
    assertNotNull(bookObject.get("price"));
    if (bookObject.has("isbn")) {
      assertNotNull(bookObject.get("isbn"));
    }
  }
}
```

## 결론
Gson 라이브러리를 사용하여 JSON 데이터를 처리하는 다양한 방법에 대해 알아보았습니다. Gson 라이브러리는 매우 강력하면서도 사용하기 쉬운 JSON 처리를 위한 도구입니다. 필요에 맞게 Gson 라이브러리를 활용하여 JSON 데이터를 효과적으로 처리할 수 있습니다.

## 참고 자료
- [Gson 공식 GitHub 리포지토리](https://github.com/google/gson)
- [Gson 사용자 가이드](https://github.com/google/gson/blob/master/UserGuide.md)
