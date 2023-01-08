package com.tableorder.tableorder.service;

import com.tableorder.tableorder.model.BookingHourModel;
import com.tableorder.tableorder.model.BookingInfoModel;

import java.util.List;

public interface BookingService {

    BookingInfoModel bookingTable(BookingInfoModel bookingInfoModel);

    List<BookingHourModel> getAvailableBookingTimeByDate(String date);
}
