package com.saloon.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saloon.domain.BookingStatus;
import com.saloon.dto.BookingRequest;
import com.saloon.dto.SaloonDTO;
import com.saloon.dto.ServiceDTO;
import com.saloon.dto.UserDTO;
import com.saloon.modal.Booking;
import com.saloon.modal.SaloonReport;
import com.saloon.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDTO user, SaloonDTO saloon, Set<ServiceDTO> serviceDTOSet) throws Exception {
        int totalDuration = serviceDTOSet.stream()
                            .mapToInt(ServiceDTO::getDuration)
                            .sum();

        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable = isTimeSlotAvailable(saloon, bookingStartTime, bookingEndTime);
       
        int totalPrice = serviceDTOSet.stream().mapToInt(ServiceDTO::getPrice).sum();

        Set<Long> idList = serviceDTOSet.stream().map(ServiceDTO::getId).collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSaloonId(saloon.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);

        return bookingRepository.save(newBooking);


    }

    public Boolean isTimeSlotAvailable(SaloonDTO saloonDTO, 
                                        LocalDateTime bookingStartTime, 
                                        LocalDateTime bookingEndTime) throws Exception{

        List<Booking> existingBookings = getBookingBySaloon(saloonDTO.getId());

        LocalDateTime saloonOpenTime = saloonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime saloonCloseTime = saloonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());

        if (bookingStartTime.isBefore(saloonOpenTime) || bookingEndTime.isAfter(saloonCloseTime)) {
            throw new Exception("Booking time must be with in saloon's working hours");
        }

        for(Booking existingBooking : existingBookings){
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

            if (bookingStartTime.isBefore(existingBookingEndTime)
                && bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("slot not available, choose different time.");
            }

            if (bookingStartTime.isEqual(existingBookingStartTime)
                || bookingEndTime.isEqual(existingBookingEndTime)) {
                    throw new Exception("slot not available, choose different time.");
            }
        }

        return true;
    }


    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySaloon(Long saloonId) {
        return bookingRepository.findBySaloonId(saloonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking==null) {
            throw new Exception("booking not found");
        }
        return booking;

    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(status);
        return bookingRepository.save(booking);

    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long saloonId) {
        List<Booking> allBookings = getBookingBySaloon(saloonId);

        if (date==null) {
            return allBookings;
        }

        return allBookings.stream().filter(booking -> 
                    isSameDate(booking.getStartTime(), date) 
                    || isSameDate(booking.getEndTime(),date))
                    .collect(Collectors.toList());

    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date){
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SaloonReport getSaloonReport(Long saloonId) {
        List<Booking> bookings = getBookingBySaloon(saloonId);

        int totalEarnings = bookings.stream().mapToInt(Booking :: getTotalPrice).sum();

        Integer totalBooking = bookings.size();

        List<Booking> cancelledBookings = bookings.stream().filter(booking ->
            booking.getStatus().equals(BookingStatus.CANCELLED)).collect(Collectors.toList());

        Double totalRefund = cancelledBookings.stream().mapToDouble(Booking::getTotalPrice).sum();

        SaloonReport report = new SaloonReport();
        report.setSaloonId(saloonId);
        report.setCancelledBookings(cancelledBookings.size());
        report.setTotalBookings(totalBooking);
        report.setTotalEarnings(totalEarnings);
        report.setTotalRefund(totalRefund);

        return report;
    }

}
