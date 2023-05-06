package stream;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class Example18Test {

  private List<String> list = Arrays.asList("Python", "C", "Java", "C++", "Visual Basic");

  private String array[] = {"Python", "C", "Java", "C++", "Visual Basic"};

  @Test
  public void testConvertArrayToList01() {

    // Given & When
    List<String> convertedList = new Example18().convertArrayToList01(array);

    // Then
    assertIterableEquals(list, convertedList);
  }

  @Test
  public void testConvertArrayToList02() {

    // Given & When
    List<String> convertedList = new Example18().convertArrayToList02(array);

    // Then
    assertIterableEquals(list, convertedList);
  }

  @Test
  public void testConvertArrayToList03() {

    // Given & When
    List<String> convertedList = new Example18().convertArrayToList03(array);

    // Then
    assertIterableEquals(list, convertedList);
  }

  @Test
  public void testConvertArrayToList04() {

    // Given & When
    List<String> convertedList = new Example18().convertArrayToList04(array);

    // Then
    assertIterableEquals(list, convertedList);
  }
}