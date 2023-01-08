package com.tableorder.tableorder.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class BookingHourModel {
    private Long id;
    private LocalTime bookingTime;
}
