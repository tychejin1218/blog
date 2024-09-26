package util.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class MapSearchTest {

  private final MapSearch mapSearch = new MapSearch();

  @Order(1)
  @DisplayName("containsKey: 특정 키가 맵에 포함되어 있는지 확인")
  @Test
  void testContainsKey() {

    // given
    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);

    // when
    boolean containsOne = mapSearch.containsKey(map, "one");
    boolean containsFour = mapSearch.containsKey(map, "four");

    // then
    assertAll(
        () -> assertTrue(containsOne),
        () -> assertFalse(containsFour)
    );
  }

  @Order(2)
  @DisplayName("containsValue: 특정 값이 맵에 포함되어 있는지 확인")
  @Test
  void testContainsValue() {

    // given
    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);

    // when
    boolean containsValue2 = mapSearch.containsValue(map, 2);
    boolean containsValue5 = mapSearch.containsValue(map, 5);

    // then
    assertAll(
        () -> assertTrue(containsValue2),
        () -> assertFalse(containsValue5)
    );
  }

  @Order(3)
  @DisplayName("getValueByKey: 키를 사용하여 값을 조회")
  @Test
  void testGetValueByKey() {

    // given
    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);

    // when
    int valueOne = mapSearch.getValueByKey(map, "one");
    Integer valueFour = mapSearch.getValueByKey(map, "four");

    // then
    assertAll(
        () -> assertEquals(1, valueOne),
        () -> assertNull(valueFour)
    );
  }

  @Order(4)
  @DisplayName("getKeyByValue: 값을 사용하여 키를 조회")
  @Test
  void testGetKeyByValue() {

    // given
    Map<String, Integer> map = new LinkedHashMap<>() {{
      put("one", 1);
      put("two", 2);
      put("three", 3);
      put("duplicate", 2);
    }};

    // when
    String keyForValueTwo = mapSearch.getKeyByValue(map, 2);
    String keyForValueFour = mapSearch.getKeyByValue(map, 4);

    // then
    assertAll(
        () -> assertEquals("two", keyForValueTwo),
        () -> assertNull(keyForValueFour)
    );
  }

  @Order(5)
  @DisplayName("findEntryByCondition: 조건에 부합하는 첫 번째 엔트리 찾기")
  @Test
  void testFindEntryByCondition() {

    // given
    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
    Predicate<Map.Entry<String, Integer>> condition = entry -> entry.getValue() > 1;

    // when
    Optional<Map.Entry<String, Integer>> entry = mapSearch.findEntryByCondition(map, condition);

    // then
    assertAll(
        () -> assertTrue(entry.isPresent()),
        () -> assertEquals("two", entry.get().getKey())
    );
  }

  @Order(6)
  @DisplayName("findEntryByPattern: 패턴에 맞는 값을 가진 첫 번째 엔트리 찾기")
  @Test
  void testFindEntryByPattern() {

    // given
    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
    Predicate<Integer> patternCondition = value -> value > 2;

    // when
    Optional<Map.Entry<String, Integer>> entry = mapSearch.findEntryByPattern(map, patternCondition);

    // then
    assertAll(
        () -> assertTrue(entry.isPresent()),
        () -> assertEquals("three", entry.get().getKey())
    );
  }

  @Order(7)
  @DisplayName("findEntryByCondition: 조건에 부합하는 첫 번째 엔트리가 없을 때")
  @Test
  void testFindEntryByConditionNoMatch() {

    // given
    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
    Predicate<Map.Entry<String, Integer>> condition = entry -> entry.getValue() > 3;

    // when
    Optional<Map.Entry<String, Integer>> entry = mapSearch.findEntryByCondition(map, condition);

    // then
    assertFalse(entry.isPresent());
  }

  @Order(8)
  @DisplayName("findEntryByCondition: 맵이 비어있을 때")
  @Test
  void testFindEntryByConditionEmptyMap() {

    // given
    Map<String, Integer> map = new HashMap<>();
    Predicate<Map.Entry<String, Integer>> condition = entry -> entry.getValue() > 1;

    // when
    Optional<Map.Entry<String, Integer>> entry = mapSearch.findEntryByCondition(map, condition);

    // then
    assertFalse(entry.isPresent());
  }

  @Order(9)
  @DisplayName("findEntryByPattern: 패턴에 부합하는 첫 번째 엔트리가 없을 때")
  @Test
  void testFindEntryByPatternNoMatch() {

    // given
    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
    Predicate<Integer> patternCondition = value -> value > 3;

    // when
    Optional<Map.Entry<String, Integer>> entry = mapSearch.findEntryByPattern(map, patternCondition);

    // then
    assertFalse(entry.isPresent());
  }

  @Order(10)
  @DisplayName("findEntryByPattern: 맵이 비어있을 때")
  @Test
  void testFindEntryByPatternEmptyMap() {

    // given
    Map<String, Integer> map = new HashMap<>();
    Predicate<Integer> patternCondition = value -> value > 1;

    // when
    Optional<Map.Entry<String, Integer>> entry = mapSearch.findEntryByPattern(map, patternCondition);

    // then
    assertFalse(entry.isPresent());
  }

  @Order(11)
  @DisplayName("findEntryByPattern: 여러 조건들이 만족될 때")
  @Test
  void testFindEntryByPatternMultipleMatches() {

    // given
    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3, "anotherThree", 3);
    Predicate<Integer> patternCondition = value -> value == 3;

    // when
    Optional<Map.Entry<String, Integer>> entry = mapSearch.findEntryByPattern(map, patternCondition);

    // then
    assertAll(
        () -> assertTrue(entry.isPresent()),
        () -> assertEquals("three", entry.get().getKey())
    );
  }
}
