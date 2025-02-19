package com.saloon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saloon.dto.CategoryDTO;
import com.saloon.dto.SaloonDTO;
import com.saloon.dto.ServiceDTO;
import com.saloon.modal.ServiceOffering;
import com.saloon.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service-offering/saloon-owner")
public class SaloonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDTO serviceDTO) {

        SaloonDTO saloonDTO = new SaloonDTO();
        saloonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategory());

        ServiceOffering serviceOfferings = serviceOfferingService.creatService(saloonDTO, serviceDTO, categoryDTO);

        return ResponseEntity.ok(serviceOfferings);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long id,
            @RequestBody ServiceOffering serviceOffering) throws Exception {

        ServiceOffering serviceOfferings = serviceOfferingService.updateService(id, serviceOffering);

        return ResponseEntity.ok(serviceOfferings);
    }

}
