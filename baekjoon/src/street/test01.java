package street;

public class test01 {

  public static void main(String[] agrs) {

    System.out.println("N=28 : " + solution(28));
    System.out.println("N=734 : " + solution(734));
    System.out.println("N=1990 : " + solution(1990));
    System.out.println("N=1000 : " + solution(1000));
  }

  static int solution(int N) {

    int result = 0;

    int givenNSum = getSumOfDigits(N);
    while (N < 50000) {
      N++;
      int compareNSum = getSumOfDigits(N);
      if (givenNSum == compareNSum) {
        result = N;
        break;
      }
    }

    return result;
  }

  static int getSumOfDigits(int n) {
    int sum = 0;
    String[] nums = String.valueOf(n).split("");
    for (String num : nums) {
      sum += Integer.parseInt(num);
    }
    return sum;
  }
}
