package com.saloon.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saloon.modal.ServiceOffering;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, Long>{

    Set<ServiceOffering> findBySaloonId(Long saloonId);
}
