package util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArrayRotationTest {

  private final ArrayRotation arrayRotation = new ArrayRotation();

  @Order(1)
  @DisplayName("rotateWithLoop: 배열 회전")
  @Test
  void testRotateWithLoop() {

    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithLoop(nums, 3);

    // Then
    int[] expected = {5, 6, 7, 1, 2, 3, 4};
    assertArrayEquals(expected, result);
  }

  @Order(2)
  @DisplayName("rotateWithLoop: 빈 배열 테스트")
  @Test
  void testRotateWithLoopEmptyArray() {

    // Given
    int[] nums = {};

    // When
    int[] result = arrayRotation.rotateWithLoop(nums, 3);

    // Then
    int[] expected = {};
    assertArrayEquals(expected, result);
  }

  @Order(3)
  @DisplayName("rotateWithLoop: 단일 요소 배열 회전")
  @Test
  void testRotateWithLoopSingleElement() {

    // Given
    int[] nums = {1};

    // When
    int[] result = arrayRotation.rotateWithLoop(nums, 3);

    // Then
    int[] expected = {1};
    assertArrayEquals(expected, result);
  }

  @Order(4)
  @DisplayName("rotateWithLoop: 회전을 하지 않는 경우 테스트")
  @Test
  void testRotateWithLoopZeroTimes() {

    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithLoop(nums, 0);

    // Then
    int[] expected = {1, 2, 3, 4, 5, 6, 7};
    assertArrayEquals(expected, result);
  }

  @Order(5)
  @DisplayName("rotateWithLoop: 배열 길이보다 많은 회전 테스트")
  @Test
  void testRotateWithLoopMoreThanLength() {

    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithLoop(nums, 10);

    // Then
    int[] expected = {5, 6, 7, 1, 2, 3, 4};
    assertArrayEquals(expected, result);
  }

  @Order(6)
  @DisplayName("rotateWithStreams: 배열 회전")
  @Test
  void testRotateWithStreams() {

    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithStreams(nums, 3);

    // Then
    int[] expected = {5, 6, 7, 1, 2, 3, 4};
    assertArrayEquals(expected, result);
  }

  @Order(7)
  @DisplayName("rotateWithStreams: 빈 배열 테스트")
  @Test
  void testRotateWithStreamsEmptyArray() {

    // Given
    int[] nums = {};

    // When
    int[] result = arrayRotation.rotateWithStreams(nums, 3);

    // Then
    int[] expected = {};
    assertArrayEquals(expected, result);
  }

  @Order(8)
  @DisplayName("rotateWithStreams: 단일 요소 배열 회전")
  @Test
  void testRotateWithStreamsSingleElement() {

    // Given
    int[] nums = {1};

    // When
    int[] result = arrayRotation.rotateWithStreams(nums, 3);

    // Then
    int[] expected = {1};
    assertArrayEquals(expected, result);
  }

  @Order(9)
  @DisplayName("rotateWithStreams: 회전을 하지 않는 경우 테스트")
  @Test
  void testRotateWithStreamsZeroTimes() {
    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithStreams(nums, 0);

    // Then
    int[] expected = {1, 2, 3, 4, 5, 6, 7};
    assertArrayEquals(expected, result);
  }

  @Order(10)
  @DisplayName("rotateWithStreams: 배열 길이보다 많은 회전 테스트")
  @Test
  void testRotateWithStreamsMoreThanLength() {

    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithStreams(nums, 10);

    // Then
    int[] expected = {5, 6, 7, 1, 2, 3, 4};
    assertArrayEquals(expected, result);
  }

  @Order(11)
  @DisplayName("rotateWithHelperMethods: 배열 회전")
  @Test
  void testRotateWithHelperMethodsBasic() {

    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithHelperMethods(nums, 3);

    // Then
    int[] expected = {5, 6, 7, 1, 2, 3, 4};
    assertArrayEquals(expected, result);
  }

  @Order(12)
  @DisplayName("rotateWithHelperMethods: 빈 배열 테스트")
  @Test
  void testRotateWithHelperMethodsEmptyArray() {

    // Given
    int[] nums = {};

    // When
    int[] result = arrayRotation.rotateWithHelperMethods(nums, 3);

    // Then
    int[] expected = {};
    assertArrayEquals(expected, result);
  }

  @Order(13)
  @DisplayName("rotateWithHelperMethods: 단일 요소 배열 회전")
  @Test
  void testRotateWithHelperMethodsSingleElement() {

    // Given
    int[] nums = {1};

    // When
    int[] result = arrayRotation.rotateWithHelperMethods(nums.clone(), 3);

    // Then
    int[] expected = {1};
    assertArrayEquals(expected, result);
  }

  @Order(14)
  @DisplayName("rotateWithHelperMethods: 회전을 하지 않는 경우 테스트")
  @Test
  void testRotateWithHelperMethodsZeroTimes() {

    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithHelperMethods(nums.clone(), 0);

    // Then
    int[] expected = {1, 2, 3, 4, 5, 6, 7};
    assertArrayEquals(expected, result);
  }

  @Order(15)
  @DisplayName("rotateWithHelperMethods: 배열 길이보다 많은 회전 테스트")
  @Test
  void testRotateWithHelperMethodsMoreThanLength() {

    // Given
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    // When
    int[] result = arrayRotation.rotateWithHelperMethods(nums.clone(), 10);

    // Then
    int[] expected = {5, 6, 7, 1, 2, 3, 4};
    assertArrayEquals(expected, result);
  }
}
