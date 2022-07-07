package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * Stream limit(), skip() 사용법
 */
public class Example02 {

  public static void main(String[] args) {

    System.out.println("1.limit() 사용법");
    createStreamLimit();

    System.out.println("2.skip() 사용법");
    createStreamSkip();
  }

  /*
   * limit(n)는 원본 스트림에서 처음부터 n개 요소만큼 스트림을 생성하여 리턴
   */
  public static void createStreamLimit() {
    List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    Stream<String> stream1 = list.stream();
    Stream<String> stream2 = stream1.limit(5);
    stream2.forEach(System.out::println);
  }

  /*
   * limit()는 무한한 스트림에서 필요한 부분의 스트림을 리턴받을 때 유용
   */
  public static void createStreamRandom() {
    Stream<Double> randoms = Stream.generate(Math::random).limit(10);
    randoms.forEach(System.out::println);
  }

  /*
   * skip(n)은 원본 스트림에서 처음 n개 요소 이후 원본 스트림이 끝날 때까지 스트림을 생성하여 리턴
   */
  public static void createStreamSkip() {
    List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    Stream<String> stream1 = list.stream();
    Stream<String> stream2 = stream1.skip(5);
    stream2.forEach(System.out::println);
  }
}