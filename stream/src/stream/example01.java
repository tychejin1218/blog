package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 생성 방법 및 예제
 */
public class example01 {

  public static void main(String args[]) {

    System.out.println("1.Stream 생성 방법 : Stream.of()");
    createStreamOf();

    System.out.println("2.Stream 생성 방법 : Stream.empty()");
    createStreamEmpty();

    System.out.println("3.Stream 생성 방법 : Stream.generate()");
    createStreamGenerate();

    System.out.println("4.Stream 생성 방법 : Stream.iterate()");
    createStreamIterate();

    System.out.println("5.Stream 생성 방법 : List -> Stream");
    createListToStream();

    System.out.println("6.Stream 생성 방법 : Array -> Stream");
    createArrayToStream();
  }

  /**
   * Stream.of()로 생성하려는 객체를 입력하면, 스트림 객체로 생성
   */
  public static void createStreamOf() {
    Stream<String> stream = Stream.of("Python", "C", "Java", "C++", "Visual Basic");
    stream.forEach(System.out::println);
  }

  /**
   * Stream.empty()는 비어있는 스트림을 생성
   */
  public static void createStreamEmpty() {
    Stream<String> stream = Stream.empty();
    stream.forEach(System.out::println);
  }

  /**
   * Stream.generate()는 전달 인자로(Argument)로 함수를 받고, 리턴되는 객체가 스트림으로 생성
   */
  public static void createStreamGenerate() {
    Stream<String> stream = Stream.generate(() -> "Java").limit(5);
    stream.forEach(System.out::println);
  }

  /**
   * Stream.iterate()는 전달 인자로(Argument)로 첫 번째는 초기값, 두 번째는 함수로 받고, 리턴되는 객체가 스트림으로 생성
   */
  public static void createStreamIterate() {
    Stream<Integer> stream = Stream.iterate(0, n -> n + 2).limit(5);
    stream.forEach(System.out::println);
  }

  /**
   * List를 기준으로 스트림을 생성
   */
  public static void createListToStream() {
    List<String> list = Arrays.asList("Python", "C", "Java", "C++", "Visual Basic");
    Stream<String> stream = list.stream();
    stream.forEach(System.out::println);
  }

  /**
   * Array를 기준으로 스트림을 생성
   */
  public static void createArrayToStream() {
    String[] array = new String[]{"Python", "C", "Java", "C++", "Visual Basic"};
    Stream<String> stream = Arrays.stream(array);
    stream.forEach(System.out::println);
  }
}
