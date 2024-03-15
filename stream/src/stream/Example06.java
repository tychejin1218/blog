package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Stream max(), min() 사용법
 */
public class Example06 {

  public static void main(String[] args) {

    System.out.println("1.count() 사용법 : 해당 스트림의 총 개수");
    createStreamCount();

    System.out.println("2.max() 사용법 : 해당 스트림에서 가장 큰 값");
    createStreamIntMax();

    System.out.println("3.max() 사용법 : 해당 스트림에서 가장 큰 값");
    createStreamIntMin();
    
    System.out.println("4.max() 사용법 : 해당 스트림에서 가장 큰 값(알파벳 순서)");
    createStreamStringMax();

    System.out.println("5.min() 사용법 : 해당 스트림에서 가장 작은 값(알파벳 순서)");
    createStreamStringMin();

    System.out.println("6.max() 사용법 : 해당 스트림에서 문자열 길이가 가장 긴 문자열");
    createStreamStringMaxLength();

    System.out.println("7.min() 사용법 : 해당 스트림에서 문자열 길이가 가장 짧은 문자열");
    createStreamStringMinLength();
  }

  /**
   * count()
   *  - 해당 스트림의 요소의 총 개수를 long 타입의 값으로 반환
   */
  public static void createStreamCount() {
    IntStream ints = IntStream.of(10, 20, 30, 40, 50);
    Long intsCount = ints.count();
    System.out.println(intsCount);
  }

  /**
   * max(Comparator<? super T> comparator)
   *  - 해당 스트림의 요소 중에서 가장 큰 값을 가지는 요소를 참조하는 Optional 객체를 반환
   */
  public static void createStreamIntMax() {
    IntStream ints = IntStream.of(10, 20, 30, 40, 50);
    OptionalInt opMaxInt = ints.max();
    opMaxInt.ifPresent(System.out::println);
  }

  /**
   * max(Comparator<? super T> comparator)
   *  - 해당 스트림의 요소 중에서 가장 작은 값을 가지는 요소를 참조하는 Optional 객체를 반환
   */
  public static void createStreamIntMin() {
    IntStream ints = IntStream.of(10, 20, 30, 40, 50);
    OptionalInt opMinInt = ints.min();
    opMinInt.ifPresent(System.out::println);
  }

  /**
   * max(Comparator<? super T> comparator)
   *  - 문자열에서 가장 큰 값(알파벳 순서)을 구하려면 Comparator에서 문자열의 알파벳 순서를 비교하도록 설정
   *  - compareToIgnoreCase()는 대/소문자를 구분하지 않고 비교
   */
  public static void createStreamStringMax() {
    List<String> strings = Arrays.asList("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Comparator<String> comparator = (s1, s2) -> s1.compareToIgnoreCase(s2);
    Optional<String> opMaxString = strings.stream().max(comparator);
    opMaxString.ifPresent(System.out::println);
  }

  /**
   * min(Comparator<? super T> comparator)
   *  - 문자열에서 가장 작은 값(알파벳 순서)을 구하려면 Comparator에서 문자열의 알파벳 순서를 비교하도록 설정
   *  - compareToIgnoreCase()는 대/소문자를 구분하지 않고 비교
   */
  public static void createStreamStringMin() {
    List<String> strings = Arrays.asList("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Comparator<String> comparator = (s1, s2) -> s1.compareToIgnoreCase(s2);
    Optional<String> opMinString = strings.stream().min(comparator);
    opMinString.ifPresent(System.out::println);
  }

  /**
   * max(Comparator<? super T> comparator)
   *  - 문자열이 가장 길 문자열을 구하려면 Comparator에서 문자열의 length를 비교하도록 설정
   *  - Comparator.comparingInt(String::length)는 문자열의 길이를 비교
   */
  public static void createStreamStringMaxLength() {
    List<String> strings = Arrays.asList("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Comparator<String> comparatorLength = Comparator.comparingInt(String::length);
    Optional<String> opMaxString = strings.stream().max(comparatorLength);
    opMaxString.ifPresent(System.out::println);
  }

  /**
   * min(Comparator<? super T> comparator)
   *  - 문자열이 가장 짧은 문자열을 구하려면 Comparator에서 문자열의 length를 비교하도록 설정
   *  - Comparator.comparingInt(String::length)는 문자열의 길이를 비교
   */
  public static void createStreamStringMinLength() {
    List<String> strings = Arrays.asList("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Comparator<String> comparatorLength = Comparator.comparingInt(String::length);
    Optional<String> opMinString = strings.stream().min(comparatorLength);
    opMinString.ifPresent(System.out::println);
  }
}
