# Java 11에서 추가된 String 메서드 사용 방법

Java 11에서는 `String` 클래스에 유용한 메서드가 여러 개 추가되었습니다. 이번 문서에서는 이러한 메서드들이 무엇인지, 각 메서드의 목적과 함께 다양한 사용 예제를 통해 알아보겠습니다.

## 1. String.isBlank()

### 설명
`isBlank()` 메서드는 문자열이 빈 문자열이거나 공백 문자(whitespace)로만 구성되어 있는지 확인합니다. 이는 공백 유무를 쉽게 체크할 수 있게 해줍니다.

### 사용 예제
```java
@Test
void testIsBlank() {
    String str = " ";
    boolean isBlank = str.isBlank();
    assertTrue(isBlank, "문자열이 공백입니다");
}
```
이 예제에서는 공백 문자열 `" "`를 확인하여 `isBlank()` 메서드가 `true`를 반환하는지 테스트합니다.

## 2. String.strip()

### 설명
`strip()` 메서드는 문자열의 시작과 끝에 있는 모든 공백 문자를 제거합니다. `trim()` 메서드와 비슷하지만, `strip()`은 Unicode의 모든 공백 문자를 처리할 수 있다는 점에서 더 강력합니다.

### 사용 예제
```java
@Test
void testStrip() {
    String str = "   Hello World!   ";
    String stripped = str.strip();
    assertEquals("Hello World!", stripped, "양쪽 공백이 제거되었습니다");
}
```
이 예제에서는 문자열 `"   Hello World!   "`의 양쪽 공백을 제거하고 결과를 검증합니다.

## 3. String.stripLeading()

### 설명
`stripLeading()` 메서드는 문자열의 시작 부분에 있는 모든 공백 문자를 제거합니다. 시작 공백만 제거할 때 유용합니다.

### 사용 예제
```java
@Test
void testStripLeading() {
    String str = "   Hello World!   ";
    String strippedLeading = str.stripLeading();
    assertEquals("Hello World!   ", strippedLeading, "앞쪽 공백이 제거되었습니다");
}
```
이 예제에서는 문자열 `"   Hello World!   "`의 시작 부분의 공백을 제거하고 결과를 검증합니다.

## 4. String.stripTrailing()

### 설명
`stripTrailing()` 메서드는 문자열의 끝 부분에 있는 모든 공백 문자를 제거합니다. 끝 공백만 제거할 때 유용합니다.

### 사용 예제
```java
@Test
void testStripTrailing() {
    String str = "   Hello World!   ";
    String strippedTrailing = str.stripTrailing();
    assertEquals("   Hello World!", strippedTrailing, "뒤쪽 공백이 제거되었습니다");
}
```
이 예제에서는 문자열의 끝 부분 공백을 제거하고 결과를 검증합니다.

## 5. String.lines()

### 설명
`lines()` 메서드는 여러 줄로 이루어진 문자열에서 각 줄을 스트림(Stream)으로 반환합니다. 이를 통해 각 줄에 대해 원하는 처리를 할 수 있습니다.

### 사용 예제
```java
@Test
void testLines() {
    String multilineStr = "This\nis\na\nmultiline\nstring.";
    long lineCount = multilineStr.lines().count();
    assertEquals(5, lineCount, "라인 수가 일치합니다");
}
```
이 예제에서는 문자열의 줄 수를 계산하고 결과를 검증합니다.

## 6. String.repeat()

### 설명
`repeat(int count)` 메서드는 문자열을 주어진 횟수만큼 반복하여 새로운 문자열을 생성합니다. 특정 문자열을 반복해야 할 때 유용합니다.

### 사용 예제
```java
@Test
void testRepeat() {
    String str = "abc";
    String repeated = str.repeat(3);
    assertEquals("abcabcabc", repeated, "문자열이 반복되었습니다");
}
```
이 예제에서는 문자열 `"abc"`를 세 번 반복한 결과를 검증합니다.

## 7. String.indent()

### 설명
`indent(int n)` 메서드는 각 줄 앞에 지정된 수만큼 공백을 추가하여 문자열을 들여쓰기 합니다. 음수 값을 사용하면 그만큼의 공백을 제거하며, 코드나 텍스트 형식을 맞출 때 유용합니다.

### 사용 예제
```java
@Test
void testIndent() {
    String str = "Hello\nWorld";
    String indentedStr = str.indent(4);
    String expected = "    Hello\n    World\n";
    assertEquals(expected, indentedStr, "문자열에 들여쓰기가 적용되었습니다");
}
```
이 예제에서는 각 줄 앞에 4개의 공백을 추가한 결과를 검증합니다.

## 8. String.stripIndent()

### 설명
`stripIndent()` 메서드는 모든 줄에서 공통적으로 시작되는 공백을 제거하여 문자열을 정리합니다. 텍스트 블록의 시작 부분을 일관되게 정렬해야 할 때 유용합니다.

### 사용 예제
```java
@Test
void testStripIndent() {
    String text = "    Hello\n    World\n    Java 11";
    String strippedIndent = text.stripIndent();
    String expected = "Hello\nWorld\nJava 11";
    assertEquals(expected, strippedIndent, "공통 시작 공백이 제거되었습니다");
}
```
이 예제에서는 각 줄에 공통적으로 포함된 4개의 공백을 제거한 결과를 검증합니다.

## 요약

Java 11에서 추가된 `String` 클래스의 새로운 메서드를 사용하여 문자열을 보다 효과적으로 다루는 방법을 배웠습니다. 각 메서드의 동작을 이해하고, 테스트 예제를 통해 활용법을 익히면 코드의 가독성과 유지보수성을 크게 향상시킬 수 있습니다.

### 추가 자료

- [Java 11 공식 문서 - java.lang.String](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html)
- [Baeldung: New String Methods in Java 11](https://www.baeldung.com/java-11-string-api)
