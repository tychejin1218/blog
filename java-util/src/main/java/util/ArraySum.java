package util;

import java.util.Arrays;

public class ArraySum {

  /**
   * 배열의 합을 전통적인 for 루프를 사용
   *
   * @param array 합을 구할 배열
   * @return 배열 요소들의 합
   */
  public int sumUsingForLoop(int[] array) {
    int sum = 0;
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
    }
    return sum;
  }

  /**
   * 배열의 합을 향상된 for 루프를 사용
   *
   * @param array 합을 구할 배열
   * @return 배열 요소들의 합
   */
  public int sumUsingEnhancedForLoop(int[] array) {
    int sum = 0;
    for (int num : array) {
      sum += num;
    }
    return sum;
  }

  /**
   * 배열의 합을 스트림 API를 사용
   *
   * @param array 합을 구할 배열
   * @return 배열 요소들의 합
   */
  public int sumUsingStreams(int[] array) {
    return Arrays.stream(array).sum();
  }

  /**
   * 배열의 합을 재귀 호출을 사용
   *
   * @param array 합을 구할 배열
   * @return 배열 요소들의 합
   */
  public int sumUsingRecursion(int[] array) {
    if (array.length == 0) {
      return 0;
    }
    return sumArrayRecursive(array, array.length - 1);
  }

  private int sumArrayRecursive(int[] array, int index) {
    if (index == 0) {
      return array[index];
    }
    return array[index] + sumArrayRecursive(array, index - 1);
  }

  /**
   * 배열의 합을 Arrays.parallelPrefix 메서드를 사용
   *
   * @param array 합을 구할 배열
   * @return 배열 요소들의 합
   */
  public int sumUsingParallelPrefix(int[] array) {
    if (array.length == 0) {
      return 0;
    }
    int[] copy = Arrays.copyOf(array, array.length);
    Arrays.parallelPrefix(copy, Integer::sum);
    return copy[copy.length - 1];
  }
}
