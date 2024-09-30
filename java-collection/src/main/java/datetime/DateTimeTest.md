# Java 8 이전과 이후의 날짜 및 시간 비교 방법

## 소개

Java 8은 날짜와 시간 API에 큰 변화를 가져왔습니다. Java 8 이전의 `Date`와 `Calendar` 클래스는 여러 단점이 있었으며 이를 보완하기 위해 Java 8에서는 `LocalDate`, `LocalTime`, `LocalDateTime` 같은 새로운 클래스가 도입되었습니다. 본 포스트에서는 Java 8 이전과 이후의 날짜 및 시간 비교 방법을 다양한 예제와 함께 살펴보겠습니다.

## 클래스 설명

### Java 8 이후 클래스

- **`LocalDate`**: 날짜를 나타내는 클래스입니다. 연도, 월, 일 정보를 포함하며, 시간 정보는 포함하지 않습니다.
- **`LocalTime`**: 시간을 나타내는 클래스입니다. 시, 분, 초 정보를 포함하며, 날짜 정보는 포함하지 않습니다.
- **`LocalDateTime`**: 날짜와 시간을 모두 포함하는 클래스입니다. 연도, 월, 일, 시, 분, 초 정보를 모두 가지고 있습니다.

### Java 8 이전 클래스

- **`Date`**: 날짜와 시간을 나타내는 클래스입니다. deprecated된 많은 메서드들과 불변성이 아닌 특성 때문에 사용하기 어렵습니다.
- **`Calendar`**: 날짜와 시간을 조작할 수 있는 클래스입니다. `Date` 클래스의 단점을 보완하기 위해 도입되었지만, 여전히 사용하기 번거롭습니다.

## 현재 날짜와 특정 날짜 비교

### Java 8 이후

Java 8에서는 `LocalDate` 클래스를 사용하여 쉽게 날짜를 비교할 수 있습니다. 예제를 통해 살펴보겠습니다:

```java
@Order(1)
@DisplayName("한국 현지 날짜를 가져오고 특정 날짜를 2024-10-04로 설정하여 비교 - Java 8 이후")
@Test
void testCompareCurrentAndSpecificDate() {
  // Given: 현재 날짜와 특정 날짜를 설정합니다.
  LocalDate currentDate = LocalDate.now(KOREA_ZONE_ID);
  LocalDate expectedDate = LocalDate.now(KOREA_ZONE_ID);
  LocalDate specificDate = LocalDate.of(2024, 10, 4);

  // When: 현재 날짜에서 일주일 후와 한 달 전 날짜를 계산합니다.
  LocalDate oneWeekLater = currentDate.plusWeeks(1);
  LocalDate oneMonthEarlier = currentDate.minusMonths(1);

  // Then: 각 날짜가 예상한 값과 같은지 확인합니다.
  assertAll(
      () -> assertEquals(expectedDate, currentDate, "현재 날짜가 다릅니다."),
      () -> assertEquals(LocalDate.of(2024, 10, 4), specificDate, "특정 날짜가 다릅니다."),
      () -> assertEquals(currentDate.plusWeeks(1), oneWeekLater, "일주일 후 날짜가 다릅니다."),
      () -> assertEquals(currentDate.minusMonths(1), oneMonthEarlier, "한 달 전 날짜가 다릅니다.")
  );
}
```

이 예제에서는 `LocalDate`를 사용하여 현재 날짜와 특정 날짜를 비교하고, 일주일 후와 한 달 전 날짜를 계산하여 확인합니다.

### Java 8 이전

Java 8 이전에는 `Date`와 `Calendar` 클래스를 사용하여 날짜를 비교했습니다. 다음은 Java 8 이전의 예제입니다:

```java
@Order(2)
@DisplayName("한국 현지 날짜를 가져오고 특정 날짜를 2024-10-04로 설정하여 비교 - Java 8 이전")
@Test
void testCompareCurrentAndSpecificDatePreJava8() throws ParseException {
  // Given: 현재 날짜와 특정 날짜를 설정합니다.
  Date currentDate = new Date();
  Date expectedDate = SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(currentDate));
  Calendar calendar = Calendar.getInstance(KOREA_TIMEZONE);
  calendar.set(2024, Calendar.OCTOBER, 4);
  Date specificDate = SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(calendar.getTime()));

  // When: 현재 날짜에서 일주일 후와 한 달 전 날짜를 계산합니다.
  calendar.setTime(expectedDate);
  calendar.add(Calendar.WEEK_OF_YEAR, 1);
  Date oneWeekLater = SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(calendar.getTime()));

  calendar.setTime(expectedDate);
  calendar.add(Calendar.MONTH, -1);
  Date oneMonthEarlier = SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(calendar.getTime()));

  // Then: 각 날짜가 예상한 값과 같은지 확인합니다.
  assertAll(
      () -> assertEquals(expectedDate, SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(currentDate)), "현재 날짜가 다릅니다."),
      () -> assertEquals(SIMPLE_DATE_FORMAT.parse("2024-10-04"), specificDate, "특정 날짜가 다릅니다."),
      () -> assertEquals(oneWeekLater, SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(oneWeekLater)), "일주일 후 날짜가 다릅니다."),
      () -> assertEquals(oneMonthEarlier, SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(oneMonthEarlier)), "한 달 전 날짜가 다릅니다.")
  );
}
```

이 예제에서는 `Date`와 `Calendar`를 사용하여 날짜를 비교하고, 일주일 후와 한 달 전 날짜를 계산하여 확인합니다. 이 방법은 복잡하고 가독성이 떨어질 수 있습니다.

### 장단점 비교

- **Java 8 이후**
    - **장점**: 불변 객체로 안전하게 사용할 수 있다. 더 직관적이고 간단한 API를 제공한다.
    - **단점**: Java 7 이하 버전에서는 사용할 수 없다.

- **Java 8 이전**
    - **장점**: 모든 Java 버전에서 사용할 수 있다.
    - **단점**: 불변성이 없어 멀티스레딩 환경에서 안전하지 않다. 복잡하고 직관적이지 않다.

## 요약

Java 8 이후의 날짜 및 시간 API는 `LocalDate`, `LocalTime`, `LocalDateTime`과 같은 클래스를 통해 훨씬 직관적이고 사용하기 쉬워졌습니다. Java 8 이전에는 `Date`와 `Calendar` 클래스를 사용해야 했고, 이는 코드를 복잡하게 만들었습니다. 최신 API를 사용하는 것이 더 읽기 쉽고 유지 보수하기 좋은 코드를 작성하는 데 도움이 됩니다.

## 추가 자료

- [Java 8 공식 문서 - java.time 패키지](https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html)
- [Migration to java.time](https://www.baeldung.com/migrating-to-java-8-date-time-api)
