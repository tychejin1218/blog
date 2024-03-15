package stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream foreach() 사용법
 */
public class Example12 {

  public static void main(String args[]) {

    System.out.println("1.foreach 사용법");
    createStreamFor();

    System.out.println("2.일반적인 중첩 for문");
    createStreamNestedFor01();

    System.out.println("3.stream을 이용한 중첩 for문");
    createStreamNestedFor02();
  }

  /**
   * forEach() 메소드는 반환 타입이 void이므로 보통 스트림의 모든 요소를 출력하는 용도로 많이 사용합니다.
   *
   * 예제) 리스트에 전체 요소를 출력
   */
  public static void createStreamFor() {
    Stream<String> stream = Stream.of("Python", "C", "Java", "Visual Basic", "PHP");
    stream.forEach(System.out::println);
  }

  /**
   * 예제) 2개의 리스트에서 중복된 요소만 출력 - 일반적인 중첩 for문
   */
  public static void createStreamNestedFor01() {
    List<String> languages01 = Arrays.asList("Python", "C", "Java", "Visual Basic", "PHP");
    List<String> languages02 = Arrays.asList("C", "JAVA", "JavaScript", "PHP", "SQL");

    for (String language01 : languages01) {
      for (String language02 : languages02) {
        if (language02.equals(language01)) {
          System.out.println(language01);
        }
      }
    }
  }

  /**
   * 예제) 2개의 리스트에서 중복된 요소만 출력 - stream을 이용한 중첩 for문
   */
  public static void createStreamNestedFor02() {
    List<String> languages01 = Arrays.asList("Python", "C", "Java", "Visual Basic", "PHP");
    List<String> languages02 = Arrays.asList("C", "JAVA", "JavaScript", "PHP", "SQL");

    Set<String> languages02Set = new HashSet<>(languages02);
    languages01.stream()
        .filter(languages02Set::contains)
        .forEach(System.out::println);
  }
}
