package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * Stream distinct() 사용법
 */
public class Example03 {

  public static void main(String[] args) {

    System.out.println("1.distinct() 사용법");
    createStreamDistinct();
  }

  /*
   * distinct()은 원본 스트림에서 중복을 제거하고 스트림을 생성하여 리턴
   */
  public static void createStreamDistinct() {
    List<String> strings = Arrays.asList("Python", "Java",  "C", "Python", "Java");
    Stream<String> stream1 = strings.stream();
    Stream<String> stream2 = stream1.distinct();
    stream2.forEach(System.out::println);
  }
}
