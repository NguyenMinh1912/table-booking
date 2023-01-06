package com.tableorder.tableorder.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BOOKING_HOUR")
public class BookingHourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true, columnDefinition = "TIME")
    private LocalTime localTime;

}
