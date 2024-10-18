package util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonUtilsTest {

  @Order(1)
  @DisplayName("JSON 문자열을 객체로 변환")
  @Test
  public void testFromJsonString() throws IOException {

    // Given
    String personJson = "{\"name\":\"홍길동\",\"age\":30}";

    // When
    Person result = JsonUtils.fromJsonString(personJson, Person.class);

    // Then
    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals("홍길동", result.getName()),
        () -> assertEquals(30, result.getAge())
    );
  }

  @Order(2)
  @DisplayName("객체를 JSON 문자열로 변환")
  @Test
  public void testToJsonString() throws IOException {

    // Given
    Person person = new Person("홍길동", 30);

    // When
    String jsonString = JsonUtils.toJsonString(person);

    // Then
    assertAll(
        () -> assertNotNull(jsonString),
        () -> assertTrue(jsonString.contains("\"name\":\"홍길동\"")),
        () -> assertTrue(jsonString.contains("\"age\":30"))
    );
  }

  @Order(3)
  @DisplayName("Map을 JSONString으로 변환")
  @Test
  public void testMapToJsonString() throws JsonProcessingException {

    // Given
    Map<String, Object> personMap = new HashMap<>();
    personMap.put("name", "홍길동");
    personMap.put("age", 30);

    // When
    String jsonString = JsonUtils.mapToJsonString(personMap);

    // Then
    assertAll(
        () -> assertNotNull(jsonString),
        () -> assertTrue(jsonString.contains("\"name\":\"홍길동\"")),
        () -> assertTrue(jsonString.contains("\"age\":30"))
    );
  }

  @Order(4)
  @DisplayName("List<Map>을 JSONString으로 변환")
  @Test
  public void testListOfMapToJsonString() throws JsonProcessingException {

    // Given
    Map<String, Object> personMap = new HashMap<>();
    personMap.put("name", "홍길동");
    personMap.put("age", 30);
    List<Map<String, Object>> givenListOfMap = List.of(personMap);

    // When
    String jsonString = JsonUtils.listOfMapToJsonString(givenListOfMap);

    // Then
    assertAll(
        () -> assertNotNull(jsonString),
        () -> assertTrue(jsonString.contains("\"name\":\"홍길동\"")),
        () -> assertTrue(jsonString.contains("\"age\":30"))
    );
  }

  @Order(5)
  @DisplayName("JSON 문자열을 Map으로 변환")
  @Test
  public void testJsonStringToMap() throws IOException {

    // Given
    String personJson = "{\"name\":\"홍길동\",\"age\":30}";

    // When
    Map<String, Object> result = JsonUtils.jsonStringToMap(personJson);

    // Then
    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals("홍길동", result.get("name")),
        () -> assertEquals(30, result.get("age"))
    );
  }

  @Order(6)
  @DisplayName("JSON 문자열을 List로 변환")
  @Test
  public void testJsonStringToList() throws IOException {

    // Given
    String jsonArrayString = "[{\"name\":\"홍길동\",\"age\":30}, {\"name\":\"이순신\",\"age\":45}]";

    // When
    List<Person> result = JsonUtils.jsonStringToList(jsonArrayString, Person.class);

    // Then
    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals(2, result.size()),
        () -> {
          Person person1 = result.get(0);
          assertEquals("홍길동", person1.getName());
          assertEquals(30, person1.getAge());
        },
        () -> {
          Person person2 = result.get(1);
          assertEquals("이순신", person2.getName());
          assertEquals(45, person2.getAge());
        }
    );
  }

  @Order(7)
  @DisplayName("JSONObject를 Map<String, String>으로 변환")
  @Test
  public void testJsonObjectToMap() {

    // Given
    Map<String, Object> personMap = new HashMap<>();
    personMap.put("name", "홍길동");
    personMap.put("age", 30);

    // When
    Map<String, String> resultMap = JsonUtils.jsonObjectToMap(personMap);

    // Then
    assertAll(
        () -> assertNotNull(resultMap),
        () -> assertEquals("홍길동", resultMap.get("name")),
        () -> assertEquals("30", resultMap.get("age"))
    );
  }

  @Order(8)
  @DisplayName("JSON 배열을 List<Map<String, String>>으로 변환")
  @Test
  public void testJsonArrayToListOfMap() throws IOException {

    // Given
    String givenJsonArrayString = "[{ \"name\": \"홍길동\", \"age\": 30 }]";

    // When
    List<Map<String, String>> result = JsonUtils.jsonArrayToListOfMap(givenJsonArrayString);

    // Then
    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals(1, result.size()),
        () -> assertEquals("홍길동", result.get(0).get("name")),
        () -> assertEquals("30", result.get(0).get("age"))
    );
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Person {

    private String name;
    private int age;
  }
}
