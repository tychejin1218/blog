package util.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArraySumTest {

  private final ArraySum arraySum = new ArraySum();

  @Order(1)
  @DisplayName("sumUsingForLoop: 배열 요소들의 합")
  @Test
  public void testSumUsingForLoop() {
    // Given
    int[] array = {1, 2, 3, 4, 5};

    // When
    int result = arraySum.sumUsingForLoop(array);

    // Then
    assertEquals(15, result);
  }

  @Order(2)
  @DisplayName("sumUsingForLoop: 빈 배열 테스트")
  @Test
  public void testSumUsingForLoop_EmptyArray() {
    // Given
    int[] array = {};

    // When
    int result = arraySum.sumUsingForLoop(array);

    // Then
    assertEquals(0, result);
  }

  @Order(3)
  @DisplayName("sumUsingEnhancedForLoop: 배열 요소들의 합")
  @Test
  public void testSumUsingEnhancedForLoop() {
    // Given
    int[] array = {1, 2, 3, 4, 5};

    // When
    int result = arraySum.sumUsingEnhancedForLoop(array);

    // Then
    assertEquals(15, result);
  }

  @Order(4)
  @DisplayName("sumUsingEnhancedForLoop: 빈 배열 테스트")
  @Test
  public void testSumUsingEnhancedForLoop_EmptyArray() {
    // Given
    int[] array = {};

    // When
    int result = arraySum.sumUsingEnhancedForLoop(array);

    // Then
    assertEquals(0, result);
  }

  @Order(5)
  @DisplayName("sumUsingStreams: 배열 요소들의 합")
  @Test
  public void testSumUsingStreams() {
    // Given
    int[] array = {1, 2, 3, 4, 5};

    // When
    int result = arraySum.sumUsingStreams(array);

    // Then
    assertEquals(15, result);
  }

  @Order(6)
  @DisplayName("sumUsingStreams: 빈 배열 테스트")
  @Test
  public void testSumUsingStreams_EmptyArray() {
    // Given
    int[] array = {};

    // When
    int result = arraySum.sumUsingStreams(array);

    // Then
    assertEquals(0, result);
  }

  @Order(7)
  @DisplayName("sumUsingRecursion: 배열 요소들의 합")
  @Test
  public void testSumUsingRecursion() {
    // Given
    int[] array = {1, 2, 3, 4, 5};

    // When
    int result = arraySum.sumUsingRecursion(array);

    // Then
    assertEquals(15, result);
  }

  @Order(8)
  @DisplayName("sumUsingRecursion: 빈 배열 테스트")
  @Test
  public void testSumUsingRecursion_EmptyArray() {
    // Given
    int[] array = {};

    // When
    int result = arraySum.sumUsingRecursion(array);

    // Then
    assertEquals(0, result);
  }

  @Order(9)
  @DisplayName("sumUsingParallelPrefix: 배열 요소들의 합")
  @Test
  public void testSumUsingParallelPrefix() {
    // Given
    int[] array = {1, 2, 3, 4, 5};

    // When
    int result = arraySum.sumUsingParallelPrefix(array);

    // Then
    assertEquals(15, result);
  }

  @Order(10)
  @DisplayName("sumUsingParallelPrefix: 빈 배열 테스트")
  @Test
  public void testSumUsingParallelPrefix_EmptyArray() {
    // Given
    int[] array = {};

    // When
    int result = arraySum.sumUsingParallelPrefix(array);

    // Then
    assertEquals(0, result);
  }
}
