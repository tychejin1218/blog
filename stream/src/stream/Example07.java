package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
 * Stream findFirst(), findAny() 사용법
 */
public class Example07 {

  public static void main(String[] args) {

    System.out.println("1.findFirst() 사용법 ");
    createStreamFindFirst();

    System.out.println("2.findAny() 사용법 ");
    createStreamFindAny();

    System.out.println("3.findFirst() 사용법 : 병렬 처리");
    createStreamParallelFindFirst();

    System.out.println("4.findAny() 사용법 : 병렬 처리");
    createStreamParallelFindAny();
  }

  /*
   * findFirst()는 스트림에서 첫번째 요소를 찾아서 Optional 타입으로 리턴
   */
  public static void createStreamFindFirst() {
    List<String> strings = Arrays.asList("A1", "A2", "A3", "A4", "A5");
    Optional<String> stringFirst = strings.stream()
        .filter(s -> s.startsWith("A"))
        .findFirst();
    stringFirst.ifPresent(System.out::println);
  }

  /*
   * findAny()는 스트림에서 첫번째 요소를 찾아서 Optional 타입으로 리턴
   *  - findFirst()와 findAny()의 차이는 병렬 처리 시 발생
   */
  public static void createStreamFindAny() {
    List<String> strings = Arrays.asList("A1", "A2", "A3", "A4", "A5");
    Optional<String> stringAny = strings.stream()
        .filter(s -> s.startsWith("A"))
        .findAny();
    stringAny.ifPresent(System.out::println);
  }

  /*
   * FindFirst()는 병렬 처리 시에도 첫번재 요소를 찾아서 Optional 타입으로 리턴
   */
  public static void createStreamParallelFindFirst() {
    List<String> strings = Arrays.asList("A1", "A2", "A3", "A4", "A5");
    Optional<String> stringAny = strings.stream()
        .parallel()
        .filter(s -> s.startsWith("A"))
        .findFirst();
    stringAny.ifPresent(System.out::println);
  }

  /*
   * FindAny()는 병렬 처리 시 가장 먼저 찾은 요소를 찾아서 Optional 타입으로 리턴
   */
  public static void createStreamParallelFindAny() {
    List<String> strings = Arrays.asList("A1", "A2", "A3", "A4", "A5");
    Optional<String> stringAny = strings.stream()
        .parallel()
        .filter(s -> s.startsWith("A"))
        .findAny();
    stringAny.ifPresent(System.out::println);
  }
}