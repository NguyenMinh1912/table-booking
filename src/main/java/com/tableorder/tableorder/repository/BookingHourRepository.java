package com.tableorder.tableorder.repository;

import com.tableorder.tableorder.entity.BookingHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface BookingHourRepository extends JpaRepository<BookingHourEntity, Long> {
    @Query("SELECT bh FROM BookingHourEntity bh " +
            "WHERE bh.id NOT IN (" +
            " SELECT bi.bookingHourEntity.id FROM BookingInfoEntity bi" +
            " WHERE bi.bookingDate = :bookingDate" +
            ")")
    List<BookingHourEntity> getAvailableBookingTimeByDate(
            @Param("bookingDate") LocalTime bookingDate);
}
