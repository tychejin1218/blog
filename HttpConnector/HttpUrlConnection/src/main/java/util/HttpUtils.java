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

    HttpURLConnection connection = null;

    try {
      URL url = new URL(targetUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setConnectTimeout(CONNECT_TIMEOUT);
      connection.setReadTimeout(READ_TIMEOUT);

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

    HttpURLConnection connection = null;

    try {
      URL url = new URL(targetUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setConnectTimeout(CONNECT_TIMEOUT);
      connection.setReadTimeout(READ_TIMEOUT);

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

    HttpURLConnection connection = null;

    try {
      URL url = new URL(targetUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("PUT");
      connection.setDoOutput(true);
      connection.setConnectTimeout(CONNECT_TIMEOUT);
      connection.setReadTimeout(READ_TIMEOUT);

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

    HttpURLConnection connection = null;

    try {
      URL url = new URL(targetUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
      connection.setDoOutput(true);
      connection.setConnectTimeout(CONNECT_TIMEOUT);
      connection.setReadTimeout(READ_TIMEOUT);

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

    HttpURLConnection connection = null;

    try {
      URL url = new URL(targetUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("DELETE");
      connection.setConnectTimeout(CONNECT_TIMEOUT);
      connection.setReadTimeout(READ_TIMEOUT);

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
}
