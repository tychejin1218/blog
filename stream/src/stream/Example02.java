package stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Stream limit(), skip() 사용법
 */
public class Example02 {

  public static void main(String[] args) {

    System.out.println("1.limit() 사용법");
    createStreamLimit();

    System.out.println("2.limit() 사용법");
    createStreamRandom();

    System.out.println("3.skip() 사용법");
    createStreamSkip();
  }

  /**
   * limit(n)는 원본 스트림에서 처음부터 n개 요소만큼 스트림을 생성하여 리턴
   * limit() 메소드는 해당 스트림의 첫 번째 요소부터 전달된 갯수만큼의 요소만으로 이루어진 새로운 스트림을 반환합니다.
   */
  // 해당 스트림을 limit() 메소드를 이용하여 첫 번째 요소부터 다섯 번째 요소만큼 이루어진 스트림으로 반환
  public static void createStreamLimit() {
    Stream<String> stream = Arrays.stream(
        new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
    stream.limit(5).forEach(System.out::println);
  }

  /**
   * limit()는 무한한 스트림에서 필요한 부분의 스트림을 리턴받을 때 유용
   */
  // 무한한 스트림에서 첫 번째 요소부터 열 번째 요소만큼 이루어진 스트림으로 반환
  public static void createStreamRandom() {
    Stream<Double> randoms = Stream.generate(Math::random).limit(10);
    randoms.forEach(System.out::println);
  }

  /**
   * skip(n)은 원본 스트림에서 처음 n개 요소 이후 원본 스트림이 끝날 때까지 스트림을 생성하여 리턴
   * skip() 메소드는 해당 스트림의 첫 번째 요소부터 전달된 갯수만큼의 요소를 제외한 나머지 요소만으로 이루어진 새로운 스트림을 반환합니다.
   */
  // 해당 스트림을 skip() 메소드를 이용하여 첫 번째 요소부터 다섯 번째 요소를 제외한 나머지 요소만으로 이루어진 스트림으로 반환
  public static void createStreamSkip() {
    Stream<String> stream = Arrays.stream(
        new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
    stream.skip(5).forEach(System.out::println);
  }
}
