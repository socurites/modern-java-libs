package com.socurites.modern.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * java.time 패키지
 *
 * Temporal + TemporalAdjuster
 *  |-- LocalDate
 *  |-- LocalTIme
 *  |-- LocalDateTime
 *  |-- Instant
 *
 *  Temporal
 *    |-- ZoneDateTime
 *
 * TemporalAmount
 *  |-- Duration
 *
 *  ChronoPeriod
 *    |-- Period
 *
 * ---------------------------
 * TemporalAdjusters
 * - 날자 조정
 *
 * ---------------------------
 * DateTimeFormatter
 * - 날짜 포맷팅
 *
 * ---------------------------
 * ZoneId
 * - 시간대
 */
public class TimeEx {
  private static final Logger log = LoggerFactory.getLogger(TimeEx.class);

  public static void main(String[] args) throws InterruptedException {
    localDateEx();

    localTimeEx();

    localDateTimeEx();

    instantEx();

    durationEx();

    periodEx();
  }

  private static void periodEx() {
    log.info("===== periodEx");

    Period period1 = Period.between(LocalDate.of(2020, 7, 4), LocalDate.of(2020, 7, 10));
    Period period2 = Period.between(LocalDate.of(2020, 7, 10), LocalDate.of(2020, 7, 4));

    log.info("{}, {}", period1, period2);
  }

  private static void durationEx() throws InterruptedException {
    log.info("===== durationEx");
    Duration between = Duration.between(LocalTime.of(13, 45, 50), LocalTime.of(13, 45, 55));
    log.info("in seconds: {}, nanos: {}", between.getSeconds(), between.get(ChronoUnit.NANOS));

    Instant now = Instant.now();
    Thread.sleep(1003);
    between = Duration.between(now, Instant.now());
    log.info("in seconds: {}, nanos: {}", between.getSeconds(), between.get(ChronoUnit.NANOS));

    Duration.ofMillis(1000);
    Duration.ofMinutes(3);
  }

  private static void instantEx() {
    // Same Instant
    Instant.ofEpochSecond(3);
    Instant.ofEpochSecond(3, 0);
    Instant.ofEpochSecond(2, 1_000_000_000);
    Instant.ofEpochSecond(4, -1_000_000_000);

  }

  private static void localDateTimeEx() {
    LocalDateTime date = LocalDateTime.of(2020, 7, 4, 13, 45, 50);
  }

  private static void localTimeEx() {
    LocalTime time = LocalTime.of(13, 45, 50);
    int hour = time.getHour();
    int minute = time.getMinute();
    int second = time.getSecond();
  }

  private static void localDateEx() {
    LocalDate date = LocalDate.of(2020, 7, 4);
    int year = date.getYear();
    Month month = date.getMonth();
    int day = date.getDayOfMonth();
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    int len = date.lengthOfMonth();
    boolean isLeapYear = date.isLeapYear();

    //TemporalField
    int year2 = date.get(ChronoField.YEAR);
//    ChronoField.MONTH_OF_YEAR
//    ChronoField.DAY_OF_MONTH
//    ChronoField.DAY_OF_WEEK
  }
}
