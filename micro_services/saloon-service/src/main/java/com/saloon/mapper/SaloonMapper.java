package com.saloon.mapper;

import com.saloon.modal.Saloon;
import com.saloon.payload.dto.SaloonDTO;

public class SaloonMapper {
    public static SaloonDTO mapToDTO(Saloon saloon){
        SaloonDTO saloonDTO = new SaloonDTO();
        saloonDTO.setId(saloon.getId());
        saloonDTO.setName(saloon.getName());
        saloonDTO.setAddress(saloon.getAddress());
        saloonDTO.setCity(saloon.getCity());
        saloonDTO.setImages(saloon.getImages());
        saloonDTO.setOpenTime(saloon.getOpenTime());
        saloonDTO.setCloseTime(saloon.getCloseTime());
        saloonDTO.setPhoneNumber(saloon.getPhoneNumber());
        saloonDTO.setOwnerId(saloon.getOwnerId());
        saloonDTO.setEmail(saloon.getEmail());
        return saloonDTO;
    }

}
