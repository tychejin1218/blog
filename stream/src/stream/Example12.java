package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Stream foreach() 사용법
 */
public class Example12 {

  public static void main(String args[]) {

    System.out.println("1.foreach 사용법");
    createStreamFor();

    System.out.println("2.중첩 fora문");
    createStreamNestedFor();
  }

  /*
   * forEach() 메소드는 반환 타입이 void이므로 보통 스트림의 모든 요소를 출력하는 용도로 많이 사용합니다.
   *
   * 예제) 리스트에 전체 요소를 출력
   */
  public static void createStreamFor() {
    Stream<String> stream = Stream.of("Python", "C", "Java", "Visual Basic", "PHP");
    stream.forEach(System.out::println);
  }

  /*
   * 예제) 2개의 리스트에서 중복된 요소만 출력
   */
  public static void createStreamNestedFor() {
    List<String> languages01 = Arrays.asList("Python", "C", "Java", "Visual Basic", "PHP");
    List<String> languages02 = Arrays.asList("C", "JAVA", "JavaScript", "PHP", "SQL");

    System.out.println("1. 일반적인 중첩 for문");
    for (String language01 : languages01) {
      for (String language02 : languages02) {
        if (language02.equals(language01)) {
          System.out.println(language01);
        }
      }
    }

    System.out.println("2. stream을 이용한 중첩 for문");
    languages01.stream()
        .filter(language -> languages02.stream().anyMatch(Predicate.isEqual(language)))
        .collect(Collectors.toList())
        .forEach(System.out::println);
  }
}
