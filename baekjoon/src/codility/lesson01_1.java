package codility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class lesson01_1 {

  static int N;

  public static void main(String[] args) {

    try {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());

      int result = solution(N);

      System.out.println(result);

      br.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // [Codility] Lesson1. BinaryGap
  static int solution(int N) {

    int binaryGap = 0;
    String binaryStr = Integer.toBinaryString(N);
    int binaryLen = binaryStr.length();
    int zeroCnt = 0;

    for (int a = 0; a < binaryLen; a++) {
      if (binaryStr.charAt(a) == '1') {
        if (zeroCnt != 0) {
          binaryGap = Math.max(binaryGap, zeroCnt);
        }
        zeroCnt = 0;
        continue;
      }
      zeroCnt++;
    }

    return binaryGap;
  }
}
