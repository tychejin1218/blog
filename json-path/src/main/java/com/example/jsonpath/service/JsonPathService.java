package com.example.jsonpath.service;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

import com.example.jsonpath.dto.BookDto;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JsonPathService {

  public String readFileFromClassPath(String fileName) {

    StringBuilder data = new StringBuilder();
    try {
      ClassPathResource classPathResource = new ClassPathResource(fileName);
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8));
      String line;
      while ((line = reader.readLine()) != null) {
        data.append(line);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data.toString();
  }

  /**
   * Json 파일에서 작가명을 조회
   *
   * @param fileName JSON 파일명
   * @return 작가명을 반환
   */
  public List<String> getAuthorsFromJsonFile(String fileName) {

    // JSON 문자열
    String json = readFileFromClassPath(fileName);

    List<String> authors = JsonPath.read(json, "$.store.book[*].author");

    return authors;
  }

  /**
   * Document 객체에서 작가명을 조회
   *
   * @param fileName JSON 파일명
   * @return 작가명을 반환
   */
  public List<String> getAuthorsFromDocument(String fileName) {

    // JSON 문자열
    String json = readFileFromClassPath(fileName);

    // JSON 문자열을 파싱하여 Document 객체를 생성
    Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

    List<String> authors = JsonPath.read(document, "$.store.book[*].author");
    return authors;
  }

  /**
   * ReadContext 객체에서 작가명을 조회
   *
   * @param fileName JSON 파일명
   * @return 작가명을 반환
   */
  public List<String> getAuthorsFromReadContext(String fileName) {

    // JSON 문자열
    String json = readFileFromClassPath(fileName);

    // JSON 문자열을 파싱하여 ReadContext 객체를 생성
    ReadContext readContext = JsonPath.parse(json);

    List<String> authors = readContext.read("$.store.book[*].author");

    return authors;
  }

  /**
   * 조건(?(@.category == 'fiction' && @.price > 10))을 추가하여 조회
   *
   * @param fileName JSON 파일명
   * @return 조건에 만족하는 리스트(Map 형태)를 반환
   */
  public List<Map<String, Object>> getBooks(String fileName) {

    // JSON 문자열
    String json = readFileFromClassPath(fileName);

    // 조건에 맞는 책들을 JSON 데이터에서 추출하여 리스트에 저장
    List<Map<String, Object>> books = JsonPath
        .using(Configuration.defaultConfiguration())
        .parse(json)
        .read("$.store.book[?(@.category == 'fiction' && @.price > 10)]", List.class);

    return books;
  }

  /**
   * 조건(?(@.category == 'fiction' && @.price > 10))을 필터로 추가하여 조회
   *
   * @param fileName JSON 파일명
   * @return 조건에 만족하는 리스트(Map 형태)를 반환
   */
  public List<Map<String, Object>> getBooksFiltered(String fileName) {

    // JSON 문자열
    String json = readFileFromClassPath(fileName);

    Filter filter = filter(
        where("category").is("fiction").and("price").gt(10D)
    );

    // 조건에 맞는 책들을 JSON 데이터에서 추출하여 리스트에 저장
    List<Map<String, Object>> expensiveBooks = JsonPath
        .using(Configuration.defaultConfiguration())
        .parse(json)
        .read("$.store.book[?]", filter);

    return expensiveBooks;
  }


  /**
   * JSON 문자열을 BookDto 객체로 변환하여 반환
   *
   * @param fileName JSON 파일명
   * @param jsonPath JSONPath 문자열
   * @return BookDto 객체를 반환
   */
  public BookDto getBookDto(String fileName, String jsonPath) {

    // JSON 문자열
    String json = readFileFromClassPath(fileName);

    // JSON 문자열을 파싱하고, jsonPath로 주어진 경로의 데이터를 BookDto 객체로 변환
    BookDto book = JsonPath.parse(json).read(jsonPath, BookDto.class);

    return book;
  }

  /**
   * JSON 객체에서 경로 리스트를 조회
   *
   * @param fileName JSON 파일명
   * @param jsonPath JSONPath 문자열
   * @return 경로 리스트 반환
   */
  public List<String> getPathList(String fileName, String jsonPath) {

    // Option.AS_PATH_LIST는 JsonPath 표현식에 매칭되는 모든 경로를 리스트의 형태로 반환
    Configuration conf = Configuration.builder()
        .options(Option.AS_PATH_LIST)
        .build();

    // JSON 문자열
    String json = readFileFromClassPath(fileName);

    List<String> pathList = JsonPath
        .using(conf)
        .parse(json)
        .read(jsonPath);

    return pathList;
  }
}
