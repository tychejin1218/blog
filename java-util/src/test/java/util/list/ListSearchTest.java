package util.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class ListSearchTest {

  private ListSearch listSearch = new ListSearch();

  @Order(1)
  @DisplayName("linearSearch: 선형 검색")
  @Test
  void testLinearSearch() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2);
    int valueToSearch = 8;

    // when
    int actualIndex = listSearch.linearSearch(list, valueToSearch);

    // then
    int expectedIndex = 2;
    assertEquals(expectedIndex, actualIndex);
  }

  @Test
  @Order(2)
  @DisplayName("binarySearch: 이진 검색")
  void testBinarySearch() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2);
    list.sort(Integer::compareTo);

    int valueToSearch = 8;

    // when
    int actualIndex = listSearch.binarySearch(list, valueToSearch);

    // then
    int expectedIndex = 4;
    assertEquals(expectedIndex, actualIndex);
  }

  @Order(3)
  @DisplayName("binarySearchUsingLibrary: Collections.binarySearch 메서드를 사용한 이진 검색")
  @Test
  void testBinarySearchUsingLibrary() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2);
    list.sort(Integer::compareTo);

    int valueToSearch = 8;

    // when
    int actualIndex = listSearch.binarySearchUsingLibrary(list, valueToSearch);

    // then
    int expectedIndex = 4;
    assertEquals(expectedIndex, actualIndex);
  }

  @Order(4)
  @DisplayName("findAllIndices: 특정 값의 모든 인덱스 찾기")
  @Test
  void testFindAllIndices() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2, 3, 8, 3);
    int valueToFind = 3;
    List<Integer> expectedIndices = Arrays.asList(1, 5, 7);

    // when
    List<Integer> actualIndices = listSearch.findAllIndices(list, valueToFind);

    // then
    assertEquals(expectedIndices, actualIndices);
  }

  @Order(4)
  @DisplayName("findMaxUsingForLoop: for 문을 사용하여 리스트 최대값 찾기")
  @Test
  void testFindMaxUsingForLoop() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2);
    int expectedMax = 8;

    // when
    int actualMax = listSearch.findMaxUsingForLoop(list);

    // then
    assertEquals(expectedMax, actualMax);
  }

  @Order(6)
  @DisplayName("findMax: 스트림을 사용하여 리스트 최대값 찾기")
  @Test
  void testFindMax() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2);
    int expectedMax = 8;

    // when
    int actualMax = listSearch.findMax(list);

    // then
    assertEquals(expectedMax, actualMax);
  }

  @Order(7)
  @DisplayName("findMinUsingForLoop: for 문을 사용하여 리스트 최소값 찾기")
  @Test
  void testFindMinUsingForLoop() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2);
    int expectedMin = 2;

    // when
    int actualMin = listSearch.findMinUsingForLoop(list);

    // then
    assertEquals(expectedMin, actualMin);
  }

  @Order(8)
  @DisplayName("findMin: 스트림을 사용하여 리스트 최소값 찾기")
  @Test
  void testFindMin() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2);
    int expectedMin = 2;

    // when
    int actualMin = listSearch.findMin(list);

    // then
    assertEquals(expectedMin, actualMin);
  }

  @Order(9)
  @DisplayName("findMostFrequent: 가장 빈번하게 나타나는 값 찾기")
  @Test
  void testFindMostFrequent() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2, 3, 8, 3);
    int expectedMostFrequent = 3;

    // when
    int actualMostFrequent = listSearch.findMostFrequent(list);

    // then
    assertEquals(expectedMostFrequent, actualMostFrequent);
  }

  @Order(10)
  @DisplayName("findDuplicates: 중복값 찾기")
  @Test
  void testFindDuplicates() {

    // given
    List<Integer> list = Arrays.asList(5, 3, 8, 4, 2, 3, 8, 3);
    Set<Integer> expectedDuplicates = Set.of(3, 8);

    // when
    Set<Integer> actualDuplicates = listSearch.findDuplicates(list);

    // then
    assertEquals(expectedDuplicates, actualDuplicates);
  }
}
