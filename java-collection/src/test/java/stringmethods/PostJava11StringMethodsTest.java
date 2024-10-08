package stringmethods;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class PostJava11StringMethodsTest {

  @Order(1)
  @DisplayName("String.isBlank 테스트")
  @Test
  void testIsBlank() {

    // Given
    String str = " ";

    // When
    boolean isBlank = str.isBlank();

    // Then
    assertTrue(isBlank, "문자열이 공백입니다");
  }

  @Order(2)
  @DisplayName("String.strip 테스트")
  @Test
  void testStrip() {

    // Given
    String str = "   Hello World!   ";

    // When
    String stripped = str.strip();

    // Then
    assertEquals("Hello World!", stripped, "양쪽 공백이 제거되었습니다");
  }

  @Order(3)
  @DisplayName("String.stripLeading 테스트")
  @Test
  void testStripLeading() {

    // Given
    String str = "   Hello World!   ";

    // When
    String strippedLeading = str.stripLeading();

    // Then
    assertEquals("Hello World!   ", strippedLeading, "앞쪽 공백이 제거되었습니다");
  }

  @Order(4)
  @DisplayName("String.stripTrailing 테스트")
  @Test
  void testStripTrailing() {

    // Given
    String str = "   Hello World!   ";

    // When
    String strippedTrailing = str.stripTrailing();

    // Then
    assertEquals("   Hello World!", strippedTrailing, "뒤쪽 공백이 제거되었습니다");
  }

  @Order(5)
  @DisplayName("String.lines 테스트")
  @Test
  void testLines() {

    // Given
    String multilineStr = "This\nis\na\nmultiline\nstring.";

    // When
    long lineCount = multilineStr.lines().count();

    // Then
    assertEquals(5, lineCount, "라인 수가 일치합니다");
  }

  @Order(6)
  @DisplayName("String.repeat 테스트")
  @Test
  void testRepeat() {

    // Given
    String str = "abc";

    // When
    String repeated = str.repeat(3);

    // Then
    assertEquals("abcabcabc", repeated, "문자열이 반복되었습니다");
  }

  @Order(7)
  @DisplayName("String.indent 테스트")
  @Test
  void testIndent() {

    // Given
    String str = "Hello\nWorld";

    // When
    String indentedStr = str.indent(4);

    // Then
    String expected = "    Hello\n    World\n";
    assertEquals(expected, indentedStr, "문자열에 들여쓰기가 적용되었습니다");
  }

  @Order(8)
  @DisplayName("String.stripIndent 테스트")
  @Test
  void testStripIndent() {

    // Given
    String text = "    Hello\n    World\n    Java 11";

    // When
    String strippedIndent = text.stripIndent();

    // Then
    String expected = "Hello\nWorld\nJava 11";
    assertEquals(expected, strippedIndent, "공통 시작 공백이 제거되었습니다");
  }
}
