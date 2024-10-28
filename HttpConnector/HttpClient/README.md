# HttpClient를 활용한 HTTP 요청

## HttpClient란?

`HttpClient`는 Java 11에서 도입된 HTTP 클라이언트 API로, 비동기 및 동기 방식의 HTTP(S) 요청을 손쉽게 수행할 수 있게 해주는 클래스입니다. `HttpClient`를 사용하면 성능이 향상되고, HTTP/2 및 Web Socket을 지원하는 등 여러 가지 이점을 제공합니다.

### 주요 클래스와 메서드

- `HttpClient`: HTTP 클라이언트를 생성하고 설정합니다.
    - `newBuilder()`: 새로운 빌더 인스턴스를 생성합니다.
    - `build()`: 설정된 빌더를 사용하여 `HttpClient` 인스턴스를 생성합니다.
    - `send()`: 동기 방식으로 HTTP 요청을 전송합니다.
    - `sendAsync()`: 비동기 방식으로 HTTP 요청을 전송합니다.

- `HttpRequest`: HTTP 요청을 생성하고 설정합니다.
    - `newBuilder()`: 새로운 빌더 인스턴스를 생성합니다.
    - `uri(URI uri)`: 요청 URI를 설정합니다.
    - `GET()`, `POST(BodyPublisher body)`, `PUT(BodyPublisher body)`, `DELETE()`: HTTP 메서드를 설정합니다.
    - `header(String name, String value)`: 요청 헤더를 설정합니다.

- `HttpResponse<T>`: HTTP 응답을 나타냅니다.
    - `body()`: 응답 본문을 반환합니다.
    - `statusCode()`: 응답 상태 코드를 반환합니다.
    - `headers()`: 응답 헤더를 반환합니다.

## HttpUtil 클래스 설명
HttpUtil 클래스는 다양한 HTTP 메서드(GET, POST, PUT, PATCH, DELETE)를 사용하여 HTTP 요청을 보내고, 서버로부터 응답을 받아 이를 DTO 객체로 반환하는 유틸리티 클래스입니다.

```java
package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * HTTP 요청(GET, POST, PUT, PATCH, DELETE)을 위한 유틸리티 클래스
 */
@Slf4j
public class HttpUtil {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final HttpClient httpClient = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(5))
      .build();

  /**
   * HTTP 요청을 위한 HttpRequest.Builder를 생성
   *
   * @param targetUrl 요청을 보낼 URL
   * @param headers 요청 헤더 정보(키-값 쌍)
   * @return HttpRequest.Builder 객체
   * @throws Exception 요청을 위한 URI 생성 중 발생할 수 있는 예외
   */
  private static HttpRequest.Builder createRequestBuilder(String targetUrl,
      Map<String, String> headers) throws Exception {
    HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
        .uri(new URI(targetUrl))
        .timeout(Duration.ofSeconds(5));

    // 헤더 설정
    if (headers != null) {
      headers.forEach(requestBuilder::setHeader);
    }

    return requestBuilder;
  }

  /**
   * GET 요청을 보내고 응답을 DTO 객체로 반환
   *
   * @param targetUrl    요청을 보낼 URL
   * @param headers      요청 헤더 정보(키-값 쌍)
   * @param responseType 응답 DTO 타입의 클래스 객체
   * @param <T>          DTO 타입
   * @return 서버로부터 받은 응답을 나타내는 DTO 객체
   * @throws Exception 요청을 보내거나 응답을 받는 도중 발생할 수 있는 모든 예외
   */
  public static <T> T sendGet(String targetUrl, Map<String, String> headers, Class<T> responseType)
      throws Exception {

    // GET 요청 생성
    HttpRequest.Builder requestBuilder = createRequestBuilder(targetUrl, headers)
        .GET();

    // 요청 보내기 및 응답 받기
    HttpRequest request = requestBuilder.build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    // 응답 코드 검사
    if (response.statusCode() != 200) {
      throw new IOException("HTTP GET request failed with response code " + response.statusCode());
    }

    log.debug("GET Response: " + response.body());

    // 응답 변환
    return objectMapper.readValue(response.body(), responseType);
  }

  /**
   * POST 요청을 보내고 응답을 DTO 객체로 반환
   *
   * @param targetUrl    요청을 보낼 URL
   * @param postData     POST 요청에 포함될 데이터 (DTO 객체)
   * @param headers      요청 헤더 정보(키-값 쌍)
   * @param responseType 응답 DTO 타입의 클래스 객체
   * @param <T>          요청 데이터의 DTO 타입
   * @param <R>          응답 데이터의 DTO 타입
   * @return 서버로부터 받은 응답을 나타내는 DTO 객체
   * @throws Exception 요청을 보내거나 응답을 받는 도중 발생할 수 있는 모든 예외
   */
  public static <T, R> R sendPost(String targetUrl, T postData, Map<String, String> headers,
      Class<R> responseType) throws Exception {

    // POST 요청 생성
    HttpRequest.Builder requestBuilder = createRequestBuilder(targetUrl, headers)
        .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(postData)));

    // 요청 보내기 및 응답 받기
    HttpRequest request = requestBuilder.build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    // 응답 코드 검사
    if (response.statusCode() != 200 && response.statusCode() != 201) {
      throw new IOException("HTTP POST request failed with response code " + response.statusCode());
    }

    log.debug("POST Response: " + response.body());

    // 응답 변환
    return objectMapper.readValue(response.body(), responseType);
  }

  /**
   * PUT 요청을 보내고 응답을 DTO 객체로 반환
   *
   * @param targetUrl    요청을 보낼 URL
   * @param putData      PUT 요청에 포함될 데이터 (DTO 객체)
   * @param headers      요청 헤더 정보(키-값 쌍)
   * @param responseType 응답 DTO 타입의 클래스 객체
   * @param <T>          요청 데이터의 DTO 타입
   * @param <R>          응답 데이터의 DTO 타입
   * @return 서버로부터 받은 응답을 나타내는 DTO 객체
   * @throws Exception 요청을 보내거나 응답을 받는 도중 발생할 수 있는 모든 예외
   */
  public static <T, R> R sendPut(String targetUrl, T putData, Map<String, String> headers,
      Class<R> responseType) throws Exception {

    // PUT 요청 생성
    HttpRequest.Builder requestBuilder = createRequestBuilder(targetUrl, headers)
        .PUT(BodyPublishers.ofString(objectMapper.writeValueAsString(putData)));

    // 요청 보내기 및 응답 받기
    HttpRequest request = requestBuilder.build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    // 응답 코드 검사
    if (response.statusCode() != 200) {
      throw new IOException("HTTP PUT request failed with response code " + response.statusCode());
    }

    log.debug("PUT Response: " + response.body());

    // 응답 변환
    return objectMapper.readValue(response.body(), responseType);
  }

  /**
   * PATCH 요청을 보내고 응답을 DTO 객체로 반환
   *
   * @param targetUrl    요청을 보낼 URL
   * @param patchData    PATCH 요청에 포함될 데이터 (DTO 객체)
   * @param headers      요청 헤더 정보(키-값 쌍)
   * @param responseType 응답 DTO 타입의 클래스 객체
   * @param <T>          요청 데이터의 DTO 타입
   * @param <R>          응답 데이터의 DTO 타입
   * @return 서버로부터 받은 응답을 나타내는 DTO 객체
   * @throws Exception 요청을 보내거나 응답을 받는 도중 발생할 수 있는 모든 예외
   */
  public static <T, R> R sendPatch(String targetUrl, T patchData, Map<String, String> headers,
      Class<R> responseType) throws Exception {

    // PATCH 요청 생성
    HttpRequest.Builder requestBuilder = createRequestBuilder(targetUrl, headers)
        .method("PATCH", BodyPublishers.ofString(objectMapper.writeValueAsString(patchData)));

    // 요청 보내기 및 응답 받기
    HttpRequest request = requestBuilder.build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    // 응답 코드 검사
    if (response.statusCode() != 200) {
      throw new IOException(
          "HTTP PATCH request failed with response code " + response.statusCode());
    }

    log.debug("PATCH Response: " + response.body());

    // 응답 변환
    return objectMapper.readValue(response.body(), responseType);
  }

  /**
   * DELETE 요청을 보내고 응답을 DTO 객체로 반환
   *
   * @param targetUrl    요청을 보낼 URL
   * @param headers      요청 헤더 정보(키-값 쌍)
   * @param responseType 응답 DTO 타입의 클래스 객체
   * @param <T>          응답 데이터의 DTO 타입
   * @return 서버로부터 받은 응답을 나타내는 DTO 객체
   * @throws Exception 요청을 보내거나 응답을 받는 도중 발생할 수 있는 모든 예외
   */
  public static <T> T sendDelete(String targetUrl, Map<String, String> headers,
      Class<T> responseType) throws Exception {

    // DELETE 요청 생성
    HttpRequest.Builder requestBuilder = createRequestBuilder(targetUrl, headers)
        .DELETE();

    // 요청 보내기 및 응답 받기
    HttpRequest request = requestBuilder.build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    // 응답 코드 검사
    if (response.statusCode() != 200 && response.statusCode() != 204) {
      throw new IOException(
          "HTTP DELETE request failed with response code " + response.statusCode());
    }

    log.debug("DELETE Response: " + response.body());

    // 응답 변환
    return objectMapper.readValue(response.body(), responseType);
  }
}
```

## HttpUtil 클래스의 메서드 설명

### 1. GET 요청
GET 요청은 주로 서버로부터 데이터를 조회할 때 사용됩니다.

```java
public static <T> T sendGet(String targetUrl, Map<String, String> headers, Class<T> responseType)
    throws Exception {

  HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(targetUrl))
      .GET()
      .timeout(Duration.ofSeconds(5)); // 요청 타임아웃 설정

  // 헤더 설정
  if (headers != null) {
    headers.forEach(requestBuilder::header);
  }

  HttpRequest request = requestBuilder.build();
  HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

  if (response.statusCode() != 200) {
    throw new Exception("HTTP GET request failed with response code " + response.statusCode());
  }

  log.debug("GET Response: " + response.body());
  return objectMapper.readValue(response.body(), responseType);
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
  PostDTO response = HttpUtil.sendGet(TEST_GET_URL, headers, PostDTO.class);
  log.debug("response: {}", objectMapper.writeValueAsString(response));

  // Then
  assertAll(
      () -> assertNotNull(response),
      () -> assertEquals(1, response.getId())
  );
}
```

### 2. POST 요청
POST 요청은 주로 서버에 데이터를 생성할 때 사용됩니다.

```java
public static <T, R> R sendPost(String targetUrl, T postData, Map<String, String> headers,
    Class<R> responseType) throws Exception {

  String requestBody = objectMapper.writeValueAsString(postData);

  HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(targetUrl))
      .POST(HttpRequest.BodyPublishers.ofString(requestBody))
      .timeout(Duration.ofSeconds(5)); // 요청 타임아웃 설정

  // 헤더 설정
  if (headers != null) {
    headers.forEach(requestBuilder::header);
  }

  HttpRequest request = requestBuilder.build();
  HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

  if (response.statusCode() != 200 && response.statusCode() != 201) {
    throw new Exception("HTTP POST request failed with response code " + response.statusCode());
  }

  log.debug("POST Response: " + response.body());
  return objectMapper.readValue(response.body(), responseType);
}
```

### 단위 테스트
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
  PostDTO response = HttpUtil.sendPost(TEST_POST_URL, postData, headers, PostDTO.class);
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
PUT 요청은 서버의 기존 데이터를 업데이트할 때 사용됩니다.

```java
public static <T, R> R sendPut(String targetUrl, T putData, Map<String, String> headers,
    Class<R> responseType) throws Exception {

  String requestBody = objectMapper.writeValueAsString(putData);

  HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(targetUrl))
      .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
      .timeout(Duration.ofSeconds(5)); // 요청 타임아웃 설정

  // 헤더 설정
  if (headers != null) {
    headers.forEach(requestBuilder::header);
  }

  HttpRequest request = requestBuilder.build();
  HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

  if (response.statusCode() != 200) {
    throw new Exception("HTTP PUT request failed with response code " + response.statusCode());
  }

  log.debug("PUT Response: " + response.body());
  return objectMapper.readValue(response.body(), responseType);
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
  PostDTO response = HttpUtil.sendPut(TEST_PUT_URL, putData, headers, PostDTO.class);
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
PATCH 요청은 서버의 데이터를 부분적으로 업데이트할 때 사용됩니다.

```java
public static <T, R> R sendPatch(String targetUrl, T patchData, Map<String, String> headers,
    Class<R> responseType) throws Exception {

  String requestBody = objectMapper.writeValueAsString(patchData);

  HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(targetUrl))
      .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
      .timeout(Duration.ofSeconds(5)); // 요청 타임아웃 설정

  // 헤더 설정
  if (headers != null) {
    headers.forEach(requestBuilder::header);
  }

  HttpRequest request = requestBuilder.build();
  HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

  if (response.statusCode() != 200) {
    throw new Exception("HTTP PATCH request failed with response code " + response.statusCode());
  }

  log.debug("PATCH Response: " + response.body());
  return objectMapper.readValue(response.body(), responseType);
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
  PostDTO response = HttpUtil.sendPatch(TEST_PATCH_URL, patchData, headers, PostDTO.class);
  log.debug("response: {}", objectMapper.writeValueAsString(response));

  // Then
  assertAll(
      () -> assertNotNull(response),
      () -> assertEquals("foo_updated", response.getTitle())
  );
}
```

### 5. DELETE 요청
DELETE 요청은 서버의 데이터를 삭제할 때 사용됩니다.

```java
public static <T> T sendDelete(String targetUrl, Map<String, String> headers,
    Class<T> responseType) throws Exception {

  HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(targetUrl))
      .DELETE()
      .timeout(Duration.ofSeconds(5)); // 요청 타임아웃 설정

  // 헤더 설정
  if (headers != null) {
    headers.forEach(requestBuilder::header);
  }

  HttpRequest request = requestBuilder.build();
  HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

  if (response.statusCode() != 200 && response.statusCode() != 204) {
    throw new Exception("HTTP DELETE request failed with response code " + response.statusCode());
  }

  log.debug("DELETE Response: " + response.body());
  return objectMapper.readValue(response.body(), responseType);
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
  PostDTO response = HttpUtil.sendDelete(TEST_DELETE_URL, headers, PostDTO.class);
  log.debug("response: {}", objectMapper.writeValueAsString(response));

  // Then
  assertAll(
      () -> assertNotNull(response),
      () -> assertEquals(0, response.getId())
  );
}
```
### 주요 장점
1. **비동기 지원**:
    - `HttpClient`는 비동기 방식으로 HTTP 요청을 보내고 받을 수 있습니다. 이는 더 나은 성능과 비동기 방식의 처리를 용이하게 합니다.

2. **HTTP/2 및 WebSocket 지원**:
    - `HttpClient`는 HTTP/2 및 WebSocket을 완벽히 지원합니다. 이를 통해 다양한 최신 네트워크 기능을 사용할 수 있습니다.

3. **빌더 패턴**:
    - `HttpClient`는 빌더 패턴을 사용하여 요청 구성을 쉽게 할 수 있습니다. 이를 통해 코드의 가독성과 유지보수성을 높일 수 있습니다.

4. **타임아웃 설정**:
    - 연결 타임아웃 및 읽기 타임아웃을 보다 쉽고 직관적으로 설정할 수 있습니다.

5. **편리한 JSON 처리**:
    - `HttpClient`는 `Jackson ObjectMapper` 등과 함께 사용되며, JSON 직렬화/역직렬화를 쉽게 처리할 수 있습니다.

## 참고 자료
- [Java 공식 문서 - HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html)
