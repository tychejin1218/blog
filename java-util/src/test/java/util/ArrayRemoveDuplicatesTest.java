package util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArrayRemoveDuplicatesTest {

  private final ArrayRemoveDuplicates arrayRemoveDuplicates = new ArrayRemoveDuplicates();

  @Order(1)
  @DisplayName("removeDuplicatesUsingSet: 일반 배열에서 중복 제거")
  @Test
  public void testRemoveDuplicatesUsingSet() {

    // Given
    int[] array = {1, 2, 3, 4, 1, 2, 5};

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingSet(array);

    // Then
    int[] expected = {1, 2, 3, 4, 5};
    assertArrayEquals(expected, result);
  }

  @Order(2)
  @DisplayName("removeDuplicatesUsingSet: null 배열 처리")
  @Test
  public void testRemoveDuplicatesUsingSet_NullArray() {

    // Given
    int[] nullArray = null;

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingSet(nullArray);

    // Then
    assertNull(result);
  }

  @Order(3)
  @DisplayName("removeDuplicatesUsingSet: 빈 배열 처리")
  @Test
  public void testRemoveDuplicatesUsingSet_EmptyArray() {

    // Given
    int[] emptyArray = {};

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingSet(emptyArray);

    // Then
    assertArrayEquals(new int[0], result);
  }

  @Order(4)
  @DisplayName("removeDuplicatesUsingStream: 일반 배열에서 중복 제거")
  @Test
  public void testRemoveDuplicatesUsingStream() {

    // Given
    int[] array = {1, 2, 3, 4, 1, 2, 5};

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingStream(array);

    // Then
    int[] expected = {1, 2, 3, 4, 5};
    assertArrayEquals(expected, result);
  }

  @Order(5)
  @DisplayName("removeDuplicatesUsingStream: null 배열 처리")
  @Test
  public void testRemoveDuplicatesUsingStream_NullArray() {

    // Given
    int[] nullArray = null;

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingStream(nullArray);

    // Then
    assertNull(result);
  }

  @Order(6)
  @DisplayName("removeDuplicatesUsingStream: 빈 배열 처리")
  @Test
  public void testRemoveDuplicatesUsingStream_EmptyArray() {

    // Given
    int[] emptyArray = {};

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingStream(emptyArray);

    // Then
    assertArrayEquals(new int[0], result);
  }

  @DisplayName("removeDuplicatesUsingLoops: 일반 배열에서 중복 제거")
  @Test
  public void testRemoveDuplicatesUsingLoops() {

    // Given
    int[] array = {1, 2, 3, 4, 1, 2, 5};

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingLoops(array);

    // Then
    int[] expected = {1, 2, 3, 4, 5};
    assertArrayEquals(expected, result);
  }

  @DisplayName("removeDuplicatesUsingLoops: null 배열 처리")
  @Test
  public void testRemoveDuplicatesUsingLoops_NullArray() {

    // Given
    int[] nullArray = null;

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingLoops(nullArray);

    // Then
    assertNull(result);
  }

  @Order(6)
  @DisplayName("removeDuplicatesUsingLoops: 빈 배열 처리")
  @Test
  public void testRemoveDuplicatesUsingLoops_EmptyArray() {

    // Given
    int[] emptyArray = {};

    // When
    int[] result = arrayRemoveDuplicates.removeDuplicatesUsingLoops(emptyArray);

    // Then
    assertArrayEquals(new int[0], result);
  }
}
