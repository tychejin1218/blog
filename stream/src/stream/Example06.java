package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/*
 * Stream max(), min() 사용법
 */
public class Example06 {

  public static void main(String[] args) {

    System.out.println("1.max() 사용법 : 문자열에서 가장 큰 값(알파벳 순서)");
    createStreamMax();

    System.out.println("2.min() 사용법 : 문자열에서 가장 작은 값(알파벳 순서)");
    createStreamMin();

    System.out.println("3.max() 사용법 : 문자열 길이가 가장 긴 문자열");
    createStreamMaxLength();

    System.out.println("4.min() 사용법 : 문자열 길이가 가장 짧은 문자열");
    createStreamMinLength();
  }

  /*
   * max(Comparator<? super T> comparator)
   *  - 문자열에서 가장 큰 값(알파벳 순서)을 구하려면 Comparator에서 문자열의 알파벳 순서를 비교하도록 설정
   *  - compareToIgnoreCase()는 대/소문자를 구분하지 않고 비교
   */
  public static void createStreamMax() {
    List<String> strings = Arrays.asList("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Comparator<String> comparator = (s1, s2) -> s1.compareToIgnoreCase(s2);
    Optional<String> opMaxString = strings.stream().max(comparator);
    opMaxString.ifPresent(System.out::println);
  }

  /*
   * min(Comparator<? super T> comparator)
   *  - 문자열에서 가장 작은 값(알파벳 순서)을 구하려면 Comparator에서 문자열의 알파벳 순서를 비교하도록 설정
   *  - compareToIgnoreCase()는 대/소문자를 구분하지 않고 비교
   */
  public static void createStreamMin() {
    List<String> strings = Arrays.asList("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Comparator<String> comparator = (s1, s2) -> s1.compareToIgnoreCase(s2);
    Optional<String> opMinString = strings.stream().min(comparator);
    opMinString.ifPresent(System.out::println);
  }

  /*
   * max(Comparator<? super T> comparator)
   *  - 문자열이 가장 길 문자열을 구하려면 Comparator에서 문자열의 length를 비교하도록 설정
   *  - Comparator.comparingInt(String::length)는 문자열의 길이를 비교
   */
  public static void createStreamMaxLength() {
    List<String> strings = Arrays.asList("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Comparator<String> comparatorLength = Comparator.comparingInt(String::length);
    Optional<String> opMaxString = strings.stream().max(comparatorLength);
    opMaxString.ifPresent(System.out::println);
  }

  /*
   * min(Comparator<? super T> comparator)
   *  - 문자열이 가장 짧은 문자열을 구하려면 Comparator에서 문자열의 length를 비교하도록 설정
   *  - Comparator.comparingInt(String::length)는 문자열의 길이를 비교
   */
  public static void createStreamMinLength() {
    List<String> strings = Arrays.asList("Python", "Java", "PHP", "JavaScript", "Kotlin");
    Comparator<String> comparatorLength = Comparator.comparingInt(String::length);
    Optional<String> opMinString = strings.stream().min(comparatorLength);
    opMinString.ifPresent(System.out::println);
  }
}