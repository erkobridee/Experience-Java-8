package academy.elqoo.java8.dateandtime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import static java.time.Month.JANUARY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DateAndTimeTest {

    @Test
    public void shouldCreateNewDate(){
        LocalDate newYearsEve = LocalDate.of(2017, Month.DECEMBER, 31); // create new years eve 2017 using the localdate static factory methods
        assertThat(newYearsEve.getYear(), is(equalTo(2017)));
        assertThat(newYearsEve.getMonth(), is(equalTo(Month.DECEMBER)));
        assertThat(newYearsEve.getDayOfMonth(), is(equalTo(31)));
    }

    @Test
    public void shouldGotoFirstOfNextMonth(){
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017();
        LocalDate firstJanuary2018 = newYearsEve.plusDays(1);
        assertThat(firstJanuary2018.getYear(), is(equalTo(2018)));
        assertThat(firstJanuary2018.getMonth(), is(equalTo(Month.JANUARY)));
        assertThat(firstJanuary2018.getDayOfMonth(), is(equalTo(1)));
    }

    @Test
    public void shouldRetrieveDateInformationUsingChronoFields(){
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017();
        int year = newYearsEve.get(ChronoField.YEAR);
        int month = newYearsEve.get(ChronoField.MONTH_OF_YEAR);
        int day = newYearsEve.get(ChronoField.DAY_OF_MONTH);
        assertThat(year, is(equalTo(newYearsEve.getYear())));
        assertThat(month, is(equalTo(12)));
        assertThat(day, is(equalTo(newYearsEve.getDayOfMonth())));
    }

    @Test
    public void shouldParseLocalDate(){
        String newYearsEveAsString = "2017-12-31";
//        LocalDate newYearsEve = LocalDate.parse(newYearsEveAsString); // uses the default formatter: DateTimeFormatter.ISO_DATE
        LocalDate newYearsEve = LocalDate.parse(newYearsEveAsString, DateTimeFormatter.ISO_DATE);
        assertThat(newYearsEve.getYear(), is(equalTo(2017)));
        assertThat(newYearsEve.getMonth(), is(equalTo(Month.DECEMBER)));
        assertThat(newYearsEve.getDayOfMonth(), is(equalTo(31)));
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldThrowParseException(){
        String newYearsEveAsString = "2017-12-31";
        /*LocalDate newYearsEve = */ LocalDate.parse(newYearsEveAsString, DateTimeFormatter.ISO_TIME);
    }

    @Test
    public void shouldParseLocalTime(){
        String midnightAsString = "00:00";
        LocalTime midnight = LocalTime.parse(midnightAsString, DateTimeFormatter.ISO_TIME);
        assertThat(midnight.getHour(), is(equalTo(0)));
        assertThat(midnight.getMinute(), is(equalTo(0)));
        assertThat(midnight.getSecond(), is(equalTo(0)));
    }

    @Test
    public void shouldCreateLocalDateTimeNewYearsEve(){
        LocalDateTime newYearsEve = LocalDateTime.of(2017, Month.DECEMBER, 31, 0, 0, 0);
        assertThat(newYearsEve.getYear(), is(equalTo(2017)));
        assertThat(newYearsEve.getMonth(), is(equalTo(Month.DECEMBER)));
        assertThat(newYearsEve.getDayOfMonth(), is(equalTo(31)));
        assertThat(newYearsEve.getHour(), is(equalTo(0)));
        assertThat(newYearsEve.getMinute(), is(equalTo(0)));
        assertThat(newYearsEve.getSecond(), is(equalTo(0)));
    }

    @Test
    public void shouldCreateNewYearsInstant(){
        // use https://www.epochconverter.com/ to create the instance
        Instant newYearsEveInstant = Instant.ofEpochMilli(1514678400000L);
        assertNotNull(newYearsEveInstant);
    }

    @Test
    public void shouldCalculateDaysBetween(){
        LocalDate[] dates = DateTime8.getTwoLocalDates();
        // calculate days between dates[0] and dates[1]
        long daysBetween = Period.between(dates[0], dates[1]).getDays();
        assertThat(DateTime8.DAYS_BETWEEN, equalTo(daysBetween));
    }

    @Test
    public void shouldCreateDurationFromTemporalUnit(){
//        Duration duration = Duration.ofSeconds(5);
        Duration duration = Duration.of(5, ChronoUnit.SECONDS);
        long seconds = duration.getSeconds();
        assertThat(5L, equalTo(seconds));
    }

    @Test
    public void shouldCheckIfDurationIsZero(){
        Duration duration = Duration.of(5, ChronoUnit.DAYS);
        boolean isZero = duration.isZero(); // check for the duration if it's zero
        assertThat(false, equalTo(isZero));
    }

    @Test
    public void shouldFormatToString(){
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017();
        String format = newYearsEve.format(DateTimeFormatter.ISO_DATE); // format the string
        assertThat("2017-12-31",equalTo(format));
    }

    @Test
    public void shouldUseWithMethodtoChangeDate() {
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017();
//        LocalDate newYearsEve2018 = newYearsEve.plus(1, ChronoUnit.YEARS); // change the newYearsEve using the with method
        LocalDate newYearsEve2018 = newYearsEve.withYear(2018); // change the newYearsEve using the with method
//        LocalDate firstJanuary = newYearsEve2018.plus(1, ChronoUnit.DAYS);
        LocalDate firstJanuary = newYearsEve2018.plusDays(1);
        assertThat(2018, equalTo(newYearsEve2018.getYear()));
        assertThat(1, equalTo(firstJanuary.getDayOfMonth()));
    }

    @Test
    public void shouldAdjustToNewYearsEve(){
        LocalDate firstOfDecember = LocalDate.of(2017,12,1);
        // write a temporal adjuster to adjust the firstOfDecember to new years eve
//        LocalDate newYearsEve = firstOfDecember.with(temporal -> temporal.plus(30, ChronoUnit.DAYS));
        LocalDate newYearsEve = firstOfDecember.with(TemporalAdjusters.lastDayOfMonth());
        assertThat(newYearsEve.getYear(), is(equalTo(2017)));
        assertThat(newYearsEve.getMonth(), is(equalTo(Month.DECEMBER)));
        assertThat(newYearsEve.getDayOfMonth(), is(equalTo(31)));
    }

    @Test
    public void shouldFindTheNextFriday13th() {
        LocalDate from = LocalDate.of(2017, JANUARY, 1);
        LocalDate expected = LocalDate.of(2017, JANUARY, 13);
        assertEquals(expected, DateTime8.findNextFriday13th(from));
    }

}
