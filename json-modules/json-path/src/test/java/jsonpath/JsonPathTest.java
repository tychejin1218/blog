package jsonpath;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Slf4j
public class JsonPathTest {

  private static String json;
  private static File jsonFile = new File("src/main/resources/store.json");

  private static String readFile(File file, Charset charset) throws IOException {
    return new String(Files.readAllBytes(file.toPath()), charset);
  }

  @BeforeAll
  static void setUp() throws IOException {
    json = readFile(jsonFile, StandardCharsets.UTF_8);
  }

  @Order(1)
  @DisplayName("getAuthorsFromJsonFile_Json 파일에서 작가명을 조회")
  @Test
  public void testGetAuthorsFromJsonFile() {

    //  Given & When
    List<String> authors = JsonPath.read(json, "$.store.book[*].author");
    log.debug("authors : {}", authors);

    // Then
    assertAll(
        () -> assertFalse(authors.isEmpty())
    );
  }

  @Order(2)
  @DisplayName("getAuthorsFromDocument_Document 에서 작가명을 조회")
  @Test
  void testGetAuthorsFromDocument() {

    // Given
    // JSON 문자열을 파싱하여 Document 객체를 생성
    Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

    // When
    List<String> authors = JsonPath.read(document, "$.store.book[*].author");
    log.debug("authors : {}", authors);

    // Then
    assertAll(
        () -> assertFalse(authors.isEmpty())
    );
  }

  @Order(3)
  @DisplayName("getAuthorsFromReadContext_ReadContext 에서 작가명을 조회")
  @Test
  void testGetAuthorsFromReadContext() {

    // Given
    // JSON 문자열을 파싱하여 ReadContext 객체를 생성
    ReadContext readContext = JsonPath.parse(json);

    // When
    List<String> authors = readContext.read("$.store.book[*].author");
    log.debug("authors : {}", authors);

    // Then
    assertAll(
        () -> assertFalse(authors.isEmpty())
    );
  }

  @Order(4)
  @DisplayName("getBooks"
      + "_조건(?(@.category == 'fiction' && @.price > 10)을 추가하여 10달러 초과의 비싼 책을 조회")
  @Test
  void testGetBooks() {

    // Given & When
    // 조건에 맞는 책들을 JSON 데이터에서 추출하여 리스트에 저장
    List<Map<String, Object>> books = JsonPath
        .using(Configuration.defaultConfiguration())
        .parse(json)
        .read("$.store.book[?(@.category == 'fiction' && @.price > 10)]", List.class);
    log.debug("books : {}", books);

    // Then
    assertAll(
        () -> assertFalse(books.isEmpty())
    );
  }

  @Order(5)
  @DisplayName("getBooksFiltered"
      + "_조건(?(@.category == 'fiction' && @.price > 10)을 추가하여 10달러 초과의 비싼 책을 조회")
  @Test
  void testGetBooksFiltered() {

    // Given
    Filter filter = filter(
        where("category").is("fiction").and("price").gt(10D)
    );

    // When
    // 조건에 맞는 책들을 JSON 데이터에서 추출하여 리스트에 저장
    List<Map<String, Object>> books = JsonPath
        .using(Configuration.defaultConfiguration())
        .parse(json)
        .read("$.store.book[?]", filter);
    log.debug("books : {}", books);

    // Then
    assertAll(
        () -> assertFalse(books.isEmpty())
    );
  }

  @Order(6)
  @DisplayName("getBookDto_JSON 문자열을 BookDto 객체로 변환하여 반환")
  @Test
  void testGetBook() {

    // Given
    String jsonPath = "$.store.book[0]";

    // When
    // JSON 문자열을 파싱하고, jsonPath로 주어진 경로의 데이터를 BookDto 객체로 변환
    Book book = JsonPath.parse(json).read(jsonPath, Book.class);
    log.debug("book : {}", book.toString());

    // Then
    assertAll(
        () -> assertNotNull(book),
        () -> assertEquals("reference", book.getCategory()),
        () -> assertEquals("Nigel Rees", book.getAuthor()),
        () -> assertEquals("Sayings of the Century", book.getTitle()),
        () -> assertEquals(8.95, book.getPrice())
    );
  }

  @Order(7)
  @DisplayName("getPathList_JSON 객체에서 경로 리스트를 조회")
  @Test
  void testGetPathList() {

    // Given
    // Option.AS_PATH_LIST는 JsonPath 표현식에 매칭되는 모든 경로를 리스트의 형태로 반환
    Configuration conf = Configuration.builder()
        .options(Option.AS_PATH_LIST)
        .build();

    String jsonPath = "$..author";

    List<String> expectedPathList = Arrays.asList(
        "$['store']['book'][0]['author']",
        "$['store']['book'][1]['author']",
        "$['store']['book'][2]['author']",
        "$['store']['book'][3]['author']");

    // When
    List<String> pathList = JsonPath
        .using(conf)
        .parse(json)
        .read(jsonPath);
    log.debug("pathList : {}", pathList);

    // Then
    assertAll(
        () -> assertFalse(pathList.isEmpty()),
        () -> assertArrayEquals(expectedPathList.toArray(), pathList.toArray())
    );
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Book {

    private String category;
    private String author;
    private String title;
    private String isbn;
    private Double price;
  }
}
