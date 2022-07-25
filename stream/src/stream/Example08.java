package stream;

import java.util.Arrays;
import java.util.List;

/*
 * Stream anyMatch(), allMatch(), noneMatch() 사용법
 */
public class Example08 {

  public static void main(String[] args) {

    System.out.println("1.anyMatch() 사용법");
    createStreamAnyMacth();

    System.out.println("2.allMatch() 사용법");
    createStreamAllMatch();

    System.out.println("3.noneMatch() 사용법");
    createStreamNoneMatch();
  }

  /*
   * anyMatch()
   *  - 해당 스트림의 일부 요소가 특정 조건을 만족할 경우 true, 만족하지 않을 경우 false를 반환
   *  - 해당 스트림에서 일부 요소가 문자열 'A'로 시작하는지 확인
   */
  public static void createStreamAnyMacth() {
    List<String> strings = Arrays.asList("A1", "A2", "B1", "B2");
    boolean isMatch = strings.stream().anyMatch(s -> s.startsWith("A"));
    System.out.println("anyMatch : " + isMatch);
  }

  /*
   * allMatch()
   *  - 해당 스트림의 모든 요소가 특정 조건을 만족할 경우 true, 만족하지 않을 경우 false를 반환
   *  - 해당 스트림에서 모든 요소가 문자열 'A'로 시작하는지 확인
   */
  public static void createStreamAllMatch() {
    List<String> strings = Arrays.asList("A1", "A2", "A3", "A4", "A5");
    boolean isMatch = strings.stream().allMatch(s -> s.startsWith("A"));
    System.out.println("allMatch : " + isMatch);
  }

  /*
   * noneMatch()
   *  - 해당 스트림의 모든 요소가 특정 조건에 만족하지 않을 경우 true , 만족할 경우 false를 반환
   *  - 해당 스트림에서 모든 요소가 문자열 'A'로 시작하지 않는지 확인
   */
  public static void createStreamNoneMatch() {
    List<String> strings = Arrays.asList("B1", "B2", "B3", "B4", "B5");
    boolean isMatch = strings.stream().noneMatch(s -> s.startsWith("A"));
    System.out.println("noneMatch : " + isMatch);
  }
}
