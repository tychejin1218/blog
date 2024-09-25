package util.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ListSumTest {

  private final ListSum listSum = new ListSum();

  @Order(1)
  @DisplayName("sumUsingForLoop: 리스트 요소들의 합")
  @Test
  public void testSumUsingForLoop() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    // When
    int result = listSum.sumUsingForLoop(list);

    // Then
    assertEquals(15, result);
  }

  @Order(2)
  @DisplayName("sumUsingForLoop: 빈 리스트인 경우")
  @Test
  public void testSumUsingForLoop_EmptyList() {

    // Given
    List<Integer> list = Collections.emptyList();

    // When
    int result = listSum.sumUsingForLoop(list);

    // Then
    assertEquals(0, result);
  }

  @Order(3)
  @DisplayName("sumUsingEnhancedForLoop: 리스트 요소들의 합")
  @Test
  public void testSumUsingEnhancedForLoop() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    // When
    int result = listSum.sumUsingEnhancedForLoop(list);

    // Then
    assertEquals(15, result);
  }

  @Order(4)
  @DisplayName("sumUsingEnhancedForLoop: 빈 리스트인 경우")
  @Test
  public void testSumUsingEnhancedForLoop_EmptyList() {

    // Given
    List<Integer> list = Collections.emptyList();

    // When
    int result = listSum.sumUsingEnhancedForLoop(list);

    // Then
    assertEquals(0, result);
  }

  @Order(5)
  @DisplayName("sumUsingStreams: 리스트 요소들의 합")
  @Test
  public void testSumUsingStreams() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    // When
    int result = listSum.sumUsingStreams(list);

    // Then
    assertEquals(15, result);
  }

  @Order(6)
  @DisplayName("sumUsingStreams: 빈 리스트인 경우")
  @Test
  public void testSumUsingStreams_EmptyList() {

    // Given
    List<Integer> list = Collections.emptyList();

    // When
    int result = listSum.sumUsingStreams(list);

    // Then
    assertEquals(0, result);
  }

  @Order(7)
  @DisplayName("sumUsingStreamReduce: 리스트 요소들의 합")
  @Test
  public void testSumUsingStreamReduce() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    // When
    int result = listSum.sumUsingStreamReduce(list);

    // Then
    assertEquals(15, result);
  }

  @Order(8)
  @DisplayName("sumUsingStreamReduce: 빈 리스트인 경우")
  @Test
  public void testSumUsingStreamReduce_EmptyList() {

    // Given
    List<Integer> list = Collections.emptyList();

    // When
    int result = listSum.sumUsingStreamReduce(list);

    // Then
    assertEquals(0, result);
  }

  @Order(9)
  @DisplayName("sumUsingRecursion: 리스트 요소들의 합")
  @Test
  public void testSumUsingRecursion() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    // When
    int result = listSum.sumUsingRecursion(list);

    // Then
    assertEquals(15, result);
  }

  @Order(10)
  @DisplayName("sumUsingRecursion: 빈 리스트인 경우")
  @Test
  public void testSumUsingRecursion_EmptyList() {

    // Given
    List<Integer> list = Collections.emptyList();

    // When
    int result = listSum.sumUsingRecursion(list);

    // Then
    assertEquals(0, result);
  }

  @Order(11)
  @DisplayName("sumUsingParallelStream: 리스트 요소들의 합")
  @Test
  public void testSumUsingParallelStream() {

    // Given
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    // When
    int result = listSum.sumUsingParallelStream(list);

    // Then
    assertEquals(15, result);
  }

  @Order(12)
  @DisplayName("sumUsingParallelStream: 빈 리스트인 경우")
  @Test
  public void testSumUsingParallelStream_EmptyList() {

    // Given
    List<Integer> list = Collections.emptyList();

    // When
    int result = listSum.sumUsingParallelStream(list);

    // Then
    assertEquals(0, result);
  }
}
