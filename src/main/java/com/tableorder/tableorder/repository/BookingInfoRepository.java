package com.tableorder.tableorder.repository;

import com.tableorder.tableorder.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingInfoRepository extends JpaRepository<BookingInfoEntity, Long> {

    @Query("SELECT bki FROM BookingInfoEntity bki " +
            "WHERE bki.bookingDate = :bookingDate " +
            "AND bki.bookingHourEntity.id = :bookingHourId")
    Optional<BookingInfoEntity> findOneByBookingDateAndBookingHourId(
            @Param(value = "bookingDate") LocalDate bookingDate,
            @Param(value = "bookingHourId") Long bookingHourId
    );
}
