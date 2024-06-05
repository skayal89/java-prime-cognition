package com.javastreams;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DateRelated {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public LocalDateTime toDate(String date){
        return LocalDateTime.parse(date, formatter);
    }

    public ZonedDateTime toDateWithZone(String date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzzz");
        return ZonedDateTime.parse(date, format);
    }

    public ZonedDateTime nowWithZone(String zone){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV");
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of(zone));
        String stringDate = date.format(format);
        System.out.println(stringDate);
        return date;
    }

    public Collection<Long> allLeapYears(long year1, long year2){
        List<Long> leapYears = new ArrayList<>();
        for(long i = year1; i <= year2; i++){
            if(Year.isLeap(i))  leapYears.add(i); // this is from java.time.Year package
        }
        return leapYears;
    }

    public void daysBetweenTwoDates(){
        LocalDate dateBefore = LocalDate.of(2023, 1, 1);
        LocalDate dateAfter = LocalDate.of(2023, Month.FEBRUARY, 5);

        long days = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        long month = ChronoUnit.MONTHS.between(dateBefore, dateAfter);
        long years = ChronoUnit.YEARS.between(dateBefore, dateAfter);

        System.out.println(years + " years "+ month + " months "+days+" days between "+dateBefore+" and "+dateAfter);
    }

    public void daysBetweenTwoDateTimes(){
        LocalDateTime dateBefore = LocalDateTime.of(2023, 1, 1, 13, 30, 10);
        LocalDateTime dateAfter = LocalDateTime.of(2022, Month.FEBRUARY, 5, 8, 10, 15);

        long days = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        long month = ChronoUnit.MONTHS.between(dateBefore, dateAfter);
        long years = ChronoUnit.YEARS.between(dateBefore, dateAfter);

        long minutes = ChronoUnit.MINUTES.between(dateBefore, dateAfter);
        long hours = ChronoUnit.HOURS.between(dateBefore, dateAfter);

        System.out.println(years + " years "+ month + " months "+days+" days between "+dateBefore+" and "+dateAfter);
    }

    public boolean checkIfDateIsLeapYear(){
        LocalDate dateBefore = LocalDate.of(2008, 1, 1);
        return dateBefore.isLeapYear();
    }

    public void getPastDate(){
        LocalDate date = LocalDate.of(2023, 1, 20);

        LocalDate minus10Days = date.minusDays(10);
        System.out.println(minus10Days);

        LocalDate minus2Months = date.minusMonths(2);
        System.out.println(minus2Months);

        LocalDate minus3Years = date.minusYears(3);
        System.out.println(minus3Years);

        // no change in the original date
        System.out.println(date);
    }

    public void getFutureDate(){
        LocalDate date = LocalDate.of(2008, 2, 29);

        LocalDate plus10Days = date.plusDays(15);
        System.out.println(plus10Days);

        LocalDate plus2Months = date.plusMonths(2);
        System.out.println(plus2Months);

        LocalDate plus3Years = date.plusYears(2);
        System.out.println(plus3Years);

        // no change in the original date
        System.out.println(date);
    }

    public boolean isPastDate(){
        LocalDate date = LocalDate.of(2008, 2, 29);
        LocalDate dateBefore = LocalDate.of(2008, 2, 25);

        return date.isAfter(dateBefore);
    }

    public boolean isFutureDate(){
        LocalDate date = LocalDate.of(2008, 2, 28);
        LocalDate dateAfter = LocalDate.of(2008, 2, 29);

        return date.isBefore(dateAfter);
    }

    public boolean isEqualDate(){
        String dt = "2008-02-29 13:05:15";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dt, dtf);
        LocalDateTime dateTime2 = LocalDateTime.parse("2008-02-29 07:15:20", dtf);

        // convert LocalDateTime to LocalDate
        LocalDate date = dateTime.toLocalDate(); // discarding the time part to check if it's the same date
        LocalDate date2 = dateTime2.toLocalDate();

        // convert LocalDateTime to LocalTime
        LocalTime time = dateTime.toLocalTime();
        LocalTime time2 = dateTime2.toLocalTime();

        return date.isEqual(date2);
    }

    public static void main(String[] args) {
        DateRelated dateRelated = new DateRelated();
//        System.out.println(dateRelated.toDate("2013-01-03 13:03"));
//        System.out.println(dateRelated.toDateWithZone("2020-11-03 16:40:44 America/Los_Angeles"));
//        System.out.println(dateRelated.nowWithZone("America/Los_Angeles"));
//        System.out.println(dateRelated.localTimeConvertedToOtherZone("America/Los_Angeles"));
//        System.out.println(dateRelated.allLeapYears(2000, 2023));
//        dateRelated.getFutureDate();
        System.out.println(dateRelated.isEqualDate());
    }
}
