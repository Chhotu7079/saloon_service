package com.saloon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.saloon.domain.BookingStatus;
import com.saloon.dto.BookingRequest;
import com.saloon.dto.SaloonDTO;
import com.saloon.dto.ServiceDTO;
import com.saloon.dto.UserDTO;
import com.saloon.modal.Booking;
import com.saloon.modal.SaloonReport;

public interface BookingService {

    Booking createBooking(BookingRequest booking, 
                        UserDTO user, SaloonDTO saloon, 
                        Set<ServiceDTO> serviceDTOSet) throws Exception;

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingBySaloon(Long SaloonId);
    Booking getBookingById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingsByDate(LocalDate date, Long saloonId);
    SaloonReport getSaloonReport(Long saloonId);


}
