package codility;

import java.util.Arrays;

public class lesson02_1 {

  public static void main(String[] args) {

    System.out.println("A=[3, 8, 9, 7, 6], K=3 : " + Arrays.toString(solution(new int[]{3, 8, 9, 7, 6}, 3)));
    System.out.println("A=[0, 0, 0], K=1 : " + Arrays.toString(solution(new int[]{0, 0, 0}, 1)));
    System.out.println("A=[1, 2, 3, 4], K=4 : " + Arrays.toString(solution(new int[]{1, 2, 3, 4}, 4)));
    System.out.println("A=[], K=1 : " + Arrays.toString(solution(new int[]{}, 1)));
  }

  // [Codility] Lesson2. CyclicRotation
  static int[] solution(int[] A, int K) {

    int[] result = A;

    int resultLen = result.length;
    if (resultLen != 0 && K != 0) {
      for (int a = 0; a < K; a++) {
        int firstEle = result[resultLen - 1];
        for (int b = resultLen - 1; b >= 1; b--) {
          result[b] = result[b - 1];
        }
        result[0] = firstEle;
      }
    }

    return result;
  }
}
