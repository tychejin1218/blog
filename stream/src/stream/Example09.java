package stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream Collectors.toList(), Collectors.toSet(), Collectors.toMap() 사용법
 */
public class Example09 {

  public static void main(String[] args) {

    System.out.println("1.스트림을 기준으로 List을 생성");
    createStreamToList();

    System.out.println("2.스트림을 기준으로 Set을 생성");
    createStreamToSet();

    System.out.println("3.스트림을 기준으로 Map을 생성");
    createStreamToMap();

    System.out.println("4.스트림을 기준으로 Map을 생성 : Key가 중복일 때");
    createStreamToMapDuplicateKey();

    System.out.println("5.해당 스트림의 요소를 1개의 String으로 반환");
    createStreamToString();

    System.out.println("6.해당 스트림의 요소의 글자 수에 따라 홀수와 짝수로 구분");
    createStreamToPartitioningBy();

    System.out.println("7.해당 스트림에서 순위(rank)가 가장 높은 요소를 반환");
    createStreamToMinBy();
  }

  /**
   * Collectors.toList()
   *  - 스트림 요소를 List로 반환   *
   */
  public static void createStreamToList() {
    Stream<String> stream = Stream.of(
        "Python", "Java", "JavaScript", "PHP", "SQL",
        "Python", "Java", "JavaScript", "PHP", "SQL"
    );
    List<String> strings = stream.collect(Collectors.toList());
    strings.forEach(System.out::println);
  }

  /**
   * Collectors.toSet()
   *  - 스트림 요소를 Set으로 반환
   */
  public static void createStreamToSet() {
    Stream<String> stream = Stream.of(
        "Python", "Java", "JavaScript", "PHP", "SQL",
        "Python", "Java", "JavaScript", "PHP", "SQL"
    );
    Set<String> stringSet = stream.collect(Collectors.toSet());
    stringSet.forEach(System.out::println);
  }

  /**
   * Collectors.toSet()
   *  - 스트림 요소를 Map으로 반환
   */
  public static void createStreamToMap() {
    Stream<Language> languageStream = Stream.of(
        new Language(1, "Python"),
        new Language(2, "Java"),
        new Language(3, "JavaScript"),
        new Language(4, "PHP"),
        new Language(5, "SQL")
    );

    Map<Integer, String> languageMap =
        languageStream.collect(Collectors.toMap(Language::getRank, Language::getLanguage));

    languageMap.entrySet().forEach(
        (entry) -> System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue())
    );
  }

  /**
   * Collectors.toSet()
   *  - 스트림 요소를 Map으로 반환
   */
  public static void createStreamToMapDuplicateKey() {
    Stream<Language> languageStream = Stream.of(
        new Language(1, "Python"),
        new Language(2, "Java"),
        new Language(3, "JavaScript"),
        new Language(4, "PHP"),
        new Language(5, "SQL"),
        new Language(1, "Kotlin")
    );

    Map<Integer, String> languageMap =
        languageStream.collect(
            Collectors.toMap(Language::getRank, Language::getLanguage, (l1, l2) -> l1)
        );

    languageMap.entrySet().forEach(
        (entry) -> System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue())
    );
  }

  static class Language {

    public Integer rank;
    public String language;

    Language(Integer rank, String language) {
      this.rank = rank;
      this.language = language;
    }

    public Integer getRank() {
      return rank;
    }

    public String getLanguage() {
      return language;
    }
  }

  /**
   * Collectors.joining()
   *  - 해당 스트림의 요소를 ', '을 기준으로 합쳐서 문자열로 반환
   */
  public static void createStreamToString() {
    Stream<String> stream = Stream.of("Python", "Java", "JavaScript", "PHP", "SQL");
    String result = stream.map(Object::toString).collect(Collectors.joining(", "));
    System.out.println("', '을 구분으로 합친 문자열 : " + result);
  }

  /**
   * Collectors.partitioningBy()
   *  - 해당 스트림의 요소의 글자 수에 따라 짝수와 홀수 리스트로 반환
   */
  public static void createStreamToPartitioningBy() {
    Stream<String> stream = Stream.of("Python", "Java", "JavaScript", "PHP", "SQL");
    Map<Boolean, List<String>> patition = stream.collect(
        Collectors.partitioningBy(s -> (s.length() % 2) == 0));
    System.out.println("글자 수가 짝수인 요소 : " + patition.get(true));
    System.out.println("글자 수가 홀수인 요소 : " + patition.get(false));
  }

  /**
   * Collectors.minBy()
   *  - 해당 스트림에서 순위가 가장 높은(rank:1) 요소를 반환
   */
  public static void createStreamToMinBy() {
    Stream<Language> languageStream = Stream.of(
        new Language(1, "Python"),
        new Language(2, "Java"),
        new Language(3, "JavaScript"),
        new Language(4, "PHP"),
        new Language(5, "SQL"),
        new Language(6, "Kotlin")
    );
    Optional<Language> opLanguage = languageStream.collect(
        Collectors.minBy((o1, o2) -> o1.getRank() - o2.getRank())
    );
    System.out.println(opLanguage.get().getLanguage());
  }
}
