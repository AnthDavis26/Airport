package com.solvd.airport.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("MM/dd/yyyy+HH:mm:ss"));
    }

    @Override
    public String marshal(LocalDateTime localDateTime) {
        String dateString = new DateAdapter().marshal(localDateTime.toLocalDate());
        String hour = "";
        String minute = "";
        String second = "";

        int temp = localDateTime.getHour();

        if (temp < 10)
            hour += '0';

        hour += temp;
        temp = localDateTime.getMinute();

        if (temp < 10)
            minute += '0';

        minute += temp;
        temp = localDateTime.getSecond();

        if (temp < 10)
            second += "0";

        second += temp;

        return dateString + "+" + hour + ":" + minute + ":" + second;
    }
}
