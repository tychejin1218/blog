package util.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;

@TestMethodOrder(OrderAnnotation.class)
public class ListSortTest {

  private final ListSort listSort = new ListSort();

  @Order(1)
  @DisplayName("listSortAscending: 정수 리스트를 오름차순으로 정렬")
  @Test
  void testListSortAscendingForIntList() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 1, 9);

    // when
    List<Integer> result = listSort.listSortAscending(list);

    // then
    List<Integer> expected = Arrays.asList(1, 3, 5, 8, 9);
    assertEquals(expected, result);
  }

  @Order(2)
  @DisplayName("listSortDescending: 정수 리스트를 내림차순으로 정렬")
  @Test
  void testListSortDescendingForIntList() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 1, 9);

    // when
    List<Integer> result = listSort.listSortDescending(list);

    // then
    List<Integer> expected = Arrays.asList(9, 8, 5, 3, 1);
    assertEquals(expected, result);
  }

  @Order(3)
  @DisplayName("streamSortAscending: 정수 리스트를 오름차순으로 정렬")
  @Test
  void testStreamSortAscendingForIntList() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 1, 9);

    // when
    List<Integer> result = listSort.streamSortAscending(list);

    // then
    List<Integer> expected = Arrays.asList(1, 3, 5, 8, 9);
    assertEquals(expected, result);
  }

  @Order(4)
  @DisplayName("streamSortDescending: 정수 리스트를 내림차순으로 정렬")
  @Test
  void testStreamSortDescendingForIntList() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 1, 9);

    // when
    List<Integer> result = listSort.streamSortDescending(list);

    // then
    List<Integer> expected = Arrays.asList(9, 8, 5, 3, 1);
    assertEquals(expected, result);
  }

  @Order(5)
  @DisplayName("parallelStreamSortAscending: 정수 리스트를 병렬로 오름차순 정렬")
  @Test
  void testParallelStreamSortAscendingForIntList() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 1, 9);

    // when
    List<Integer> result = listSort.parallelStreamSortAscending(list);

    // then
    List<Integer> expected = Arrays.asList(1, 3, 5, 8, 9);
    assertEquals(expected, result);
  }

  @Order(6)
  @DisplayName("parallelStreamSortDescending: 정수 리스트를 병렬로 내림차순 정렬")
  @Test
  void testParallelStreamSortDescendingForIntList() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 1, 9);

    // when
    List<Integer> result = listSort.parallelStreamSortDescending(list);

    // then
    List<Integer> expected = Arrays.asList(9, 8, 5, 3, 1);
    assertEquals(expected, result);
  }

  @Order(7)
  @DisplayName("listSortAscending: 문자열 리스트를 오름차순으로 정렬")
  @Test
  void testListSortAscendingForStringList() {

    // given
    List<String> list = Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby");

    // when
    List<String> result = listSort.listSortAscending(list);

    // then
    List<String> expected = Arrays.asList("C++", "Java", "JavaScript", "Python", "Ruby");
    assertEquals(expected, result);
  }

  @Order(8)
  @DisplayName("listSortDescending: 문자열 리스트를 내림차순으로 정렬")
  @Test
  void testListSortDescendingForStringList() {

    // given
    List<String> list = Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby");

    // when
    List<String> result = listSort.listSortDescending(list);

    // then
    List<String> expected = Arrays.asList("Ruby", "Python", "JavaScript", "Java", "C++");
    assertEquals(expected, result);
  }

  @Order(9)
  @DisplayName("streamSortAscending: 문자열 리스트를 오름차순으로 정렬")
  @Test
  void testStreamSortAscendingForStringList() {

    // given
    List<String> list = Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby");

    // when
    List<String> result = listSort.streamSortAscending(list);

    // then
    List<String> expected = Arrays.asList("C++", "Java", "JavaScript", "Python", "Ruby");
    assertEquals(expected, result);
  }

  @Order(10)
  @DisplayName("streamSortDescending: 문자열 리스트를 내림차순으로 정렬")
  @Test
  void testStreamSortDescendingForStringList() {

    // given
    List<String> list = Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby");

    // when
    List<String> result = listSort.streamSortDescending(list);

    // then
    List<String> expected = Arrays.asList("Ruby", "Python", "JavaScript", "Java", "C++");
    assertEquals(expected, result);
  }

  @Order(11)
  @DisplayName("parallelStreamSortAscending: 문자열 리스트를 병렬로 오름차순 정렬")
  @Test
  void testParallelStreamSortAscendingForStringList() {

    // given
    List<String> list = Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby");

    // when
    List<String> result = listSort.parallelStreamSortAscending(list);

    // then
    List<String> expected = Arrays.asList("C++", "Java", "JavaScript", "Python", "Ruby");
    assertEquals(expected, result);
  }

  @Order(12)
  @DisplayName("parallelStreamSortDescending: 문자열 리스트를 병렬로 내림차순 정렬")
  @Test
  void testParallelStreamSortDescendingForStringList() {

    // given
    List<String> list = Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby");

    // when
    List<String> result = listSort.parallelStreamSortDescending(list);

    // then
    List<String> expected = Arrays.asList("Ruby", "Python", "JavaScript", "Java", "C++");
    assertEquals(expected, result);
  }
}
