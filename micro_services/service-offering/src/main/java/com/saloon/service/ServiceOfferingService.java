package com.saloon.service;

import java.util.Set;

import com.saloon.dto.CategoryDTO;
import com.saloon.dto.SaloonDTO;
import com.saloon.dto.ServiceDTO;
import com.saloon.modal.ServiceOffering;

public interface ServiceOfferingService {

    ServiceOffering creatService(SaloonDTO saloonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;

    Set<ServiceOffering> getAllServiceBySaloonId(Long saloonId, Long categoryId);

    Set<ServiceOffering> getServiceByIds(Set<Long> ids);

    ServiceOffering getServiceById(Long id) throws Exception;

}
