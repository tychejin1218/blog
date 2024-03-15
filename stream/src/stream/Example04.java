package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream concat() 사용법
 */
public class Example04 {

  public static void main(String[] args) {

    System.out.println("1.concat() 사용법");
    createStreamConcat();
  }

  /**
   * concat(a, b)은 첫 번째 스트림과 두 번째 스트림을 연결하여 스트림을 생성하여 리턴
   */
  public static void createStreamConcat() {
    List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
    List<String> chars = Arrays.asList("a", "b", "c", "d", "e");
    Stream<String> stream1 = numbers.stream();
    Stream<String> stream2 = chars.stream();
    Stream<String> stream3 = Stream.concat(stream1, stream2);
    stream3.forEach(System.out::println);
  }
}
