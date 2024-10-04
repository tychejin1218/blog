package datetime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class DateTimeTest {

  private static final TimeZone KOREA_TIMEZONE = TimeZone.getTimeZone("Asia/Seoul");
  private static final ZoneId KOREA_ZONE_ID = ZoneId.of("Asia/Seoul");
  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss");
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss");

  @BeforeAll
  public static void setup() {
    SIMPLE_DATE_FORMAT.setTimeZone(KOREA_TIMEZONE);
    SIMPLE_DATE_TIME_FORMAT.setTimeZone(KOREA_TIMEZONE);
  }

  @Order(1)
  @DisplayName("한국 현지 날짜를 가져오고 특정 날짜를 2024-10-04로 설정하여 비교 - Java 8 이후")
  @Test
  void testCompareCurrentAndSpecificDate() {
    // Given
    LocalDate currentDate = LocalDate.now(KOREA_ZONE_ID);
    LocalDate expectedDate = LocalDate.now(KOREA_ZONE_ID);
    LocalDate specificDate = LocalDate.of(2024, 10, 4);

    // When
    LocalDate oneWeekLater = currentDate.plusWeeks(1);
    LocalDate oneMonthEarlier = currentDate.minusMonths(1);

    // Then
    assertAll(
        () -> assertEquals(expectedDate, currentDate, "현재 날짜가 다릅니다."),
        () -> assertEquals(LocalDate.of(2024, 10, 4), specificDate, "특정 날짜가 다릅니다."),
        () -> assertEquals(currentDate.plusWeeks(1), oneWeekLater, "일주일 후 날짜가 다릅니다."),
        () -> assertEquals(currentDate.minusMonths(1), oneMonthEarlier, "한 달 전 날짜가 다릅니다.")
    );
  }

  @Order(2)
  @DisplayName("한국 현지 날짜를 가져오고 특정 날짜를 2024-10-04로 설정하여 비교 - Java 8 이전")
  @Test
  void testCompareCurrentAndSpecificDatePreJava8() throws ParseException {

    // Given
    Date currentDate = new Date();
    Date expectedDate = SIMPLE_DATE_FORMAT.parse(
        SIMPLE_DATE_FORMAT.format(currentDate));
    Calendar calendar = Calendar.getInstance(KOREA_TIMEZONE);
    calendar.set(2024, Calendar.OCTOBER, 4);
    Date specificDate = SIMPLE_DATE_FORMAT.parse(
        SIMPLE_DATE_FORMAT.format(calendar.getTime()));

    // When
    calendar.setTime(expectedDate);
    calendar.add(Calendar.WEEK_OF_YEAR, 1);
    Date oneWeekLater = SIMPLE_DATE_FORMAT.parse(
        SIMPLE_DATE_FORMAT.format(calendar.getTime()));

    calendar.setTime(expectedDate);
    calendar.add(Calendar.MONTH, -1);
    Date oneMonthEarlier = SIMPLE_DATE_FORMAT.parse(
        SIMPLE_DATE_FORMAT.format(calendar.getTime()));

    // Then
    assertAll(
        () -> assertEquals(expectedDate,
            SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(currentDate)), "현재 날짜가 다릅니다."),
        () -> assertEquals(SIMPLE_DATE_FORMAT.parse("2024-10-04"), specificDate, "특정 날짜가 다릅니다."),
        () -> assertEquals(oneWeekLater,
            SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(oneWeekLater)), "일주일 후 날짜가 다릅니다."),
        () -> assertEquals(oneMonthEarlier,
            SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(oneMonthEarlier)), "한 달 전 날짜가 다릅니다.")
    );
  }

  @Order(3)
  @DisplayName("한국 현지 시간을 가져오고, 주어진 시간과 같거나 그 이후인지를 검증 - Java 8 이후")
  @Test
  void testCompareCurrentTimeAndGivenTime() {

    // Given
    LocalTime thirteenOClock = LocalTime.of(9, 0);

    // When
    LocalTime currentTime = LocalTime.now(KOREA_ZONE_ID);

    // Then
    assertTrue(currentTime.isAfter(thirteenOClock) || currentTime.equals(thirteenOClock), "현재 시간이 13시보다 이르다.");
  }

  @Order(4)
  @DisplayName("한국 현지 시간을 가져오고, 주어진 시간과 같거나 그 이후인지를 검증 - Java 8 이전")
  @Test
  void testCompareCurrentTimeAndGivenTimePreJava8() {

    // Given
    Calendar calendar = Calendar.getInstance(KOREA_TIMEZONE);
    calendar.set(Calendar.HOUR_OF_DAY, 9);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    Date thirteenOClock = calendar.getTime();

    // When
    calendar.setTime(new Date());
    Date currentTime = calendar.getTime();

    // Then
    assertTrue(currentTime.after(thirteenOClock) || currentTime.equals(thirteenOClock), "현재 시간이 13시보다 이르다.");
  }

  @Order(5)
  @DisplayName("한국 현지 날짜와 시간을 가져오고 특정 날짜와 시간을 2024-10-04 14:30:45로 설정하여 비교 - Java 8 이후")
  @Test
  void testCompareCurrentAndSpecificDateTime() {
    // Given
    LocalDateTime currentDateTime = LocalDateTime.now(KOREA_ZONE_ID);

    // When
    LocalDateTime tomorrowSameTime = currentDateTime.plusDays(1);
    LocalDateTime lastMonthSameTime = currentDateTime.minusMonths(1);

    // Then
    assertAll(
        () -> assertTrue(currentDateTime.isBefore(tomorrowSameTime), "현재 날짜와 시간이 내일 같은 시간보다 늦습니다."),
        () -> assertTrue(currentDateTime.isAfter(lastMonthSameTime),
            "현재 날짜와 시간이 지난 달 같은 시간보다 빠릅니다.")
    );
  }

  @Order(6)
  @DisplayName("한국 현지 날짜와 시간을 가져오고 특정 날짜와 시간을 2024-10-04 14:30:45로 설정하여 비교 - Java 8 이전")
  @Test
  void testCompareCurrentAndSpecificDateTimePreJava8() {

    // Given
    Date currentDateTime = new Date();
    Calendar calendar = Calendar.getInstance(KOREA_TIMEZONE);
    calendar.set(2024, Calendar.OCTOBER, 4, 14, 30, 45);

    // When
    calendar.setTime(currentDateTime);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    Date tomorrowSameTime = calendar.getTime();

    calendar.setTime(currentDateTime);
    calendar.add(Calendar.MONTH, -1);
    Date lastMonthSameTime = calendar.getTime();

    // Then
    assertAll(
        () -> assertTrue(currentDateTime.before(tomorrowSameTime), "현재 날짜와 시간이 내일 같은 시간보다 늦습니다."),
        () -> assertTrue(currentDateTime.after(lastMonthSameTime), "현재 날짜와 시간이 지난 달 같은 시간보다 빠릅니다.")
    );
  }

  @Order(7)
  @DisplayName("날짜와 시간을 포맷팅하여 문자열로 변환하고 다시 파싱하여 비교 - Java 8 이후")
  @Test
  void testFormatAndParseDateTime() {

    // Given
    LocalDateTime currentDateTime = LocalDateTime.now(KOREA_ZONE_ID);

    // When
    String formattedDateTime = currentDateTime.format(DATE_TIME_FORMATTER);
    LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, DATE_TIME_FORMATTER);

    // Then
    assertEquals(currentDateTime.truncatedTo(ChronoUnit.SECONDS), parsedDateTime,
        "포맷 후 파싱한 날짜와 시간이 현재 날짜와 시간이 다릅니다.");
  }

  @Order(8)
  @DisplayName("날짜와 시간을 포맷팅하여 문자열로 변환하고 다시 파싱하여 비교 - Java 8 이전")
  @Test
  void testFormatAndParseDateTimePreJava8() throws ParseException {

    // Given
    Date currentDateTime = new Date();

    // When
    String formattedDateTime = SIMPLE_DATE_TIME_FORMAT.format(currentDateTime);
    Date parsedDateTime = SIMPLE_DATE_TIME_FORMAT.parse(formattedDateTime);

    // Then
    assertEquals(currentDateTime.toString(), parsedDateTime.toString(),
        "포맷 후 파싱한 날짜와 시간이 현재 날짜와 시간이 다릅니다.");
  }

  @Order(9)
  @DisplayName("잘못된 날짜를 설정하여 예외를 발생시키는 테스트 - Java 8 이후")
  @Test
  void testInvalidDateThrowsException() {

    // Given-When-Then
    assertThrows(DateTimeException.class, () -> {
      LocalDate.of(2024, 2, 30); // 30일이 없는 날짜
    });
  }

  @Order(10)
  @DisplayName("잘못된 날짜를 설정하여 예외를 발생시키는 테스트 - Java 8 이전")
  @Test
  void testInvalidDateThrowsExceptionPreJava8() {

    // Given-When-Then
    assertThrows(IllegalArgumentException.class, () -> {
      Calendar calendar = Calendar.getInstance();
      calendar.setLenient(false); // 엄격한 검증 모드로 설정
      calendar.set(2024, Calendar.FEBRUARY, 30); // 30일이 없는 날짜
      calendar.getTime();
    });
  }
}
