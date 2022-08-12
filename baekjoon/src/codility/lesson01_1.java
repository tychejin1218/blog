package codility;

public class lesson01_1 {

  public static void main(String[] args) {
    System.out.println("529 : " + solution(529));
    System.out.println("20 : " + solution(20));
    System.out.println("15 : " + solution(15));
    System.out.println("1041 : " + solution(1041));
    System.out.println("32 : " + solution(32));
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
