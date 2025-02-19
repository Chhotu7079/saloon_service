package com.saloon.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.saloon.domain.BookingStatus;

import lombok.Data;

@Data
public class BookingDTO {

    private Long id;

    private Long saloonId;

    private Long customerId;

    private LocalDateTime starTime;

    private LocalDateTime endTime;

    private Set<Long> serviceIds;

    private BookingStatus status = BookingStatus.PENDING;



}
