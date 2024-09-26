package collection.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import collection.array.ArraySearch;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class ArraySearchTest {

  private ArraySearch arraySearch = new ArraySearch();

  @Order(1)
  @DisplayName("linearSearch: 선형 검색")
  @Test
  void testLinearSearch() {

    // given
    int[] array = new int[]{5, 3, 8, 4, 2};
    int valueToSearch = 8;

    // when
    int actualIndex = arraySearch.linearSearch(array, valueToSearch);

    // then
    int expectedIndex = 2;
    assertEquals(expectedIndex, actualIndex);
  }

  @Test
  @Order(2)
  @DisplayName("binarySearch: 이진 검색")
  void testBinarySearch() {

    // given
    int[] array = new int[]{5, 3, 8, 4, 2};
    Arrays.sort(array);

    int valueToSearch = 8;

    // when
    int actualIndex = arraySearch.binarySearch(array, valueToSearch);

    // then
    int expectedIndex = 4;
    assertEquals(expectedIndex, actualIndex);
  }

  @Order(3)
  @DisplayName("binarySearchUsingLibrary: sArrays.binarySearch 메서드를 사용한 이진 검색")
  @Test
  void testBinarySearchUsingLibrary() {

    // given
    int[] array = new int[]{5, 3, 8, 4, 2};
    Arrays.sort(array);

    int valueToSearch = 8;

    // when
    int actualIndex = arraySearch.binarySearchUsingLibrary(array, valueToSearch);

    // then
    int expectedIndex = 4;
    assertEquals(expectedIndex, actualIndex);
  }

  @Order(4)
  @DisplayName("containsIn2DArray: 2차원 배열 내 값의 존재 여부 확인")
  @Test
  void testContainsIn2DArray() {

    // given
    int[][] array2D = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    int valueToSearch = 5;

    // when
    boolean result = arraySearch.containsIn2DArray(array2D, valueToSearch);

    // then
    assertTrue(result);
  }

  @Order(5)
  @DisplayName("findMaxUsingForLoop: for 루프를 사용하여 배열 최대값 찾기")
  @Test
  void testFindMaxUsingForLoop() {

    // given
    int[] array = new int[]{5, 3, 8, 4, 2};
    int expectedMax = 8;

    // when
    int actualMax = arraySearch.findMaxUsingForLoop(array);

    // then
    assertEquals(expectedMax, actualMax);
  }

  @Order(6)
  @DisplayName("findMax: 스트림을 사용하여 배열 최대값 찾기")
  @Test
  void testFindMax() {

    // given
    int[] array = new int[]{5, 3, 8, 4, 2};
    int expectedMax = 8;

    // when
    int actualMax = arraySearch.findMax(array);

    // then
    assertEquals(expectedMax, actualMax);
  }

  @Order(7)
  @DisplayName("findMinUsingForLoop: for 루프를 사용하여 배열 최소값 찾기")
  @Test
  void testFindMinUsingForLoop() {

    // given
    int[] array = new int[]{5, 3, 8, 4, 2};
    int expectedMin = 2;

    // when
    int actualMin = arraySearch.findMinUsingForLoop(array);

    // then
    assertEquals(expectedMin, actualMin);
  }

  @Order(8)
  @DisplayName("findMin: 스트림을 사용하여 배열 최소값 찾기")
  @Test
  void testFindMin() {

    // given
    int[] array = new int[]{5, 3, 8, 4, 2};
    int expectedMin = 2;

    // when
    int actualMin = arraySearch.findMin(array);

    // then
    assertEquals(expectedMin, actualMin);
  }
}
