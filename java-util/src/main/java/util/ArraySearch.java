package util;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArraySearch {

  /**
   * 순차 검색을 사용하여 배열에서 특정 값을 찾음
   *
   * @param array 검색할 배열
   * @param value 검색할 값
   * @return 배열에서 값의 인덱스 (존재하지 않으면 -1)
   */
  public int linearSearch(int[] array, int value) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == value) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 이진 검색을 사용하여 정렬된 배열에서 특정 값을 찾음
   *
   * @param array 정렬된 배열
   * @param value 검색할 값
   * @return 배열에서 값의 인덱스 (존재하지 않으면 -1)
   */
  public int binarySearch(int[] array, int value) {

    int left = 0;
    int right = array.length - 1;

    while (left <= right) {
      int middle = left + (right - left) / 2;

      if (array[middle] == value) {
        return middle;
      }

      if (array[middle] < value) {
        left = middle + 1;
      } else {
        right = middle - 1;
      }
    }

    return -1;
  }

  /**
   * Arrays.binarySearch 메서드를 사용하여 정렬된 배열에서 특정 값을 찾음
   *
   * @param array 정렬된 배열
   * @param value 검색할 값
   * @return 배열에서 값의 인덱스 (존재하지 않으면 -1)
   */
  public int binarySearchUsingLibrary(int[] array, int value) {
    int index = Arrays.binarySearch(array, value);
    return index >= 0 ? index : -1;
  }

  /**
   * 2차원 배열에서 특정 값이 존재하는지 확인
   *
   * @param array 검색할 2차원 배열
   * @param value 검색할 값
   * @return 값이 존재하면 true, 그렇지 않으면 false
   */
  public boolean containsIn2DArray(int[][] array, int value) {
    for (int[] subArray : array) {
      if (Arrays.stream(subArray).anyMatch(i -> i == value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 배열에서 최대값을 찾음
   *
   * @param array 검색할 배열
   * @return 배열에서 최대값
   * @throws NoSuchElementException 배열이 비어있는 경우
   */
  public int findMaxUsingForLoop(int[] array) {
    if (array == null || array.length == 0) {
      throw new NoSuchElementException("Array is empty");
    }

    int max = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }

  /**
   * 배열에서 최대값을 찾음
   *
   * @param array 검색할 배열
   * @return 배열에서 최대값
   * @throws NoSuchElementException 배열이 비어있는 경우
   */
  public int findMax(int[] array) {
    return Arrays.stream(array)
        .max()
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * 배열에서 최소값을 찾음
   *
   * @param array 검색할 배열
   * @return 배열에서 최소값
   * @throws NoSuchElementException 배열이 비어있는 경우
   */
  public int findMinUsingForLoop(int[] array) {
    if (array == null || array.length == 0) {
      throw new NoSuchElementException("Array is empty");
    }

    int min = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }
    return min;
  }

  /**
   * 배열에서 최소값을 찾음
   *
   * @param array 검색할 배열
   * @return 배열에서 최소값
   * @throws NoSuchElementException 배열이 비어있는 경우
   */
  public int findMin(int[] array) {
    return Arrays.stream(array)
        .min()
        .orElseThrow(NoSuchElementException::new);
  }
}
