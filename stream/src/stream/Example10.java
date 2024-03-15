package stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Stream map(), flatMap() 사용법
 */
public class Example10 {

  public static void main(String[] args) {

    System.out.println("1.map() 사용법");
    createStreamMap();

    System.out.println("2.flatMap() 사용법");
    createStreamflatMap();
  }

  /**
   * map() 메소드는 해당 스트림의 요소들을 주어진 함수에 인수로 전달하여, 그 반환값들로 이루어진 새로운 스트림을 반환합니다.
   */
  // 해당 스트림을 map() 메서드를 이용하여 각 요소의 길이로 이루어진 스트림으로 반환
  public static void createStreamMap() {
    Stream<String> stream = Stream.of("Python", "Java", "PHP", "JavaScript", "Kotlin");
    stream.map(s -> s.length()).forEach(System.out::println);
  }

  /**
   * flatMap() 메소드 해당 스트림의 요소가 배열일 때 각 배열의 각 요소의 반환값을 하나로 합친 새로운 스트림을 반환합니다.
   */
  // 해당 스트림을 flatMap() 메서드를 이용하여 1차원 배열의 요소로 이루어진 스트림으로 반환
  public static void createStreamflatMap() {
    Stream<String[]> stream = Stream.of(
        new String[]{"Python", "Java", "C"},
        new String[]{"PHP", "JavaScript", "Kotlin"}
    );
    stream.flatMap(Arrays::stream).forEach(System.out::println);
  }
}

