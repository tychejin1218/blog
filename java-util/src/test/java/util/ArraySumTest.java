package util;

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
    int[] array = {1, 2, 3, 4, 5};
    int result = arraySum.sumUsingForLoop(array);
    assertEquals(15, result);
  }

  @Order(2)
  @DisplayName("sumUsingForLoop: 빈 배열 테스트")
  @Test
  public void testSumUsingForLoop_EmptyArray() {
    int[] array = {};
    int result = arraySum.sumUsingForLoop(array);
    assertEquals(0, result);
  }

  @Order(3)
  @DisplayName("sumUsingEnhancedForLoop: 배열 요소들의 합")
  @Test
  public void testSumUsingEnhancedForLoop() {
    int[] array = {1, 2, 3, 4, 5};
    int result = arraySum.sumUsingEnhancedForLoop(array);
    assertEquals(15, result);
  }

  @Order(4)
  @DisplayName("sumUsingEnhancedForLoop: 빈 배열 테스트")
  @Test
  public void testSumUsingEnhancedForLoop_EmptyArray() {
    int[] array = {};
    int result = arraySum.sumUsingEnhancedForLoop(array);
    assertEquals(0, result);
  }

  @Order(5)
  @DisplayName("sumUsingStreams: 배열 요소들의 합")
  @Test
  public void testSumUsingStreams() {
    int[] array = {1, 2, 3, 4, 5};
    int result = arraySum.sumUsingStreams(array);
    assertEquals(15, result);
  }

  @Order(6)
  @DisplayName("sumUsingStreams: 빈 배열 테스트")
  @Test
  public void testSumUsingStreams_EmptyArray() {
    int[] array = {};
    int result = arraySum.sumUsingStreams(array);
    assertEquals(0, result);
  }

  @Order(7)
  @DisplayName("sumUsingRecursion: 배열 요소들의 합")
  @Test
  public void testSumUsingRecursion() {
    int[] array = {1, 2, 3, 4, 5};
    int result = arraySum.sumUsingRecursion(array);
    assertEquals(15, result);
  }

  @Order(8)
  @DisplayName("sumUsingRecursion: 빈 배열 테스트")
  @Test
  public void testSumUsingRecursion_EmptyArray() {
    int[] array = {};
    int result = arraySum.sumUsingRecursion(array);
    assertEquals(0, result);
  }

  @Order(9)
  @DisplayName("sumUsingParallelPrefix: 배열 요소들의 합")
  @Test
  public void testSumUsingParallelPrefix() {
    int[] array = {1, 2, 3, 4, 5};
    int result = arraySum.sumUsingParallelPrefix(array);
    assertEquals(15, result);
  }

  @Order(10)
  @DisplayName("sumUsingParallelPrefix: 빈 배열 테스트")
  @Test
  public void testSumUsingParallelPrefix_EmptyArray() {
    int[] array = {};
    int result = arraySum.sumUsingParallelPrefix(array);
    assertEquals(0, result);
  }
}
