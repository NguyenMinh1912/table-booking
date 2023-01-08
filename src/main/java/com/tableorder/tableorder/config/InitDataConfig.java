package com.tableorder.tableorder.config;

import com.tableorder.tableorder.entity.BookingHourEntity;
import com.tableorder.tableorder.entity.Role;
import com.tableorder.tableorder.entity.UserEntity;
import com.tableorder.tableorder.repository.BookingHourRepository;
import com.tableorder.tableorder.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Arrays;

@Component
public class InitDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookingHourRepository bookingHourRepository;

    public InitDataConfig(UserRepository userRepository, BookingHourRepository bookingHourRepository) {
        this.userRepository = userRepository;
        this.bookingHourRepository = bookingHourRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = UserEntity.builder()
                .username("user")
                .name("User")
                .email("user@gmail.com")
                .password("user")
                .roles(Arrays.asList(Role.GUEST))
                .build();

        UserEntity admin = UserEntity.builder()
                .username("admin")
                .password("admin")
                .roles(Arrays.asList(Role.ADMIN))
                .build();

        userRepository.save(user);
        userRepository.save(admin);


        BookingHourEntity bookingHourEntity1 = BookingHourEntity.builder()
                .bookingTime(LocalTime.of(8,0,0))
                .build();

        BookingHourEntity bookingHourEntity2 = BookingHourEntity.builder()
                .bookingTime(LocalTime.of(10,0,0))
                .build();

        BookingHourEntity bookingHourEntity3 = BookingHourEntity.builder()
                .bookingTime(LocalTime.of(12,0,0))
                .build();

        bookingHourRepository.save(bookingHourEntity1);
        bookingHourRepository.save(bookingHourEntity2);
        bookingHourRepository.save(bookingHourEntity3);
    }


}
