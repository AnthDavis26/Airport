package com.solvd.airport.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return new SimpleDateFormat("MM/dd/yyyy")
                .parse(s).toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public String marshal(LocalDate localDate) {
        String day = "";
        String month = "";
        String year = "";
        int temp = localDate.getMonthValue();

        if (temp < 10)
            day += '0';

        day += temp;
        temp = localDate.getDayOfMonth();

        if (temp < 10)
            month += '0';

        month += temp;
        temp = localDate.getYear();

        if (temp < 10)
            year += "000";
        else if (temp < 100)
            year += "00";
        else if (temp < 1000)
            year += "0";

        year += temp;

        return day + "/" + month + "/" + year;
    }
}
