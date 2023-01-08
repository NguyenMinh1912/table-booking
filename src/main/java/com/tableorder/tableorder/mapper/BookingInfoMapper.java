package com.tableorder.tableorder.mapper;

import com.tableorder.tableorder.entity.BookingInfoEntity;
import com.tableorder.tableorder.model.BookingInfoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingInfoMapper {


    @Mapping(source = "bookingHourEntity.bookingTime", target = "bookingTime")
    @Mapping(source = "user.id", target = "userId")
    BookingInfoModel entityToModel(BookingInfoEntity bookingInfoEntity);
}
