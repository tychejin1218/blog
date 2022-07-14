package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/*
 * Stream sorted() 사용법
 */
public class Example05 {

  public static void main(String[] args) {

    System.out.println("1.sorted() 사용법");
    createStreamSorted();

    System.out.println("2.sorted() 사용법 : 스트림을 역순으로 정렬");
    createStreamSortedReverseOrder();

    System.out.println("3.sorted() 사용법 : 스트림을 문자열 길이로 정렬");
    createStreamSortedStringLength();

    System.out.println("4.sorted() 사용법 : 스트림을 문자열 길이의 역순으로 정렬");
    createStreamSortedStringLengthReverseOrder();
  }

  /*
   * sorted()
   * sorted()는 원본 스트림의 요소들을 정렬하여 스트림을 생성하여 리턴
   */
  public static void createStreamSorted() {
    Stream<String> stream = Stream.of("Python", "Java", "PHP", "JavaScript", "Kotlin");
    stream.sorted().forEach(System.out::println);
  }

  /*
   * sorted(Comparator<? super T> comparator)
   * 스트림을 역순으로 정렬하려면 sorted()의 파라미터를 'Comparator.reverseOrder()'로 설정
   */
  public static void createStreamSortedReverseOrder() {
    Stream<String> stream = Stream.of("Python", "Java", "PHP", "JavaScript", "Kotlin");
    stream.sorted(Comparator.reverseOrder()).forEach(System.out::println);
  }

  /*
   * sorted(Comparator<? super T> comparator)
   * 스트림을 문자열 길이로 정렬하려면 sorted()의 파라미터를 'Comparator.comparing(String::length)' 설정
   */
  public static void createStreamSortedStringLength() {
    Stream<String> stream = Stream.of("Python", "Java", "PHP", "JavaScript", "Kotlin");
    stream.sorted(Comparator.comparing(String::length)).forEach(System.out::println);
  }

  /*
   * sorted(Comparator<? super T> comparator)
   * 스트림을 문자열 길이의 역순으로 정렬하려면 sorted()의 파라미터를 'Comparator.comparing(String::length).reversed()' 설정
   */
  public static void createStreamSortedStringLengthReverseOrder() {
    Stream<String> stream = Stream.of("Python", "Java", "PHP", "JavaScript", "Kotlin");
    stream.sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
  }
}
