package collection.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TestMethodOrder(OrderAnnotation.class)
public class CollectionConverterTest {

  @Order(1)
  @DisplayName("arrayToList: Integer 배열을 List로 변환")
  @Test
  void testArrayToListInteger() {

    // given
    Integer[] array = {1, 2, 3, 4, 5};

    // when
    List<Integer> result = CollectionConverter.arrayToList(array);

    // then
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
    assertEquals(expected, result);
  }

  @Order(2)
  @DisplayName("arrayToList: String 배열을 List로 변환")
  @Test
  void testArrayToListString() {

    // given
    String[] array = {"a", "b", "c", "d", "e"};

    // when
    List<String> result = CollectionConverter.arrayToList(array);

    // then
    List<String> expected = Arrays.asList("a", "b", "c", "d", "e");
    assertEquals(expected, result);
  }

  @Order(3)
  @DisplayName("arrayToSet: Integer 배열을 List로 변환 후 Set으로 변환")
  @Test
  void testArrayToSetInteger() {

    // given
    Integer[] array = {1, 2, 3, 4, 5};

    // when
    Set<Integer> result = CollectionConverter.arrayToSet(array);

    // then
    Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
    assertEquals(expected, result);
  }

  @Order(4)
  @DisplayName("arrayToSet: String 배열을 List로 변환 후 Set으로 변환")
  @Test
  void testArrayToSetString() {

    // given
    String[] array = {"a", "b", "c", "d", "e"};

    // when
    Set<String> result = CollectionConverter.arrayToSet(array);

    // then
    Set<String> expected = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e"));
    assertEquals(expected, result);
  }

  @Order(5)
  @DisplayName("listToSet: Integer List를 Set으로 변환")
  @Test
  void testListToSetInteger() {

    // given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    // when
    Set<Integer> result = CollectionConverter.listToSet(list);

    // then
    Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
    assertEquals(expected, result);
  }

  @Order(6)
  @DisplayName("listToSet: String List를 Set으로 변환")
  @Test
  void testListToSetString() {

    // given
    List<String> list = Arrays.asList("a", "b", "c", "d", "e");

    // when
    Set<String> result = CollectionConverter.listToSet(list);

    // then
    Set<String> expected = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e"));
    assertEquals(expected, result);
  }

  @Order(7)
  @DisplayName("listToArray: Integer List를 배열로 변환")
  @Test
  void testListToArrayInteger() {

    // given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    // when
    Integer[] result = CollectionConverter.listToArray(list, new Integer[list.size()]);

    // then
    Integer[] expected = {1, 2, 3, 4, 5};
    assertArrayEquals(expected, result);
  }

  @Order(8)
  @DisplayName("listToArray: String List를 배열로 변환")
  @Test
  void testListToArrayString() {

    // given
    List<String> list = Arrays.asList("a", "b", "c", "d", "e");

    // when
    String[] result = CollectionConverter.listToArray(list, new String[list.size()]);

    // then
    String[] expected = {"a", "b", "c", "d", "e"};
    assertArrayEquals(expected, result);
  }

  @Order(9)
  @DisplayName("mapKeysToList: Map의 Integer Key들을 List로 변환")
  @Test
  void testMapKeysToListInteger() {

    // given
    Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");

    // when
    List<Integer> result = CollectionConverter.mapKeysToList(map);

    // then
    List<Integer> expected = Arrays.asList(1, 2, 3);
    assertAll(
        () -> assertTrue(result.containsAll(expected)),
        () -> assertTrue(expected.containsAll(result))
    );
  }

  @Order(10)
  @DisplayName("mapKeysToList: Map의 String Key들을 List로 변환")
  @Test
  void testMapKeysToListString() {

    // given
    Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);

    // when
    List<String> result = CollectionConverter.mapKeysToList(map);

    // then
    List<String> expected = Arrays.asList("a", "b", "c");
    assertAll(
        () -> assertTrue(result.containsAll(expected)),
        () -> assertTrue(expected.containsAll(result))
    );
  }

  @Order(11)
  @DisplayName("mapValuesToList: Map의 Integer Value들을 List로 변환")
  @Test
  void testMapValuesToListInteger() {

    // given
    Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);

    // when
    List<Integer> result = CollectionConverter.mapValuesToList(map);

    // then
    List<Integer> expected = Arrays.asList(1, 2, 3);
    assertAll(
        () -> assertTrue(result.containsAll(expected)),
        () -> assertTrue(expected.containsAll(result))
    );
  }

  @Order(12)
  @DisplayName("mapValuesToList: Map의 String Value들을 List로 변환")
  @Test
  void testMapValuesToListString() {

    // given
    Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");

    // when
    List<String> result = CollectionConverter.mapValuesToList(map);

    // then
    List<String> expected = Arrays.asList("one", "two", "three");
    assertAll(
        () -> assertTrue(result.containsAll(expected)),
        () -> assertTrue(expected.containsAll(result))
    );
  }

  @Order(13)
  @DisplayName("mapEntriesToList: Map의 Integer Entry들을 List로 변환")
  @Test
  void testMapEntriesToListInteger() {

    // given
    Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");

    // when
    List<Map.Entry<Integer, String>> result = CollectionConverter.mapEntriesToList(map);

    // then
    List<Map.Entry<Integer, String>> expected = Arrays.asList(
        Map.entry(1, "one"),
        Map.entry(2, "two"),
        Map.entry(3, "three")
    );
    assertAll(
        () -> assertTrue(result.containsAll(expected)),
        () -> assertTrue(expected.containsAll(result))
    );
  }

  @Order(14)
  @DisplayName("mapEntriesToList: Map의 String Entry들을 List로 변환")
  @Test
  void testMapEntriesToListString() {

    // given
    Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);

    // when
    List<Map.Entry<String, Integer>> result = CollectionConverter.mapEntriesToList(map);

    // then
    List<Map.Entry<String, Integer>> expected = Arrays.asList(
        Map.entry("a", 1),
        Map.entry("b", 2),
        Map.entry("c", 3)
    );
    assertAll(
        () -> assertTrue(result.containsAll(expected)),
        () -> assertTrue(expected.containsAll(result))
    );
  }

  @Order(15)
  @DisplayName("setToList: Integer Set을 List로 변환")
  @Test
  void testSetToListInteger() {

    // given
    Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

    // when
    List<Integer> result = CollectionConverter.setToList(set);

    // then
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
    assertAll(
        () -> assertEquals(expected.size(), result.size()),
        () -> assertTrue(result.containsAll(expected)),
        () -> assertTrue(expected.containsAll(result))
    );
  }

  @Order(16)
  @DisplayName("setToList: String Set을 List로 변환")
  @Test
  void testSetToListString() {

    // given
    Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e"));

    // when
    List<String> result = CollectionConverter.setToList(set);

    // then
    List<String> expected = Arrays.asList("a", "b", "c", "d", "e");
    assertAll(
        () -> assertEquals(expected.size(), result.size()),
        () -> assertTrue(result.containsAll(expected)),
        () -> assertTrue(expected.containsAll(result))
    );
  }
}
