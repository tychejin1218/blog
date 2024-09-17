package util.array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class ArraySortTest {

  private final ArraySort arraySort = new ArraySort();

  @Order(1)
  @DisplayName("arraySortAscending: 정수 배열을 오름차순으로 정렬")
  @Test
  void testArraySortAscendingForIntArray() {

    // given
    int[] arr = {5, 3, 8, 1, 9};

    // when
    int[] result = arraySort.arraySortAscending(arr);

    // then
    int[] expected = {1, 3, 5, 8, 9};
    assertArrayEquals(expected, result);
  }

  @Order(2)
  @DisplayName("arraySortDescending: 정수 배열을 내림차순으로 정렬")
  @Test
  void testArraySortDescendingForIntArray() {

    // given
    int[] arr = {5, 3, 8, 1, 9};

    // when
    int[] result = arraySort.arraySortDescending(arr);

    // then
    int[] expected = {9, 8, 5, 3, 1};
    assertArrayEquals(expected, result);
  }

  @Order(3)
  @DisplayName("parallelSortAscending: 정수 배열을 병렬로 오름차순 정렬")
  @Test
  void testParallelSortAscendingForIntArray() {

    // given
    int[] arr = {5, 3, 8, 1, 9};

    // when
    int[] result = arraySort.parallelSortAscending(arr);

    // then
    int[] expected = {1, 3, 5, 8, 9};
    assertArrayEquals(expected, result);
  }

  @Order(4)
  @DisplayName("parallelSortDescending: 정수 배열을 병렬로 내림차순 정렬")
  @Test
  void testParallelSortDescendingForIntArray() {

    // given
    int[] arr = {5, 3, 8, 1, 9};

    // when
    int[] result = arraySort.parallelSortDescending(arr);

    // then
    int[] expected = {9, 8, 5, 3, 1};
    assertArrayEquals(expected, result);
  }

  @Order(5)
  @DisplayName("arraySortAscending: 문자열 배열을 오름차순으로 정렬")
  @Test
  void testArraySortAscendingForStringArray() {

    // given
    String[] arr = {"Java", "Python", "C++", "JavaScript", "Ruby"};

    // when
    String[] result = arraySort.arraySortAscending(arr);

    // then
    String[] expected = {"C++", "Java", "JavaScript", "Python", "Ruby"};
    assertArrayEquals(expected, result);
  }

  @Order(6)
  @DisplayName("arraySortDescending: 문자열 배열을 내림차순으로 정렬")
  @Test
  void testArraySortDescendingForStringArray() {

    // given
    String[] arr = {"Java", "Python", "C++", "JavaScript", "Ruby"};

    // when
    String[] result = arraySort.arraySortDescending(arr);

    // then
    String[] expected = {"Ruby", "Python", "JavaScript", "Java", "C++"};
    assertArrayEquals(expected, result);
  }

  @Order(7)
  @DisplayName("parallelSortAscending: 문자열 배열을 병렬로 오름차순 정렬")
  @Test
  void testParallelSortAscendingForStringArray() {

    // given
    String[] arr = {"Java", "Python", "C++", "JavaScript", "Ruby"};

    // when
    String[] result = arraySort.parallelSortAscending(arr);

    // then
    String[] expected = {"C++", "Java", "JavaScript", "Python", "Ruby"};
    assertArrayEquals(expected, result);
  }

  @Order(8)
  @DisplayName("parallelSortDescending: 문자열 배열을 병렬로 내림차순으로 정렬")
  @Test
  void testParallelSortDescendingForStringArray() {

    // given
    String[] arr = {"Java", "Python", "C++", "JavaScript", "Ruby"};

    // when
    String[] result = arraySort.parallelSortDescending(arr);

    // then
    String[] expected = {"Ruby", "Python", "JavaScript", "Java", "C++"};
    assertArrayEquals(expected, result);
  }
}
