package util.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ListRotationTest {

  private final ListRotation listRotation = new ListRotation();

  @Order(1)
  @DisplayName("rotateWithCollections: 리스트 회전")
  @Test
  void testRotateWithCollections() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithCollections(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = List.of(5, 6, 7, 1, 2, 3, 4);
    assertEquals(expected, result);
  }

  @Order(2)
  @DisplayName("rotateWithCollections: 빈 리스트 회전")
  @Test
  void testRotateWithCollectionsEmptyList() {

    // Given
    List<Integer> list = new ArrayList<>();

    // When
    List<Integer> result = listRotation.rotateWithCollections(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = new ArrayList<>();
    assertEquals(expected, result);
  }

  @Order(3)
  @DisplayName("rotateWithCollections: 단일 요소 리스트 회전")
  @Test
  void testRotateWithCollectionsSingleElement() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1));

    // When
    List<Integer> result = listRotation.rotateWithCollections(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = List.of(1);
    assertEquals(expected, result);
  }

  @Order(4)
  @DisplayName("rotateWithCollections: 회전을 하지 않는 경우")
  @Test
  void testRotateWithCollectionsZeroTimes() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithCollections(new ArrayList<>(list), 0);

    // Then
    List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
    assertEquals(expected, result);
  }

  @Order(5)
  @DisplayName("rotateWithCollections: 리스트 길이보다 많은 회전")
  @Test
  void testRotateWithCollectionsMoreThanLength() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithCollections(new ArrayList<>(list), 10);

    // Then
    List<Integer> expected = List.of(5, 6, 7, 1, 2, 3, 4);
    assertEquals(expected, result);
  }

  @Order(6)
  @DisplayName("rotateWithLoop: 리스트 회전")
  @Test
  void testRotateWithLoop() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithLoop(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = List.of(5, 6, 7, 1, 2, 3, 4);
    assertEquals(expected, result);
  }

  @Order(7)
  @DisplayName("rotateWithLoop: 빈 리스트 회전")
  @Test
  void testRotateWithLoopEmptyList() {

    // Given
    List<Integer> list = new ArrayList<>();

    // When
    List<Integer> result = listRotation.rotateWithLoop(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = new ArrayList<>();
    assertEquals(expected, result);
  }

  @Order(8)
  @DisplayName("rotateWithLoop: 단일 요소 리스트 회전")
  @Test
  void testRotateWithLoopSingleElement() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1));

    // When
    List<Integer> result = listRotation.rotateWithLoop(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = List.of(1);
    assertEquals(expected, result);
  }

  @Order(9)
  @DisplayName("rotateWithLoop: 회전을 하지 않는 경우")
  @Test
  void testRotateWithLoopZeroTimes() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithLoop(new ArrayList<>(list), 0);

    // Then
    List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
    assertEquals(expected, result);
  }

  @Order(10)
  @DisplayName("rotateWithLoop: 리스트 길이보다 많은 회전")
  @Test
  void testRotateWithLoopMoreThanLength() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithLoop(new ArrayList<>(list), 10);

    // Then
    List<Integer> expected = List.of(5, 6, 7, 1, 2, 3, 4);
    assertEquals(expected, result);
  }

  @Order(11)
  @DisplayName("rotateWithStreams: 리스트 회전")
  @Test
  void testRotateWithStreams() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithStreams(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = List.of(5, 6, 7, 1, 2, 3, 4);
    assertEquals(expected, result);
  }

  @Order(12)
  @DisplayName("rotateWithStreams: 빈 리스트 회전")
  @Test
  void testRotateWithStreamsEmptyList() {

    // Given
    List<Integer> list = new ArrayList<>();

    // When
    List<Integer> result = listRotation.rotateWithStreams(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = new ArrayList<>();
    assertEquals(expected, result);
  }

  @Order(13)
  @DisplayName("rotateWithStreams: 단일 요소 리스트 회전")
  @Test
  void testRotateWithStreamsSingleElement() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1));

    // When
    List<Integer> result = listRotation.rotateWithStreams(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = List.of(1);
    assertEquals(expected, result);
  }

  @Order(14)
  @DisplayName("rotateWithStreams: 회전을 하지 않는 경우")
  @Test
  void testRotateWithStreamsZeroTimes() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithStreams(new ArrayList<>(list), 0);

    // Then
    List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
    assertEquals(expected, result);
  }

  @Order(15)
  @DisplayName("rotateWithStreams: 리스트 길이보다 많은 회전")
  @Test
  void testRotateWithStreamsMoreThanLength() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithStreams(new ArrayList<>(list), 10);

    // Then
    List<Integer> expected = List.of(5, 6, 7, 1, 2, 3, 4);
    assertEquals(expected, result);
  }

  @Order(16)
  @DisplayName("rotateWithHelperMethods: 리스트 회전")
  @Test
  void testRotateWithHelperMethods() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithHelperMethods(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = List.of(5, 6, 7, 1, 2, 3, 4);
    assertEquals(expected, result);
  }

  @Order(17)
  @DisplayName("rotateWithHelperMethods: 빈 리스트 회전")
  @Test
  void testRotateWithHelperMethodsEmptyList() {

    // Given
    List<Integer> list = new ArrayList<>();

    // When
    List<Integer> result = listRotation.rotateWithHelperMethods(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = new ArrayList<>();
    assertEquals(expected, result);
  }

  @Order(18)
  @DisplayName("rotateWithHelperMethods: 단일 요소 리스트 회전")
  @Test
  void testRotateWithHelperMethodsSingleElement() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1));

    // When
    List<Integer> result = listRotation.rotateWithHelperMethods(new ArrayList<>(list), 3);

    // Then
    List<Integer> expected = List.of(1);
    assertEquals(expected, result);
  }

  @Order(19)
  @DisplayName("rotateWithHelperMethods: 회전을 하지 않는 경우")
  @Test
  void testRotateWithHelperMethodsZeroTimes() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithHelperMethods(new ArrayList<>(list), 0);

    // Then
    List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
    assertEquals(expected, result);
  }

  @Order(20)
  @DisplayName("rotateWithHelperMethods: 리스트 길이보다 많은 회전")
  @Test
  void testRotateWithHelperMethodsMoreThanLength() {

    // Given
    List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    // When
    List<Integer> result = listRotation.rotateWithHelperMethods(new ArrayList<>(list), 10);

    // Then
    List<Integer> expected = List.of(5, 6, 7, 1, 2, 3, 4);
    assertEquals(expected, result);
  }
}
