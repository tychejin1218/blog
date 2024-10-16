package gson;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
public class GsonTest {

  private static final File jsonFile = new File("src/main/resources/store.json");
  private static String json;
  private static Gson gson;

  private static String readFile() throws IOException {
    return Files.readString(GsonTest.jsonFile.toPath(), StandardCharsets.UTF_8);
  }

  @BeforeAll
  static void setUp() throws IOException {
    json = readFile();
    gson = new Gson();
  }

  @Order(1)
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

  @Order(2)
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

  @Order(3)
  @DisplayName("JSON 데이터 수정")
  @Test
  public void testModifyJsonData() {

    // Given
    JsonElement rootElement = JsonParser.parseString(json);
    JsonObject modifiedRoot = rootElement.getAsJsonObject();

    // When
    // 특정 노드 값 수정
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

  @Order(4)
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

  @Order(5)
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

  @Order(6)
  @DisplayName("JSON 속성 추가")
  @Test
  public void testAddJsonProperty() {

    // Given
    JsonElement rootElement = JsonParser.parseString(json);
    JsonObject modifiedRoot = rootElement.getAsJsonObject();

    // When
    // 노드 추가
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

  @Order(7)
  @DisplayName("JSON 속성 삭제")
  @Test
  public void testRemoveJsonProperty() {

    // Given
    JsonElement rootElement = JsonParser.parseString(json);
    JsonObject modifiedRoot = rootElement.getAsJsonObject();

    // When
    // 노드 삭제
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

  @Order(8)
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
