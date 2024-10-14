# 자주 사용되는 Java String 메서드

Java의 `String` 클래스에서 자주 사용되는 메서드에 대해서 다양한 사용 예제를 통해 알아보겠습니다.

## 1. `String.length()` 테스트: 문자열의 길이를 반환
```java
@Test
void testLength() {
  // Given
  String str = "Hello";

  // When
  int len = str.length();

  // Then
  assertEquals(5, len, "문자열 길이가 일치합니다");
}
```
이 테스트는 문자열 `"Hello"`의 길이가 5인지 확인합니다.

## 2. `String.charAt()` 테스트: 특정 인덱스의 문자를 반환
```java
@Test
void testCharAt() {
  // Given
  String str = "Hello";

  // When
  char ch = str.charAt(1);

  // Then
  assertEquals('e', ch, "인덱스 1의 문자가 일치합니다");
}
```
이 테스트는 문자열 `"Hello"`에서 인덱스 1의 문자가 `'e'`인지 확인합니다.

## 3. `String.substring()` 테스트: 특정 범위의 서브 문자열을 반환
```java
@Test
void testSubstring() {
  // Given
  String str = "Hello";

  // When
  String sub = str.substring(1, 4);

  // Then
  assertEquals("ell", sub, "서브 문자열이 일치합니다");
}
```
이 테스트는 문자열 `"Hello"`에서 인덱스 1부터 4까지의 서브 문자열이 `"ell"`인지 확인합니다.

## 4. `String.equals()` 테스트: 문자열이 동일한지 비교
```java
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
```
이 테스트는 두 문자열 `"Hello"`가 동일한지 확인합니다.

## 5. `String.equalsIgnoreCase()` 테스트: 대소문자를 무시하고 문자열이 동일한지 비교
```java
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
```
이 테스트는 두 문자열 `"Hello"`와 `"hello"`가 대소문자를 무시하고 동일한지 확인합니다.

## 6. `String.compareTo()` 테스트: 문자열을 사전순으로 비교
```java
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
```
이 테스트는 문자열 `"abc"`가 `"abd"`보다 사전순으로 앞에 있는지 비교합니다.

## 7. `String.indexOf()` 테스트: 특정 문자열의 처음 나타나는 인덱스를 반환
```java
@Test
void testIndexOf() {
  // Given
  String str = "Hello";

  // When
  int index = str.indexOf("e");

  // Then
  assertEquals(1, index, "문자 'e'의 인덱스가 일치합니다");
}
```
이 테스트는 문자열 `"Hello"`에서 문자 `'e'`의 첫 번째 인덱스가 1인지 확인합니다.

## 8. `String.lastIndexOf()` 테스트: 특정 문자열의 마지막 나타나는 인덱스를 반환
```java
@Test
void testLastIndexOf() {
  // Given
  String str = "Hello";

  // When
  int index = str.lastIndexOf("l");

  // Then
  assertEquals(3, index, "문자 'l'의 마지막 인덱스가 일치합니다");
}
```
이 테스트는 문자열 `"Hello"`에서 문자 `'l'`의 마지막 인덱스가 3인지 확인합니다.

## 9. `String.replace()` 테스트: 문자열 내의 특정 문자열을 다른 문자열로 치환
```java
@Test
void testReplace() {
  // Given
  String str = "Hello";

  // When
  String newStr = str.replace('l', 'p');

  // Then
  assertEquals("Heppo", newStr, "문자열 치환 결과가 일치합니다");
}
```
이 테스트는 문자열 `"Hello"`에서 문자 `'l'`을 `'p'`로 치환하여 `"Heppo"`가 되는지 확인합니다.

## 10. `String.toUpperCase()` 테스트: 문자열을 대문자로 변환
```java
@Test
void testToUpperCase() {
  // Given
  String str = "Hello";

  // When
  String upperStr = str.toUpperCase();

  // Then
  assertEquals("HELLO", upperStr, "대문자로 변환된 문자열이 일치합니다");
}
```
이 테스트는 문자열 `"Hello"`를 대문자 `"HELLO"`로 변환하는지 확인합니다.

## 11. `String.toLowerCase()` 테스트: 문자열을 소문자로 변환
```java
@Test
void testToLowerCase() {
  // Given
  String str = "Hello";

  // When
  String lowerStr = str.toLowerCase();

  // Then
  assertEquals("hello", lowerStr, "소문자로 변환된 문자열이 일치합니다");
}
```
이 테스트는 문자열 `"Hello"`를 소문자 `"hello"`로 변환하는지 확인합니다.

## 12. `String.trim()` 테스트: 문자열의 양 끝에서 공백을 제거
```java
@Test
void testTrim() {
  // Given
  String str = "  Hello  ";

  // When
  String trimmedStr = str.trim();

  // Then
  assertEquals("Hello", trimmedStr, "공백이 제거된 문자열이 일치합니다");
}
```
이 테스트는 문자열 `"  Hello  "`의 양 끝 공백을 제거한 결과가 `"Hello"`인지 확인합니다.

## 13. `String.split()` 테스트: 문자열을 주어진 기준으로 분리하여 배열로 반환
```java
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
```
이 테스트는 문자열 `"apple,orange,banana"`를 콤마 기준으로 분리하여 배열 `["apple", "orange", "banana"]`와 일치하는지 확인합니다.

## 요약
이 클래스는 자주 사용되는 `String` 메서드들의 동작을 테스트합니다. 각 메서드에 대한 이해와 함께 다양한 사례를 통해 이를 검증함으로써 개발 과정에서 신뢰성을 높일 수 있습니다.

## 추가 자료

- [Java 8 공식 문서 - java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html): Java의 공식 문서에서 String 클래스의 모든 메서드와 그 설명을 확인할 수 있습니다.
- [Baeldung: Java String Methods](https://www.baeldung.com/java-string): 자주 사용되는 Java String 메서드들에 대한 설명과 예제가 포함되어 있습니다.
