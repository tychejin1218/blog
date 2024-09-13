package util;

import java.util.Arrays;
import java.util.Comparator;

public class ArraySort {

  /**
   * Arrays.sort 메소드를 사용하여 배열을 오름차순으로 정렬
   *
   * @param arr 정렬할 배열
   * @return 정렬된 배열
   */
  public int[] arraySortAscending(int[] arr) {
    Arrays.sort(arr);
    return arr;
  }

  /**
   * Arrays.sort 메소드를 사용하여 배열을 내림차순으로 정렬
   *
   * @param arr 정렬할 배열
   * @return 정렬된 배열
   */
  public int[] arraySortDescending(int[] arr) {

    // 원시형 배열을 래퍼 클래스 배열로 변환
    Integer[] boxedArr = Arrays.stream(arr)
        .boxed()
        .toArray(Integer[]::new);

    // 내림차순 정렬
    Arrays.sort(boxedArr, Comparator.reverseOrder());

    // 래퍼 클래스 배열을 원시형 배열로 변환
    return Arrays.stream(boxedArr)
        .mapToInt(Integer::intValue)
        .toArray();
  }

  /**
   * Arrays.parallelSort 메소드를 사용하여 배열을 병렬로 오름차순 정렬
   *
   * @param arr 정렬할 배열
   * @return 정렬된 배열
   */
  public int[] parallelSortAscending(int[] arr) {
    Arrays.parallelSort(arr);
    return arr;
  }

  /**
   * Arrays.parallelSort 메소드를 사용하여 배열을 병렬로 내림차순 정렬
   *
   * @param arr 정렬할 배열
   * @return 정렬된 배열
   */
  public int[] parallelSortDescending(int[] arr) {

    // 원시형 배열을 래퍼 클래스 배열로 변환
    Integer[] boxedArr = Arrays.stream(arr)
        .boxed()
        .toArray(Integer[]::new);

    // 내림차순 정렬
    Arrays.parallelSort(boxedArr, Comparator.reverseOrder());

    // 래퍼 클래스 배열을 원시형 배열로 변환
    return Arrays.stream(boxedArr)
        .mapToInt(Integer::intValue)
        .toArray();
  }

  /**
   * Arrays.sort 메소드를 사용하여 문자열 배열을 오름차순으로 정렬
   *
   * @param arr 정렬할 배열
   * @return 정렬된 배열
   */
  public String[] arraySortAscending(String[] arr) {
    Arrays.sort(arr);
    return arr;
  }

  /**
   * Arrays.sort 메소드를 사용하여 문자열 배열을 내림차순으로 정렬
   *
   * @param arr 정렬할 배열
   * @return 정렬된 배열
   */
  public String[] arraySortDescending(String[] arr) {
    Arrays.sort(arr, Comparator.reverseOrder());
    return arr;
  }

  /**
   * Arrays.parallelSort 메소드를 사용하여 문자열 배열을 병렬로 오름차순 정렬
   *
   * @param arr 정렬할 배열
   * @return 정렬된 배열
   */
  public String[] parallelSortAscending(String[] arr) {
    Arrays.parallelSort(arr);
    return arr;
  }

  /**
   * Arrays.parallelSort 메소드를 사용하여 문자열 배열을 병렬로 내림차순 정렬
   *
   * @param arr 정렬할 배열
   * @return 정렬된 배열
   */
  public String[] parallelSortDescending(String[] arr) {
    Arrays.parallelSort(arr, Comparator.reverseOrder());
    return arr;
  }
}
