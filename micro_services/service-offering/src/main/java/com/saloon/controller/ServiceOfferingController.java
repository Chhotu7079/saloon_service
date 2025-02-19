package com.saloon.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saloon.modal.ServiceOffering;
import com.saloon.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/saloon/{saloonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySaloonId(@PathVariable Long saloonId,
            @RequestParam(required = false) Long categoryId) {

        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServiceBySaloonId(saloonId, categoryId);

        return ResponseEntity.ok(serviceOfferings);
    }

    @GetMapping("{id}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long id) throws Exception {

        ServiceOffering serviceOffering = serviceOfferingService.getServiceById(id);

        return ResponseEntity.ok(serviceOffering);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(@PathVariable Set<Long> ids) {

        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getServiceByIds(ids);

        return ResponseEntity.ok(serviceOfferings);
    }

}
