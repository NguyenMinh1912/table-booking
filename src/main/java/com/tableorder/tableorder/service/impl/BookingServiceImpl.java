package com.tableorder.tableorder.service.impl;

import com.tableorder.tableorder.entity.BookingHourEntity;
import com.tableorder.tableorder.entity.BookingInfoEntity;
import com.tableorder.tableorder.entity.UserEntity;
import com.tableorder.tableorder.exceptions.EntityConfligException;
import com.tableorder.tableorder.exceptions.EntityNotFoundException;
import com.tableorder.tableorder.mapper.BookingInfoMapper;
import com.tableorder.tableorder.model.BookingHourModel;
import com.tableorder.tableorder.model.BookingInfoModel;
import com.tableorder.tableorder.repository.BookingHourRepository;
import com.tableorder.tableorder.repository.BookingInfoRepository;
import com.tableorder.tableorder.repository.UserRepository;
import com.tableorder.tableorder.service.BookingService;
import com.tableorder.tableorder.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingInfoRepository bookingInfoRepository;
    private final UserRepository userRepository;
    private final BookingHourRepository bookingHourRepository;
    private final Validator validator;
    private final BookingInfoMapper bookingInfoMapper;

    private final DateUtils dateUtils;

    public BookingServiceImpl(BookingInfoRepository bookingInfoRepository, UserRepository userRepository, BookingHourRepository bookingHourRepository, Validator validator, BookingInfoMapper bookingInfoMapper, DateUtils dateUtils) {
        this.bookingInfoRepository = bookingInfoRepository;
        this.userRepository = userRepository;
        this.bookingHourRepository = bookingHourRepository;
        this.validator = validator;
        this.bookingInfoMapper = bookingInfoMapper;
        this.dateUtils = dateUtils;
    }

    @Override
    public BookingInfoModel bookingTable(BookingInfoModel bookingInfoModel) {
        Set<ConstraintViolation<BookingInfoModel>> violations = validator.validate(bookingInfoModel);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<BookingInfoModel> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }

        Long userId = bookingInfoModel.getUserId();
        Long bookingHourId = bookingInfoModel.getBookingHourId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        BookingHourEntity bookingHourEntity = bookingHourRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Booking hour not found"));


        Optional<BookingInfoEntity> bookingInfoEntity
                = bookingInfoRepository.findOneByBookingDateAndBookingHourId(bookingInfoModel.getBookingDate(),
                bookingHourId);

        if (bookingInfoEntity.isPresent()) {
            throw new EntityConfligException("This booking hour is unavailable");
        }

        BookingInfoEntity newBookingInfo = new BookingInfoEntity();
        newBookingInfo.setBookingDate(bookingInfoModel.getBookingDate());
        newBookingInfo.setUser(userEntity);
        newBookingInfo.setBookingHourEntity(bookingHourEntity);
        bookingInfoRepository.save(newBookingInfo);

        return bookingInfoMapper.entityToModel(newBookingInfo);
    }

    @Override
    public List<BookingHourModel> getAvailableBookingTimeByDate(String date) {
        LocalTime localDate = dateUtils.parseLocalDate(date);
        List<BookingHourEntity> bookingHourEntities = bookingHourRepository.getAvailableBookingTimeByDate(localDate);
        List<BookingHourModel> bookingHourModels = new ArrayList<>();
        for (BookingHourEntity bookingHourEntity : bookingHourEntities) {
            BookingHourModel bookingHourModel = new BookingHourModel();
            bookingHourModel.setBookingTime(bookingHourEntity.getBookingTime());
            bookingHourModel.setId(bookingHourEntity.getId());
        }
        return bookingHourModels;
    }
}
