package util.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 정렬되지 않은 배열에서 중복된 요소 제거
 */
public class ArrayRemoveDuplicates {

  /**
   * 1. HashSet을 사용한 중복 제거
   *
   * @param array 중복된 요소를 제거할 정수형 배열
   * @return 중복이 제거된 새로운 배열, 입력 배열이 null인 경우 null 반환
   */
  public int[] removeDuplicatesUsingSet(int[] array) {

    if (array == null) {
      return null;
    }

    Set<Integer> set = new HashSet<>();
    for (int value : array) {
      set.add(value);
    }

    int[] result = new int[set.size()];
    int i = 0;
    for (int value : set) {
      result[i++] = value;
    }

    return result;
  }

  /**
   * 2. Stream API를 사용한 중복 제거.
   *
   * @param array 중복된 요소를 제거할 정수형 배열
   * @return 중복이 제거된 새로운 배열, 입력 배열이 null인 경우 null 반환
   */
  public int[] removeDuplicatesUsingStream(int[] array) {

    if (array == null) {
      return null;
    }

    return Arrays.stream(array)
        .distinct()
        .toArray();
  }

  /**
   * 중첩 루프를 사용한 중복 제거 (비효율적).
   *
   * @param array 중복된 요소를 제거할 정수형 배열
   * @return 중복이 제거된 새로운 배열, 입력 배열이 null인 경우 null 반환
   */
  public int[] removeDuplicatesUsingLoops(int[] array) {

    if (array == null) {
      return null;
    }

    int n = array.length;
    int[] temp = new int[n];
    int j = 0;

    for (int i = 0; i < n; i++) {
      boolean isDuplicate = false;
      for (int k = 0; k < j; k++) {
        if (array[i] == temp[k]) {
          isDuplicate = true;
          break;
        }
      }
      if (!isDuplicate) {
        temp[j++] = array[i];
      }
    }

    int[] result = new int[j];
    System.arraycopy(temp, 0, result, 0, j);

    return result;
  }
}
