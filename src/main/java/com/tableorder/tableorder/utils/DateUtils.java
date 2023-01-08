package com.tableorder.tableorder.utils;

import com.tableorder.tableorder.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateUtils {
    private final DateTimeFormatter TIME_FOMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter DATE_FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalTime parseLocalTime(String time){
        try {
            LocalTime result = LocalTime.parse(time, TIME_FOMATTER);
            return result;
        }catch (DateTimeParseException e){
            throw new ValidationException("Date time string is not match pattern HH:mm");
        }

    }

    public LocalTime parseLocalDate(String time){
        try {
            LocalTime result = LocalTime.parse(time, DATE_FOMATTER);
            return result;
        }catch (DateTimeParseException e){
            throw new ValidationException("Date time string is not match pattern yyyy-MM-dd");
        }

    }

}
