package com.example.jsonpath.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.jsonpath.dto.BookDto;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class JsonPathServiceTest {

  @Autowired
  JsonPathService jsonPathService;

  @Order(1)
  @DisplayName("getAuthorsFromJsonFile_Json 파일에서 작가명을 조회")
  @Test
  void testGetAuthorsFromJsonFile() {

    // Given
    String fileName = "json/store.json";

    // When
    List<String> authors = jsonPathService.getAuthorsFromJsonFile(fileName);
    log.debug("authors : {}", authors);

    // Then
    assertAll(
        () -> assertFalse(authors.isEmpty())
    );
  }

  @Order(2)
  @DisplayName("getAuthorsFromDocument_Document에서 작가명을 조회")
  @Test
  void testGetAuthorsFromDocument() {

    // Given
    String fileName = "json/store.json";

    // When
    List<String> authors = jsonPathService.getAuthorsFromDocument(fileName);
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
    String fileName = "json/store.json";

    // When
    List<String> authors = jsonPathService.getAuthorsFromReadContext(fileName);
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

    // Given
    String fileName = "json/store.json";

    // When
    List<Map<String, Object>> books = jsonPathService.getBooks(fileName);
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
    String fileName = "json/store.json";

    // When
    List<Map<String, Object>> books = jsonPathService.getBooksFiltered(fileName);
    log.debug("books : {}", books);

    // Then
    assertAll(
        () -> assertFalse(books.isEmpty())
    );
  }

  @Order(6)
  @DisplayName("getBookDto_JSON 문자열을 BookDto 객체로 변환하여 반환")
  @Test
  void testGetBookDto() {

    // Given
    String fileName = "json/store.json";
    String jsonPath = "$.store.book[0]";

    // When
    BookDto book = jsonPathService.getBookDto(fileName, jsonPath);
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
}
