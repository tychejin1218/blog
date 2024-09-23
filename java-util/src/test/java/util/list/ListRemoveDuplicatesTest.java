package util.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ListRemoveDuplicatesTest {

  private ListRemoveDuplicates listRemoveDuplicates;

  @BeforeEach
  void setUp() {
    listRemoveDuplicates = new ListRemoveDuplicates();
  }

  @Order(1)
  @DisplayName("removeDuplicatesUsingList: 정수 리스트에서 중복 제거")
  @Test
  void testRemoveDuplicatesUsingListInteger() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 1, 2, 5);

    // When
    List<Integer> result = listRemoveDuplicates.removeDuplicatesUsingList(list);

    // Then
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
    assertEquals(expected, result);
  }

  @Order(2)
  @DisplayName("removeDuplicatesUsingSet: 정수 리스트에서 중복 제거")
  @Test
  void testRemoveDuplicatesUsingSetInteger() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 1, 2, 5);

    // When
    List<Integer> result = listRemoveDuplicates.removeDuplicatesUsingSet(list);

    // Then
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
    assertEquals(expected, result);
  }

  @Order(3)
  @DisplayName("removeDuplicatesUsingStream: 정수 리스트에서 중복 제거")
  @Test
  void testRemoveDuplicatesUsingStreamInteger() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 1, 2, 5);

    // When
    List<Integer> result = listRemoveDuplicates.removeDuplicatesUsingStream(list);

    // Then
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
    assertEquals(expected, result);
  }

  @Order(4)
  @DisplayName("removeDuplicatesUsingTreeSet: 정수 리스트에서 중복 제거 및 정렬")
  @Test
  void testRemoveDuplicatesUsingTreeSetInteger() {

    // Given
    List<Integer> list = Arrays.asList(4, 3, 2, 1, 5, 4, 3, 2, 1);

    // When
    List<Integer> result = listRemoveDuplicates.removeDuplicatesUsingTreeSet(list);

    // Then
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
    assertEquals(expected, result);
  }

  @Order(5)
  @DisplayName("removeDuplicatesUsingLinkedHashSet: 정수 리스트에서 중복 제거 및 삽입 순서 유지")
  @Test
  void testRemoveDuplicatesUsingLinkedHashSetInteger() {

    // Given
    List<Integer> list = Arrays.asList(5, 4, 3, 2, 1, 5, 4, 3, 2, 1);

    // When
    List<Integer> result = listRemoveDuplicates.removeDuplicatesUsingLinkedHashSet(list);

    // Then
    List<Integer> expected = Arrays.asList(5, 4, 3, 2, 1);
    assertEquals(expected, result);
  }

  @Order(6)
  @DisplayName("removeDuplicatesUsingList: 문자열 리스트에서 중복 제거")
  @Test
  void testRemoveDuplicatesUsingListString() {

    // Given
    List<String> list = Arrays.asList("Java", "Python", "Java", "C#", "Python", "JavaScript");

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingList(list);

    // Then
    List<String> expected = Arrays.asList("Java", "Python", "C#", "JavaScript");
    assertEquals(expected, result);
  }

  @Order(7)
  @DisplayName("removeDuplicatesUsingSet: 문자열 리스트에서 중복 제거")
  @Test
  void testRemoveDuplicatesUsingSetString() {

    // Given
    List<String> list = Arrays.asList("Java", "Python", "Java", "C#", "Python", "JavaScript");

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingSet(list);
    List<String> sortedResult = new ArrayList<>(result);
    Collections.sort(sortedResult);

    // Then
    List<String> expected = Arrays.asList("C#", "Java", "JavaScript", "Python");
    assertEquals(expected, sortedResult);
  }

  @Order(8)
  @DisplayName("removeDuplicatesUsingStream: 문자열 리스트에서 중복 제거")
  @Test
  void testRemoveDuplicatesUsingStreamString() {

    // Given
    List<String> list = Arrays.asList("Java", "Python", "Java", "C#", "Python", "JavaScript");

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingStream(list);

    // Then
    List<String> expected = Arrays.asList("Java", "Python", "C#", "JavaScript");
    assertEquals(expected, result);
  }

  @Order(9)
  @DisplayName("removeDuplicatesUsingTreeSet: 문자열 리스트에서 중복 제거 및 정렬")
  @Test
  void testRemoveDuplicatesUsingTreeSetString() {

    // Given
    List<String> list = Arrays.asList("JavaScript", "Python", "Java", "C#", "Java", "Python");

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingTreeSet(list);

    // Then
    List<String> expected = Arrays.asList("C#", "Java", "JavaScript", "Python");
    assertEquals(expected, result);
  }

  @Order(10)
  @DisplayName("removeDuplicatesUsingLinkedHashSet: 문자열 리스트에서 중복 제거 및 삽입 순서 유지")
  @Test
  void testRemoveDuplicatesUsingLinkedHashSetString() {

    // Given
    List<String> list = Arrays.asList("JavaScript", "Python", "Java", "C#", "Java", "Python");

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingLinkedHashSet(list);

    // Then
    List<String> expected = Arrays.asList("JavaScript", "Python", "Java", "C#");
    assertEquals(expected, result);
  }

  @Order(11)
  @DisplayName("removeDuplicatesUsingList: 빈 문자열 리스트 처리")
  @Test
  void testRemoveDuplicatesUsingListEmptyListString() {

    // Given
    List<String> emptyList = Collections.emptyList();

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingList(emptyList);

    // Then
    assertTrue(result.isEmpty());
  }

  @Order(12)
  @DisplayName("removeDuplicatesUsingSet: 빈 문자열 리스트 처리")
  @Test
  void testRemoveDuplicatesUsingSetEmptyListString() {

    // Given
    List<String> emptyList = Collections.emptyList();

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingSet(emptyList);

    // Then
    assertTrue(result.isEmpty());
  }

  @Order(13)
  @DisplayName("removeDuplicatesUsingStream: 빈 문자열 리스트 처리")
  @Test
  void testRemoveDuplicatesUsingStreamEmptyListString() {

    // Given
    List<String> emptyList = Collections.emptyList();

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingStream(emptyList);

    // Then
    assertTrue(result.isEmpty());
  }

  @Order(14)
  @DisplayName("removeDuplicatesUsingTreeSet: 빈 문자열 리스트 처리")
  @Test
  void testRemoveDuplicatesUsingTreeSetEmptyListString() {

    // Given
    List<String> emptyList = Collections.emptyList();

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingTreeSet(emptyList);

    // Then
    assertTrue(result.isEmpty());
  }

  @Order(15)
  @DisplayName("removeDuplicatesUsingLinkedHashSet: 빈 문자열 리스트 처리")
  @Test
  void testRemoveDuplicatesUsingLinkedHashSetEmptyListString() {

    // Given
    List<String> emptyList = Collections.emptyList();

    // When
    List<String> result = listRemoveDuplicates.removeDuplicatesUsingLinkedHashSet(emptyList);

    // Then
    assertTrue(result.isEmpty());
  }
}
