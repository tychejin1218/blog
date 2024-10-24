# HttpURLConnection을 활용한 HTTP 요청

## HttpURLConnection이란?

`HttpURLConnection`은 Java에서 표준 HTTP(S) 통신을 제공하기 위한 클래스입니다. 이 클래스는 HTTP 요청을 보내고, 서버로부터 응답을 받아 처리하는 기능을 제공합니다.

### 주요 메서드
- `setRequestMethod(String method)`: HTTP 요청 메서드를 설정합니다. 예: GET, POST, PUT, DELETE, PATCH
- `setConnectTimeout(int timeout)`: 연결을 시도할 때의 타임아웃 시간을 설정합니다. 단위는 밀리초입니다.
- `setReadTimeout(int timeout)`: 데이터 읽기를 시도할 때의 타임아웃 시간을 설정합니다. 단위는 밀리초입니다.
- `setRequestProperty(String key, String value)`: 요청 헤더를 설정합니다.
- `getResponseCode()`: 서버로부터 받은 응답 코드(상태 코드)를 반환합니다.
- `getInputStream()`: 서버로부터 받은 응답 데이터를 읽기 위한 입력 스트림을 반환합니다.
- `getOutputStream()`: 서버로 데이터를 전송하기 위한 출력 스트림을 반환합니다.
- `setDoOutput(boolean doOutput)`: 출력 스트림을 사용할지 여부를 설정합니다.
- `disconnect()`: 연결을 종료합니다.


## HttpUtils 클래스
HttpUtils 클래스는 다양한 HTTP 메서드(GET, POST, PUT, PATCH, DELETE)를 사용하여 HTTP 요청을 보내고 DTO 객체로 응답을 받는 기능을 제공합니다.

```java
package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * HTTP 요청(GET, POST, PUT, PATCH, DELETE)을 위한 유틸리티 클래스
 */
@Slf4j
public class HttpUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final int CONNECT_TIMEOUT = 5000; // 5초 연결 타임아웃
  private static final int READ_TIMEOUT = 5000;    // 5초 읽기 타임아웃

  // 메서드들을 위에서 설명한 형태로 추가 ...
}
```

## HttpUtils 클래스의 메서드 설명

### 1. GET 요청
```java
public static <T> T sendGet(String targetUrl, Map<String, String> headers, Class<T> responseType)
    throws Exception {

  HttpURLConnection connection = null;

  try {
    URL url = new URL(targetUrl);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setConnectTimeout(CONNECT_TIMEOUT); // 연결 타임아웃 설정
    connection.setReadTimeout(READ_TIMEOUT);       // 읽기 타임아웃 설정

    // 헤더 설정
    if (headers != null) {
      for (Map.Entry<String, String> header : headers.entrySet()) {
        connection.setRequestProperty(header.getKey(), header.getValue());
      }
    }

    int responseCode = connection.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_OK) {
      throw new Exception("HTTP GET request failed with response code " + responseCode);
    }

    // 응답 읽기
    try (BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()))) {
      StringBuilder response = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      log.debug("GET Response: " + response.toString());
      return objectMapper.readValue(response.toString(), responseType);
    }
  } finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
}
```

### 단위 테스트
```java
@DisplayName("GET 요청 테스트 URL에서 포스트를 가져와서 ID를 확인")
@Test
public void testGetRequest() throws Exception {

  // Given
  Map<String, String> headers = new HashMap<>();
  headers.put("Accept", "application/json");

  // When
  PostDTO response = HttpUtils.sendGet(TEST_GET_URL, headers, PostDTO.class);
  log.debug("response: {}", objectMapper.writeValueAsString(response));

  // Then
  assertAll(
      () -> assertNotNull(response),
      () -> assertEquals(1, response.getId())
  );
}
```

### 2. POST 요청
```java
public static <T, R> R sendPost(String targetUrl, T postData, Map<String, String> headers,
    Class<R> responseType) throws Exception {

  HttpURLConnection connection = null;

  try {
    URL url = new URL(targetUrl);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    connection.setConnectTimeout(CONNECT_TIMEOUT); // 연결 타임아웃 설정
    connection.setReadTimeout(READ_TIMEOUT);       // 읽기 타임아웃 설정

    // 헤더 설정
    if (headers != null) {
      for (Map.Entry<String, String> header : headers.entrySet()) {
        connection.setRequestProperty(header.getKey(), header.getValue());
      }
    }

    // 요청 데이터 전송 (DTO 객체를 JSON으로 변환하여 전송)
    try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
      out.writeBytes(objectMapper.writeValueAsString(postData));
      out.flush();
    }

    int responseCode = connection.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_OK
        && responseCode != HttpURLConnection.HTTP_CREATED) {
      throw new Exception("HTTP POST request failed with response code " + responseCode);
    }

    // 응답 읽기
    try (BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()))) {
      StringBuilder response = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      log.debug("POST Response: " + response.toString());
      return objectMapper.readValue(response.toString(), responseType);
    }
  } finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
}
```

#### 단위 테스트
```java
@DisplayName("POST 요청 테스트 새로운 포스트를 생성하여 응답의 title과 body를 확인")
@Test
public void testPostRequest() throws Exception {

  // Given
  Map<String, String> headers = new HashMap<>();
  headers.put("Content-Type", "application/json; charset=UTF-8");

  PostDTO postData = PostDTO.builder()
      .title("foo")
      .body("bar")
      .userId(1)
      .build();

  // When
  PostDTO response = HttpUtils.sendPost(TEST_POST_URL, postData, headers, PostDTO.class);
  log.debug("response: {}", objectMapper.writeValueAsString(response));

  // Then
  assertAll(
      () -> assertNotNull(response),
      () -> assertEquals("foo", response.getTitle()),
      () -> assertEquals("bar", response.getBody())
  );
}
```

### 3. PUT 요청
```java
public static <T, R> R sendPut(String targetUrl, T putData, Map<String, String> headers,
    Class<R> responseType) throws Exception {

  HttpURLConnection connection = null;

  try {
    URL url = new URL(targetUrl);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("PUT");
    connection.setDoOutput(true);
    connection.setConnectTimeout(CONNECT_TIMEOUT); // 연결 타임아웃 설정
    connection.setReadTimeout(READ_TIMEOUT);       // 읽기 타임아웃 설정

    // 헤더 설정
    if (headers != null) {
      for (Map.Entry<String, String> header : headers.entrySet()) {
        connection.setRequestProperty(header.getKey(), header.getValue());
      }
    }

    // 요청 데이터 전송 (DTO 객체를 JSON으로 변환하여 전송)
    try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
      out.writeBytes(objectMapper.writeValueAsString(putData));
      out.flush();
    }

    int responseCode = connection.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_OK) {
      throw new Exception("HTTP PUT request failed with response code " + responseCode);
    }

    // 응답 읽기
    try (BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()))) {
      StringBuilder response = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      log.debug("PUT Response: " + response.toString());
      return objectMapper.readValue(response.toString(), responseType);
    }
  } finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
}
```

### 단위 테스트
```java
@DisplayName("PUT 요청 테스트 기존 포스트를 업데이트하여 응답의 title과 body를 확인")
@Test
public void testPutRequest() throws Exception {

  // Given
  Map<String, String> headers = new HashMap<>();
  headers.put("Content-Type", "application/json; charset=UTF-8");

  PostDTO putData = PostDTO.builder()
      .id(1)
      .title("foo")
      .body("bar")
      .userId(1)
      .build();

  // When
  PostDTO response = HttpUtils.sendPut(TEST_PUT_URL, putData, headers, PostDTO.class);
  log.debug("response: {}", objectMapper.writeValueAsString(response));

  // Then
  assertAll(
      () -> assertNotNull(response),
      () -> assertEquals("foo", response.getTitle()),
      () -> assertEquals("bar", response.getBody())
  );
}
```

### 4. PATCH 요청
```java
public static <T, R> R sendPatch(String targetUrl, T patchData, Map<String, String> headers,
    Class<R> responseType) throws Exception {

  HttpURLConnection connection = null;

  try {
    URL url = new URL(targetUrl);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
    connection.setDoOutput(true);
    connection.setConnectTimeout(CONNECT_TIMEOUT); // 연결 타임아웃 설정
    connection.setReadTimeout(READ_TIMEOUT);       // 읽기 타임아웃 설정

    // 헤더 설정
    if (headers != null) {
      for (Map.Entry<String, String> header : headers.entrySet()) {
        connection.setRequestProperty(header.getKey(), header.getValue());
      }
    }

    // 요청 데이터 전송 (DTO 객체를 JSON으로 변환하여 전송)
    try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
      out.writeBytes(objectMapper.writeValueAsString(patchData));
      out.flush();
    }

    int responseCode = connection.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_OK) {
      throw new Exception("HTTP PATCH request failed with response code " + responseCode);
    }

    // 응답 읽기
    try (BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()))) {
      StringBuilder response = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      log.debug("PATCH Response: " + response.toString());
      return objectMapper.readValue(response.toString(), responseType);
    }
  } finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
}
```

### 단위 테스트
```java
@DisplayName("PATCH 요청 테스트 기존 포스트의 title을 업데이트하여 응답의 title을 확인")
@Test
public void testPatchRequest() throws Exception {

  // Given
  Map<String, String> headers = new HashMap<>();
  headers.put("Content-Type", "application/json; charset=UTF-8");

  PostDTO patchData = PostDTO.builder()
      .title("foo_updated")
      .build();

  // When
  PostDTO response = HttpUtils.sendPatch(TEST_PATCH_URL, patchData, headers, PostDTO.class);
  log.debug("response: {}", objectMapper.writeValueAsString(response));

  // Then
  assertAll(
      () -> assertNotNull(response),
      () -> assertEquals("foo_updated", response.getTitle())
  );
}
```

### 5. DELETE 요청
```java
public static <T> T sendDelete(String targetUrl, Map<String, String> headers,
    Class<T> responseType) throws Exception {

  HttpURLConnection connection = null;

  try {
    URL url = new URL(targetUrl);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("DELETE");
    connection.setConnectTimeout(CONNECT_TIMEOUT); // 연결 타임아웃 설정
    connection.setReadTimeout(READ_TIMEOUT);       // 읽기 타임아웃 설정

    // 헤더 설정
    if (headers != null) {
      for (Map.Entry<String, String> header : headers.entrySet()) {
        connection.setRequestProperty(header.getKey(), header.getValue());
      }
    }

    int responseCode = connection.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_OK
        && responseCode != HttpURLConnection.HTTP_NO_CONTENT) {
      throw new Exception("HTTP DELETE request failed with response code " + responseCode);
    }

    // 응답 읽기
    try (BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()))) {
      StringBuilder response = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      log.debug("DELETE Response: " + response.toString());
      return objectMapper.readValue(response.toString(), responseType);
    }
  } finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
}
```

### 단위 테스트
```java
@DisplayName("DELETE 요청 테스트 기존 포스트를 삭제하고 응답을 확인")
@Test
public void testDeleteRequest() throws Exception {

  // Given
  Map<String, String> headers = new HashMap<>();
  headers.put("Accept", "application/json");

  // When
  PostDTO response = HttpUtils.sendDelete(TEST_DELETE_URL, headers, PostDTO.class);
  log.debug("response: {}", objectMapper.writeValueAsString(response));

  // Then
  assertAll(
      () -> assertNotNull(response),
      () -> assertEquals(0, response.getId())
  );
}
```

## 참고 자료
- [Java 공식 문서 - HttpURLConnection](https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html)
