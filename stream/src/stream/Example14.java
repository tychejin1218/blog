package stream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Example14 {

  public static void main(String[] args) {

    System.out.println("1.sum() 사용법");
    createStreamIntSum();
    createStreamDoubleSum();

    System.out.println("2.average() 사용법");
    createStreamIntAverage();
    createStreamDoubleAverage();
  }

  /*
   * sum()
   * IntStream이나 DoubleStrem과 같은 기본 타입 스트림의 모든 요소에 합을 반환
   *
   * Int 타입 스트림의 합
   */
  public static void createStreamIntSum() {
    IntStream stream = IntStream.of(10, 20, 30, 40, 50);
    System.out.println(stream.sum());
  }

  /*
   * Double 타입 스트림의 평균
   */
  public static void createStreamDoubleSum() {
    DoubleStream stream = DoubleStream.of(10.1, 20.2, 30.3, 40.4, 50.5);
    System.out.println(stream.sum());
  }

  /*
   * average()
   * IntStream이나 DoubleStrem과 같은 기본 타입 스트림의 모든 요소에 평균을 각 기본 타입으로 래핑된 Optional 객체를 반환
   *
   * Int 타입 스트림의 평균
   */
  public static void createStreamIntAverage() {
    IntStream stream = IntStream.of(10, 20, 30, 40, 50);
    System.out.println(stream.average().getAsDouble());
  }

  /*
   * Double 타입 스트림의 평균
   */
  public static void createStreamDoubleAverage() {
    DoubleStream stream = DoubleStream.of(10.1, 20.2, 30.3, 40.4, 50.5);
    System.out.println(stream.average().getAsDouble());
  }
}