package stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * List를 Map으로 변환
 */
public class Example17 {

  /**
   * 1. for문을 이용하여 List를 Map으로 변환
   */
  public Map convertListToMap01(List<Language> languageList) {

    Map<Integer, Language> languageMap = new HashMap<>();
    for (Language language : languageList) {
      languageMap.put(language.getId(), language);
    }

    return languageMap;
  }

  /**
   * 2. Stream을 이용하여 List를 Map으로 변환
   * <p/>
   * Stream.collect()과 Collectors.toMap()을 이용하여 다음과 같이 List를 Map으로 변환할 수 있습니다.
   * Function.identity()는 인자로 들어온 값을 그대로 반환합니다.
   */
  public Map convertListToMap02(List<Language> languageList) {

    Map<Integer, Language> languageMap = languageList.stream()
        .collect(Collectors.toMap(Language::getId, Function.identity()));

    return languageMap;
  }

  public static class Language {

    public Integer id;
    public String language;

    public Language(Integer id, String language) {
      this.id = id;
      this.language = language;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getLanguage() {
      return language;
    }

    public void setLanguage(String language) {
      this.language = language;
    }
  }
}
