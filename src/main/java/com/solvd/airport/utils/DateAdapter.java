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
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.getMonthValue() + "/" +
                localDate.getDayOfMonth() + "/" + localDate.getYear();
    }
}
