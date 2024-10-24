package util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HttpUtilsTest {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private final String TEST_GET_URL = "https://jsonplaceholder.typicode.com/posts/1";
  private final String TEST_POST_URL = "https://jsonplaceholder.typicode.com/posts";
  private final String TEST_PUT_URL = "https://jsonplaceholder.typicode.com/posts/1";
  private final String TEST_PATCH_URL = "https://jsonplaceholder.typicode.com/posts/1";
  private final String TEST_DELETE_URL = "https://jsonplaceholder.typicode.com/posts/1";

  /**
   * GET 요청 테스트 URL에서 포스트를 가져와서 ID를 확인
   */
  @Order(1)
  @DisplayName(" GET 요청 테스트 URL에서 포스트를 가져와서 ID를 확인")
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

  /**
   * POST 요청 테스트 새로운 포스트를 생성하여 응답의 title과 body를 확인
   */
  @Order(2)
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

  /**
   * PUT 요청 테스트 기존 포스트를 업데이트하여 응답의 title과 body를 확인
   */
  @Order(3)
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

  /**
   * PATCH 요청 테스트 기존 포스트의 title을 업데이트하여 응답의 title을 확인
   */
  @Order(4)
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

  /**
   * DELETE 요청 테스트_기존 포스트를 삭제하고 응답을 확인
   */
  @Order(5)
  @DisplayName("DELETE 요청 테스트_기존 포스트를 삭제하고 응답을 확인")
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

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PostDTO {

    private int id;
    private String title;
    private String body;
    private int userId;
  }
}
