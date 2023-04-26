package stream;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stream.Example17.Language;

class Example17Test {

  private List<Language> languageList;

  @BeforeEach
  void init() {
    languageList = new ArrayList<>();
    languageList.add(new Language(1, "Python"));
    languageList.add(new Language(2, "C"));
    languageList.add(new Language(3, "Java"));
    languageList.add(new Language(4, "Java"));
    languageList.add(new Language(5, "Java"));
  }

  @Test
  public void testConvertMapToList01() {

    // Given & When
    Map<Integer, Language> languageMap = new Example17().convertMapToList01(languageList);

    // Then
    assertIterableEquals(languageList, languageMap.values());
  }

  @Test
  public void testConvertMapToList02() {

    // Given & When
    Map<Integer, Language> languageMap = new Example17().convertMapToList02(languageList);

    // Then
    assertIterableEquals(languageList, languageMap.values());
  }
}
