package com.tableorder.tableorder.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BOOKING_INFO", uniqueConstraints = {
        @UniqueConstraint(name = "booking_hour_user_uk", columnNames = {"user_id", "booking_hour_id"})
})
public class BookingInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "booking_hour_id")
    private BookingHourEntity bookingHourEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String node;
}
