package stream;

import java.util.Optional;
import java.util.stream.Stream;

public class Example13 {

  public static void main(String args[]) {

    System.out.println("1.reduce() 사용법");
    createStreamReduceSum();

    System.out.println("2.reduce() 사용법 : 초깃값");
    createStreamInitialValueReduce();

    System.out.println("3.reduce() 사용법 : 비어있는 스트림");
    createStreamEmptyReduce();
  }

  /*
   * reduce()
   *  첫 번째와 두 번째 요소를 가지고 연산을 수행한 뒤, 그 결과와 세 번째 요소를 가지고 또 다시 연산을 수행합니다.
   * 이런 식으로 해당 스트림의 모든 요소를 소모하여 연산을 수행하고, 그 결과를 반환합니다.
   *
   * 예제) 해당 스트림의 전체 합
   */
  public static void createStreamReduceSum() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    Optional<Integer> result = numbers.reduce((subtotal, element) -> subtotal + element);
    result.ifPresent(System.out::println);
  }


  /*
   * 예제) 초깃값과 해당 스트림의 전체 합
   * 초깃값을 전달하는 reduce() 메소드는 반환 타입이 Optional<T>가 아닌 T 타입입니다.
   */
  public static void createStreamInitialValueReduce() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    Integer result = numbers.reduce(10, (subtotal, element) -> subtotal + element);
    System.out.println(result);
  }

  /*
   * 예제) 초깃값과 비어있는 스트림의 전체 합
   * 비어있는 스트림과 reduce 연산을 할 경우 전달받은 초깃값을 그대로 반환합니다.
   */
  public static void createStreamEmptyReduce() {
    Stream<Integer> numbers = Stream.empty();
    Integer result = numbers.reduce(10, (subtotal, element) -> subtotal + element);
    System.out.println(result);
  }
}