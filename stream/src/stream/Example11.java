package stream;

import java.util.stream.Stream;

/*
 * Stream peek() 사용법
 */
public class Example11 {

  public static void main(String[] args) {

//    System.out.println("1.peek() 사용법 : 동작하지 않음");
//    createStreamPeekNotWorking();

    System.out.println("1.peek() 사용법");
    createStreamPeek();
  }

  /*
   * peek() 메소드는 결과 스트림으로부터 요소를 소모하여 추가로 명시된 동작을 수행합니다.
   * 또한, 원본 스트림에서 요소로를 소모하지 않으므로, 주로 연산과 연산 사이에 결과를 확인할 때 사용합니다.
   *
   * 중간 처리 단계에서 전체 요소를 루핑하며, 추가적인 작업을 할 때 사용합니다.
   */
  // peek() 메소드를 최종 연산으로 사용하면 동작하지 않음
  public static void createStreamPeekNotWorking() {
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    stream.filter(s -> s % 3 == 0)
        .peek(s -> System.out.println("원본 스트림 : " + s));
  }

  // peek() 메소드를 중간 연산으로 사용하면 동작함
  public static void createStreamPeek() {
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    stream.filter(s -> s % 3 == 0)
        .peek(s -> System.out.println("원본 스트림 : " + s))
        .forEach(s -> {});
  }
}
