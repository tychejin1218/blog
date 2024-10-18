package json;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonSimpleTest {

  private static final String jsonFilePath = "src/main/resources/store.json";
  private static JSONObject jsonObject;

  @BeforeAll
  static void setUp() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    FileReader reader = new FileReader(jsonFilePath, StandardCharsets.UTF_8);
    jsonObject = (JSONObject) parser.parse(reader);
  }

  @Order(1)
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

  @Order(2)
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

  @Order(3)
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

  @Order(4)
  @DisplayName("JSON 데이터 병합")
  @Test
  public void testMergeJsonData() throws ParseException {

    // Given
    String additionalJson = "{\"newProperty\":\"newValue\"}";
    JSONParser parser = new JSONParser();
    JSONObject additionalObject = (JSONObject) parser.parse(additionalJson);;

    // When
    jsonObject.putAll(additionalObject);

    // Then
    log.debug("mergedJson : {}", jsonObject);
    assertAll(
        () -> assertNotNull(jsonObject),
        () -> assertTrue(jsonObject.toJSONString().contains("\"newProperty\":\"newValue\""))
    );
  }

  @Order(5)
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

  @Order(6)
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

  @Order(7)
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

  @Order(8)
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

  @Getter
  @Setter
  @ToString
  public static class StoreWrapper {

    private Store store;
    private int expensive;

    public String toJSONString() {
      JSONObject jsonStoreWrapper = new JSONObject();
      JSONObject jsonStore = new JSONObject();
      JSONArray jsonBooks = new JSONArray();

      for (Book book : store.getBook()) {
        JSONObject jsonBook = new JSONObject();

        jsonBook.put("category", book.getCategory());
        jsonBook.put("author", book.getAuthor());
        jsonBook.put("title", book.getTitle());
        jsonBook.put("price", book.getPrice());
        if (book.getIsbn() != null) {
          jsonBook.put("isbn", book.getIsbn());
        }

        jsonBooks.add(jsonBook);
      }

      JSONObject jsonBicycle = new JSONObject();
      jsonBicycle.put("color", store.getBicycle().getColor());
      jsonBicycle.put("price", store.getBicycle().getPrice());

      jsonStore.put("book", jsonBooks);
      jsonStore.put("bicycle", jsonBicycle);
      jsonStoreWrapper.put("store", jsonStore);

      return jsonStoreWrapper.toJSONString();
    }
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
