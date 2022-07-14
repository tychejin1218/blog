package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * Stream distinct() 사용법
 */
public class Example03 {

  public static void main(String[] args) {

    System.out.println("1.filter() 사용법");
    createStreamFilter();

    System.out.println("2.distinct() 사용법");
    createStreamDistinct();
  }

  /*
   * filter()는 해당 스트림에서 주어진 조건(predicate)에 맞는 요소만으로 구성된 새로운 스트림을 반환
   */
  public static void createStreamFilter() {
    Stream<String> stream1 = Stream.of("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Stream<String> stream2 = stream1.filter(s -> "Java".equals(s));
    stream2.forEach(System.out::println);
  }

  /*
   * distinct()은 해당 스트림에서 중복을 제거하고 새로운 스트림을 반환
   */
  public static void createStreamDistinct() {
    List<String> strings = Arrays.asList("Python", "Java", "C", "Python", "Java");
    Stream<String> stream1 = strings.stream();
    Stream<String> stream2 = stream1.distinct();
    stream2.forEach(System.out::println);
  }
}
