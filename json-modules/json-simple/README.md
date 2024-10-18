# json-simple 라이브러리를 사용한 JSON 데이터 처리
Java에서 JSON 데이터를 처리할 때 json-simple 라이브러리가 있습니다. 이번 글에서는 json-simple 라이브러리를 사용하여 JSON 데이터를 처리하는 다양한 방법을 예제를 통해 설명하겠습니다.

## json-simple 라이브러리 설정
우선, Gradle 프로젝트에서 json-simple 라이브러리를 사용하기 위해 `build.gradle` 파일에 다음과 같이 json-simple 라이브러리를 추가합니다.

```gradle
dependencies {
    implementation 'com.googlecode.json-simple:json-simple:1.1.1'
}
```

## json-simple에서 자주 사용하는 메서드
json-simple 라이브러리를 사용할 때 자주 사용하는 주요 메서드는 다음과 같습니다.

### `JSONParser.parse(Reader reader)`
- 파일 리더로부터 JSON 데이터를 파싱합니다.

### `JSONParser.parse(String jsonString)`
- JSON 문자열을 파싱합니다.

### `JSONObject.put(String key, Object value)`
- 주어진 키에 해당하는 값을 저장합니다.

### `JSONObject.get(String key)`
- 주어진 키에 해당하는 값을 가져옵니다.

### `JSONObject.remove(String key)`
- 주어진 키에 해당하는 값을 제거합니다.

### `JSONObject.toJSONString()`
- `JSONObject`를 JSON 문자열로 변환합니다.

### `JSONArray.add(Object value)`
- 값을 배열에 추가합니다.

### `JSONArray.get(int index)`
- 주어진 인덱스에 해당하는 값을 가져옵니다.

### `JSONArray.size()`
- 배열의 크기를 반환합니다.

## JSON 파일 예제: `store.json`
사용할 `store.json` 파일의 예시는 다음과 같습니다:

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

## json-simple 라이브러리를 사용한 JSON 처리 방법

### 1. JSON 문자열을 객체로 역직렬화
JSON 문자열을 Java 객체로 변환합니다. `JSONObject.get` 메서드를 통해 JSON 문자열을 Java 객체로 변환할 수 있습니다.

```java
@DisplayName("JSON 문자열을 객체로 역직렬화")
@Test
public void testDeserializeJsonStringToObject() {

  // Given
  JSONObject storeJson = (JSONObject) jsonObject.get("store");
  JSONArray booksJsonArray = (JSONArray) storeJson.get("book");
  JSONObject bicycleJson = (JSONObject) storeJson.get("bicycle");

  // When
  // JSON 데이터를 StoreWrapper 객체로 변환
  StoreWrapper storeWrapper = new StoreWrapper();
  Store store = new Store();

  // Book 객체 설정
  List<Book> books = new ArrayList<>();
  for (Object bookObject : booksJsonArray) {
    JSONObject bookJson = (JSONObject) bookObject;
    Book book = new Book();

    book.setCategory((String) bookJson.get("category"));
    book.setAuthor((String) bookJson.get("author"));
    book.setTitle((String) bookJson.get("title"));
    book.setPrice(((Number) bookJson.get("price")).doubleValue());

    // isbn이 있을 경우에만 설정
    if (bookJson.containsKey("isbn")) {
      book.setIsbn((String) bookJson.get("isbn"));
    }

    books.add(book);
  }

  // Bicycle 객체 설정
  Bicycle bicycle = new Bicycle();
  bicycle.setColor((String) bicycleJson.get("color"));
  bicycle.setPrice(((Number) bicycleJson.get("price")).doubleValue());

  // Store 객체 설정
  store.setBook(books);
  store.setBicycle(bicycle);

  // StoreWrapper 객체 설정
  storeWrapper.setStore(store);

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
Java 객체를 JSON 문자열로 변환합니다. `JSONObject.toJSONString` 메서드를 통해 Java 객체를 JSON 문자열로 변환할 수 있습니다.

```java
@DisplayName("객체를 JSON 문자열로 직렬화")
@Test
public void testSerializeObjectToJsonString() {

  // Given
  JSONObject storeJson = (JSONObject) jsonObject.get("store");
  JSONArray booksJsonArray = (JSONArray) storeJson.get("book");
  JSONObject bicycleJson = (JSONObject) storeJson.get("bicycle");

  // JSON 데이터를 StoreWrapper 객체로 변환
  StoreWrapper storeWrapper = new StoreWrapper();
  Store store = new Store();

  // Book 객체 설정
  List<Book> books = new ArrayList<>();
  for (Object bookObject : booksJsonArray) {
    JSONObject bookJson = (JSONObject) bookObject;
    Book book = new Book();

    book.setCategory((String) bookJson.get("category"));
    book.setAuthor((String) bookJson.get("author"));
    book.setTitle((String) bookJson.get("title"));
    book.setPrice(((Number) bookJson.get("price")).doubleValue());

    // isbn이 있을 경우에만 설정
    if (bookJson.containsKey("isbn")) {
      book.setIsbn((String) bookJson.get("isbn"));
    }

    books.add(book);
  }

  // Bicycle 객체 설정
  Bicycle bicycle = new Bicycle();
  bicycle.setColor((String) bicycleJson.get("color"));
  bicycle.setPrice(((Number) bicycleJson.get("price")).doubleValue());

  // Store 객체 설정
  store.setBook(books);
  store.setBicycle(bicycle);

  // StoreWrapper 객체 설정
  storeWrapper.setStore(store);

  // When
  String serializedJson = storeWrapper.toJSONString();

  // Then
  log.debug("serializedJson : {}", serializedJson);
  assertAll(
      () -> assertNotNull(serializedJson),
      () -> assertTrue(serializedJson.contains("store"))
  );
}
```

### 3. JSON 데이터 수정
특정 JSON 노드의 값을 수정합니다. `JSONObject.put` 및 `JSONObject.remove` 메서드를 통해 JSON 데이터를 수정할 수 있습니다.

```java
@DisplayName("JSON 데이터 수정")
@Test
public void testModifyJsonData() {

  // Given
  JSONObject modifiedJsonObject = (JSONObject) jsonObject.clone();

  // When
  modifiedJsonObject.put("expensive", 15);

  // Then
  log.debug("modifiedJsonObject : {}", modifiedJsonObject);
  assertAll(
      () -> assertEquals(15, modifiedJsonObject.get("expensive")),
      () -> assertNotNull(modifiedJsonObject),
      () -> assertTrue(modifiedJsonObject.toJSONString().contains("\"expensive\":15"))
  );
}
```

### 4. JSON 데이터 병합
두 개의 JSON 객체를 병합합니다. `JSONObject.putAll` 메서드를 통해 JSON 객체를 병합할 수 있습니다.

```java
@DisplayName("JSON 데이터 병합")
@Test
public void testMergeJsonData() throws ParseException {

  // Given
  String additionalJson = "{\"newProperty\":\"newValue\"}";
  JSONParser parser = new JSONParser();
  JSONObject additionalObject = (JSONObject) parser.parse(additionalJson);

  // When
  jsonObject.putAll(additionalObject);

  // Then
  log.debug("mergedJson : {}", jsonObject);
  assertAll(
      () -> assertNotNull(jsonObject),
      () -> assertTrue(jsonObject.toJSONString().contains("\"newProperty\":\"newValue\""))
  );
}
```

### 5. JSON 속성 추출
특정 JSON 속성을 추출합니다. `JSONObject.get` 메서드를 통해 JSON 속성을 추출할 수 있습니다.

```java
@DisplayName("JSON 속성 추출")
@Test
public void testExtractJsonProperty() {

  // Given & When
  Object expensiveElement = jsonObject.get("expensive");

  // Then
  log.debug("expensive : {}", expensiveElement);
  assertAll(
      () -> assertNotNull(expensiveElement),
      () -> assertEquals((long) 10, expensiveElement) 
  );
}
```

### 6. JSON 속성 추가
새로운 JSON 속성을 추가합니다. `JSONObject.put` 메서드를 통해 JSON 속성을 추가할 수 있습니다.

```java
@DisplayName("JSON 속성 추가")
@Test
public void testAddJsonProperty() {

  // Given & When
  jsonObject.put("newProperty", "newValue");

  // Then
  log.debug("modifiedJson : {}", jsonObject);
  assertAll(
      () -> assertNotNull(jsonObject),
      () -> assertEquals("newValue", jsonObject.get("newProperty"))
  );
}
```

### 7. JSON 속성 삭제
특정 JSON 속성을 삭제합니다. `JSONObject.remove` 메서드를 통해 JSON 속성을 삭제할 수 있습니다.

```java
@DisplayName("JSON 속성 삭제")
@Test
public void testRemoveJsonProperty() {

  // Given & When
  jsonObject.remove("expensive");

  // Then
  log.debug("modifiedJson : {}", jsonObject);
  assertAll(
      () -> assertNotNull(jsonObject),
      () -> assertFalse(jsonObject.containsKey("expensive"))
  );
}
```

### 8. JSON 배열 처리
JSON 배열을 처리합니다. `JSONArray.get` 및 `JSONArray.add` 메서드를 통해 JSON 배열을 처리할 수 있습니다.

```java
@DisplayName("JSON 배열 처리")
@Test
public void testHandleJsonArray() {

  // Given
  JSONObject store = (JSONObject) jsonObject.get("store");
  JSONArray books = (JSONArray) store.get("book");

  // Then
  assertTrue(books instanceof JSONArray);
  for (Object bookElement : books) {
    JSONObject bookObject = (JSONObject) bookElement;
    log.debug("Book: {}", bookObject);
    assertNotNull(bookObject.get("category"));
    assertNotNull(bookObject.get("author"));
    assertNotNull(bookObject.get("title"));
    assertNotNull(bookObject.get("price"));
    if (bookObject.containsKey("isbn")) {
      assertNotNull(bookObject.get("isbn"));
    }
  }
}
```

## 참고 자료
- [json-simple GitHub](https://github.com/fangyidong/json-simple)
