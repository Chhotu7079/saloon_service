package com.saloon.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saloon.domain.BookingStatus;
import com.saloon.dto.BookingDTO;
import com.saloon.dto.BookingRequest;
import com.saloon.dto.BookingSlotDTO;
import com.saloon.dto.SaloonDTO;
import com.saloon.dto.ServiceDTO;
import com.saloon.dto.UserDTO;
import com.saloon.mapper.BookingMapper;
import com.saloon.modal.Booking;
import com.saloon.modal.SaloonReport;
import com.saloon.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long saloonId, @RequestBody BookingRequest bookingRequest) throws Exception{


        UserDTO user = new UserDTO();
        user.setId(1L);

        SaloonDTO saloon = new SaloonDTO();
        saloon.setId(saloonId);
        saloon.setOpenTime(LocalTime.now());
        saloon.setCloseTime(LocalTime.now().plusHours(12));

        Set<ServiceDTO> serviceDTOSet = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(499);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Hair cut for men");

        serviceDTOSet.add(serviceDTO);

        Booking booking = bookingService.createBooking(bookingRequest, user, saloon, serviceDTOSet);
        

        return ResponseEntity.ok(booking);
    }



    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(){
        UserDTO user = new UserDTO();
        user.setId(1L);

        List<Booking> bookings = bookingService.getBookingsByCustomer(1L);


        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/saloon")
    public ResponseEntity<Set<BookingDTO>> getBookingBySaloon(){

        List<Booking> bookings = bookingService.getBookingBySaloon(1L);


        return ResponseEntity.ok(getBookingDTOs(bookings));
    }


    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings){
        return bookings.stream().map(booking -> {
            return BookingMapper.toDTO(booking);
        }).collect(Collectors.toSet());
    }


    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) throws Exception{

        Booking booking = bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
        @PathVariable Long bookingId,
        @RequestParam BookingStatus status
        ) throws Exception{

        Booking booking = bookingService.updateBooking(bookingId, status);
        
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @GetMapping("/slots/saloon/{saloonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(@PathVariable Long saloonId, @RequestParam(required = false) LocalDate date) throws Exception{

        List<Booking> bookings = bookingService.getBookingsByDate(date, saloonId);

        List<BookingSlotDTO> slotDTOs = bookings.stream()
            .map(booking -> {
                BookingSlotDTO slotDTO = new BookingSlotDTO();
                slotDTO.setStartTime(booking.getStartTime());
                slotDTO.setEndTime(booking.getEndTime());
                return slotDTO;
            }).collect(Collectors.toList());

        return ResponseEntity.ok(slotDTOs);
    }

    @GetMapping("/report")
   public ResponseEntity<SaloonReport> getSaloonReport(){
    SaloonReport report = bookingService.getSaloonReport(1L);
    return ResponseEntity.ok(report);
   }



}
