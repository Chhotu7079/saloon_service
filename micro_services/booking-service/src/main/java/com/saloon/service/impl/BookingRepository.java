package com.saloon.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saloon.modal.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

    List<Booking> findByCustomerId(Long customerId);
    List<Booking> findBySaloonId(Long saloonId);

}
