package street;

import java.sql.SQLOutput;

public class test02 {

  public static void main(String[] agrs) {

    System.out.println("A=[1, 1, 2, 3, 3], K=3 : " + solution(new int[]{1, 1, 2, 3, 3}, 3));
    System.out.println("A=[1, 1, 3], K=2 : " + solution(new int[]{1, 1, 3}, 2));
    System.out.println("A=[1, 1, 2, 3, 3, 4], K=4 : " + solution(new int[]{1, 1, 2, 3, 3, 4}, 4));
    System.out.println("A=[1, 1, 3], K=3 : " + solution(new int[]{1, 1, 3}, 3));
  }

  public static boolean solution(int[] A, int K) {
    int n = A.length;
    for (int i = 0; i < n - 1; i++) {
      if ((A[i] != A[i + 1]) && (A[i] + 1 != A[i + 1])) {
        return false;
      }
    }
    if (A[0] != 1 || A[n - 1] != K) {
      return false;
    } else {
      return true;
    }
  }
}

