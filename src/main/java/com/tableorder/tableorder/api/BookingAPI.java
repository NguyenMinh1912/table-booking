package com.tableorder.tableorder.api;

import com.tableorder.tableorder.model.BookingHourModel;
import com.tableorder.tableorder.model.BookingInfoModel;
import com.tableorder.tableorder.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
public class BookingAPI {

    private final BookingService bookingService;

    public BookingAPI(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingInfoModel bookingTable(
            @RequestBody BookingInfoModel bookingInfoModel
    ){
        return bookingService.bookingTable(bookingInfoModel);
    }

    @GetMapping("{date}/availableBookingTime")
    public List<BookingHourModel> getAvailableBookingTimeByDate(
            @PathVariable("date") String date
    ){
        return bookingService.getAvailableBookingTimeByDate(date);
    }
}
