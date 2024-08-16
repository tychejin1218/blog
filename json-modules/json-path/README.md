# Java에서 JSONPath로 JSON 데이터 다루기

**JSONPath**는 JSON 데이터를 효율적으로 탐색하고, 특정 값을 추출하거나 필터링하는 쿼리 언어입니다. 이번 포스트에서는 `com.jayway.jsonpath` 라이브러리를 사용하여 Java에서 JSON 데이터를 다루는 방법을 살펴보겠습니다.

---

## JSONPath 개요

JSONPath는 JSON 문서를 대상으로 SQL과 유사한 구문을 사용하여 JSON 데이터의 특정 값을 추출할 수 있습니다. 대표적인 JSONPath 구문은 다음과 같습니다.

| JSON 경로 | 설명 |
| --------- | ---- |
| `$`       | 루트 객체를 나타냅니다. |
| `@`       | 현재 객체를 나타냅니다. |
| `.` 또는 `[]` | 자식을 나타냅니다. |
| `*`       | 모든 요소를 나타냅니다. |
| `..`      | 모든 하위 요소를 재귀적으로 탐색합니다. |
| `?()`     | 필터링 조건을 나타냅니다. |

---

## 1. 라이브러리 추가

`com.jayway.jsonpath:json-path:2.9.0`는 Java에서 JSON 데이터를 다루기 위해 사용하는 라이브러리입니다.

**Gradle 설정:**

```groovy
// build.gradle 파일
implementation 'com.jayway.jsonpath:json-path:2.9.0'
```

---

## 2. 샘플 JSON

다음은 예제에서 사용할 `store.json` 파일입니다.

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

---

## 3. JSONPath를 사용한 테스트 코드

### 3_1. JSONPath를 사용하여 JSON 파일에서 작가명 추출

`$.store.book[*].author` 표현식은 `book` 배열 내의 모든 `author` 필드를 찾아서 반환합니다.

```java
List<String> authors = JsonPath.read(json, "$.store.book[*].author");
```

### 3_2. JSON 문자열을 Document 객체로 파싱한 후, JSONPath를 사용하여 작가명 추출

```java
Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
List<String> authors = JsonPath.read(document, "$.store.book[*].author");
```

### 3_3. ReadContext 객체를 이용하여 JSONPath를 통한 작가명 추출

```java
ReadContext readContext = JsonPath.parse(json);
List<String> authors = readContext.read("$.store.book[*].author");
```

### 3_4. 특정 조건을 만족하는 책 정보 추출

`$.store.book[?(@.category == 'fiction' && @.price > 10)]` 표현식은 `book` 배열 내에서 `category`가 'fiction'이고 `price`가 10달러를 초과하는 책 객체들을 반환합니다.

```java
List<Map<String, Object>> books = JsonPath
    .using(Configuration.defaultConfiguration())
    .parse(json)
    .read("$.store.book[?(@.category == 'fiction' && @.price > 10)]", List.class);
```

### 3_5. JSONPath 필터를 사용하여 특정 조건을 만족하는 책 정보 추출

필터를 사용하여 `category`가 'fiction'이고 `price`가 10달러를 초과하는 책 객체들을 반환합니다.

```java
Filter filter = filter(where("category").is("fiction").and("price").gt(10D));
List<Map<String, Object>> books = JsonPath
    .using(Configuration.defaultConfiguration())
    .parse(json)
    .read("$.store.book[?]", filter);
```

### 3_6. JSONPath를 이용하여 JSON 문자열을 객체로 변환

JSONPath를 사용하여 첫 번째 책의 정보를 Book 객체로 변환하고 이를 검증합니다.

```java
String jsonPath = "$.store.book[0]";
Book book = JsonPath.parse(json).read(jsonPath, Book.class);
```

### 3_7. JSONPath 옵션을 사용하여 JSON 데이터에서 경로 리스트를 추출

`Option.AS_PATH_LIST` 옵션을 사용하여 JSON 데이터에서 모든 경로 리스트를 추출하고 이를 검증합니다.

```java
Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
String jsonPath = "$..author";
List<String> pathList = JsonPath.using(conf).parse(json).read(jsonPath);
```

---

## 참고
- [JsonPath - Github](https://github.com/json-path/JsonPath)
- [소스 코드 - Github Repository](https://github.com/tychejin1218/blog/tree/main/json-modules/json-path)
