package jackson;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JacksonTest {

  private static final File jsonFile = new File("src/main/resources/store.json");
  private static String json;
  private static ObjectMapper objectMapper;

  private static String readFile() throws IOException {
    return Files.readString(JacksonTest.jsonFile.toPath(), StandardCharsets.UTF_8);
  }

  @BeforeAll
  static void setUp() throws IOException {
    json = readFile();
    objectMapper = new ObjectMapper();
  }

  @Order(1)
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

  @Order(2)
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

  @Order(3)
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

  @Order(4)
  @DisplayName("JSON 데이터 병합")
  @Test
  public void testMergeJsonData() throws IOException {

    // Given
    String additionalJson = "{\"newProperty\":\"newValue\"}";
    JsonNode originalNode = objectMapper.readTree(json);
    JsonNode additionalNode = objectMapper.readTree(additionalJson);
    ObjectNode mergedNode = ((ObjectNode) originalNode).setAll((ObjectNode) additionalNode);

    // When
    String mergedJson = objectMapper.writeValueAsString(mergedNode);

    // Then
    log.debug("mergedJson : {}", mergedJson);
    assertAll(
        () -> assertNotNull(mergedJson),
        () -> assertTrue(mergedJson.contains("\"newProperty\":\"newValue\""))
    );
  }

  @Order(5)
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

  @Order(6)
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

  @Order(7)
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

  @Order(8)
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

  @Getter
  @Setter
  @ToString
  public static class StoreWrapper {

    private Store store;
    private int expensive;
  }

  @Getter
  @Setter
  @ToString
  public static class Store {

    private List<Book> book;
    private Bicycle bicycle;
  }

  @Getter
  @Setter
  @ToString
  public static class Book {

    private String category;
    private String author;
    private String title;
    private Double price;
    private String isbn;
  }

  @Getter
  @Setter
  @ToString
  public static class Bicycle {

    private String color;
    private Double price;
  }
}
