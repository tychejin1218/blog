package stringmethods;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class FrequentlyUsedStringMethodsTest {

  @Order(1)
  @DisplayName("String.length() 테스트: 문자열의 길이를 반환")
  @Test
  void testLength() {

    // Given
    String str = "Hello";

    // When
    int len = str.length();

    // Then
    assertEquals(5, len, "문자열 길이가 일치합니다");
  }

  @Order(2)
  @DisplayName("String.charAt() 테스트: 특정 인덱스의 문자를 반환")
  @Test
  void testCharAt() {

    // Given
    String str = "Hello";

    // When
    char ch = str.charAt(1);

    // Then
    assertEquals('e', ch, "인덱스 1의 문자가 일치합니다");
  }

  @Order(3)
  @DisplayName("String.substring() 테스트: 특정 범위의 서브 문자열을 반환")
  @Test
  void testSubstring() {

    // Given
    String str = "Hello";

    // When
    String sub = str.substring(1, 4);

    // Then
    assertEquals("ell", sub, "서브 문자열이 일치합니다");
  }

  @Order(4)
  @DisplayName("String.equals() 테스트: 문자열이 동일한지 비교")
  @Test
  void testEquals() {

    // Given
    String str1 = "Hello";
    String str2 = "Hello";

    // When
    boolean isEqual = str1.equals(str2);

    // Then
    assertTrue(isEqual, "두 문자열이 동일합니다");
  }

  @Order(5)
  @DisplayName("String.equalsIgnoreCase() 테스트: 대소문자를 무시하고 문자열이 동일한지 비교")
  @Test
  void testEqualsIgnoreCase() {

    // Given
    String str1 = "Hello";
    String str2 = "hello";

    // When
    boolean isEqual = str1.equalsIgnoreCase(str2);

    // Then
    assertTrue(isEqual, "두 문자열이 대소문자를 무시하고 동일합니다");
  }

  @Order(6)
  @DisplayName("String.compareTo() 테스트: 문자열을 사전순으로 비교")
  @Test
  void testCompareTo() {

    // Given
    String str1 = "abc";
    String str2 = "abd";

    // When
    int result = str1.compareTo(str2);

    // Then
    assertEquals(-1, result, "str1이 str2보다 사전순으로 앞에 있습니다");
  }

  @Order(7)
  @DisplayName("String.indexOf() 테스트: 특정 문자열의 처음 나타나는 인덱스를 반환")
  @Test
  void testIndexOf() {

    // Given
    String str = "Hello";

    // When
    int index = str.indexOf("e");

    // Then
    assertEquals(1, index, "문자 'e'의 인덱스가 일치합니다");
  }

  @Order(8)
  @DisplayName("String.lastIndexOf() 테스트: 특정 문자열의 마지막 나타나는 인덱스를 반환")
  @Test
  void testLastIndexOf() {

    // Given
    String str = "Hello";

    // When
    int index = str.lastIndexOf("l");

    // Then
    assertEquals(3, index, "문자 'l'의 마지막 인덱스가 일치합니다");
  }

  @Order(9)
  @DisplayName("String.replace() 테스트: 문자열 내의 특정 문자열을 다른 문자열로 치환")
  @Test
  void testReplace() {

    // Given
    String str = "Hello";

    // When
    String newStr = str.replace('l', 'p');

    // Then
    assertEquals("Heppo", newStr, "문자열 치환 결과가 일치합니다");
  }

  @Order(10)
  @DisplayName("String.toUpperCase() 테스트: 문자열을 대문자로 변환")
  @Test
  void testToUpperCase() {

    // Given
    String str = "Hello";

    // When
    String upperStr = str.toUpperCase();

    // Then
    assertEquals("HELLO", upperStr, "대문자로 변환된 문자열이 일치합니다");
  }

  @Order(11)
  @DisplayName("String.toLowerCase() 테스트: 문자열을 소문자로 변환")
  @Test
  void testToLowerCase() {

    // Given
    String str = "Hello";

    // When
    String lowerStr = str.toLowerCase();

    // Then
    assertEquals("hello", lowerStr, "소문자로 변환된 문자열이 일치합니다");
  }

  @Order(12)
  @DisplayName("String.trim() 테스트: 문자열의 양 끝에서 공백을 제거")
  @Test
  void testTrim() {

    // Given
    String str = "  Hello  ";

    // When
    String trimmedStr = str.trim();

    // Then
    assertEquals("Hello", trimmedStr, "공백이 제거된 문자열이 일치합니다");
  }

  @Order(13)
  @DisplayName("String.split() 테스트: 문자열을 주어진 기준으로 분리하여 배열로 반환")
  @Test
  void testSplit() {

    // Given
    String str = "apple,orange,banana";

    // When
    String[] fruits = str.split(",");

    // Then
    assertAll(
        () -> assertEquals("apple", fruits[0], "첫 번째 과일이 일치합니다"),
        () -> assertEquals("orange", fruits[1], "두 번째 과일이 일치합니다"),
        () -> assertEquals("banana", fruits[2], "세 번째 과일이 일치합니다")
    );
  }
}
