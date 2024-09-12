package util;

import java.util.stream.IntStream;

public class ArrayRotation {

  /**
   * 반복문을 사용하여 배열을 회전
   *
   * @param nums 회전할 배열
   * @param k    회전할 위치 수
   * @return 회전된 배열
   */
  public int[] rotateWithLoop(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) {
      return nums;
    }
    k = k % n;
    int[] rotated = new int[n];

    for (int i = 0; i < n; i++) {
      rotated[(i + k) % n] = nums[i];
    }

    return rotated;
  }

  /**
   * 스트림을 사용하여 배열을 회전
   *
   * @param nums 회전할 배열
   * @param k    회전할 위치 수
   * @return 회전된 배열
   */
  public int[] rotateWithStreams(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) {
      return nums;
    }
    k = k % n;
    final int finalK = k;

    return IntStream.range(0, n)
        .map(i -> nums[(i + (n - finalK)) % n])
        .toArray();
  }

  /**
   * 헬퍼 메서드를 사용하여 배열을 회전시킵니다. 배열을 역순으로 뒤집는 방법을 사용
   *
   * @param nums 회전할 배열
   * @param k    회전할 위치 수
   * @return 회전된 배열
   */
  public int[] rotateWithHelperMethods(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) {
      return nums;
    }
    k = k % n;

    int[] result = nums.clone();
    reverse(result, 0, n - 1);
    reverse(result, 0, k - 1);
    reverse(result, k, n - 1);

    return result;
  }

  /**
   * 배열의 일부를 뒤집는 헬퍼 메서드
   *
   * @param nums  회전할 배열
   * @param start 뒤집을 시작 인덱스
   * @param end   뒤집을 끝 인덱스
   */
  private void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }
}
