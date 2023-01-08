package com.tableorder.tableorder.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingInfoModel {
    private Long id;
    @NotNull(message = "booking date can't be null")
    private LocalDate bookingDate;
    @NotNull(message = "booking hour can't be null")
    private Long bookingHourId;
    @NotNull(message = "userId can't be null")
    private Long userId;

    private LocalTime bookingTime;
    private String note;
}
