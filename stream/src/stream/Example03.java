package stream;

import java.util.stream.Stream;

/**
 * Stream distinct() 사용법
 */
public class Example03 {

  public static void main(String[] args) {

    System.out.println("1.filter() 사용법");
    createStreamFilter();

    System.out.println("2.distinct() 사용법");
    createStreamDistinct();
  }

  /**
   * filter() 메소드는 해당 스트림에서 주어진 조건(predicate)에 맞는 요소만으로 구성된 새로운 스트림을 반환합니다.
   */
  // 해당 스트림을 filter() 메서드를 이용하여 요소의 값이 'Java'로 이루어진 스트림으로 반환
  public static void createStreamFilter() {
    Stream<String> stream = Stream.of("Python", "Java", "PHP", "JavaScript", "Kotlin");
    stream.filter(s -> "Java".equals(s)).forEach(System.out::println);
  }

  /**
   * distinct() 메소드는 해당 스트림에서 중복된 요소가 제가된 새로운 스트림을 반환합니다.
   * distinct() 메소드는 내부적으로 Object 클래스의 equals() 메소드를 사용하여 요소의 중복을 비교합니다.
   */
  // 해당 스트림을 distinct() 메서드를 이용하여 중복된 요소를 제거한 스트림으로 반환
  public static void createStreamDistinct() {
    Stream<String> stream = Stream.of("Python", "Java", "C", "Python", "Java", "Python", "C");
    stream.distinct().forEach(System.out::println);
  }
}
