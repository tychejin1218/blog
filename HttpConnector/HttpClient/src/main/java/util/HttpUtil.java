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
