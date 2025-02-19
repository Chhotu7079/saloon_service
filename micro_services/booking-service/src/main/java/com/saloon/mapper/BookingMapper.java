package com.saloon.mapper;

import com.saloon.dto.BookingDTO;
import com.saloon.modal.Booking;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStarTime(booking.getStartTime());
        bookingDTO.setSaloonId(booking.getSaloonId());
        bookingDTO.setServiceIds(booking.getServiceIds());

        return bookingDTO;
    }

}
