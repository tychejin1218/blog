package stream;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
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
  }

  /*
   * Collectors.toList()
   *  - 스트림 요소를 List로 반환
   */
  public static void createStreamToList() {
    Stream<String> stream = Stream.of(
        "Python", "Java", "JavaScript", "PHP", "SQL",
        "Python", "Java", "JavaScript", "PHP", "SQL"
    );
    List<String> strings = stream.collect(Collectors.toList());
    strings.forEach(System.out::println);
  }

  /*
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

  /*
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

  /*
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
}