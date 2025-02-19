package com.saloon.service;

import java.util.List;

import com.saloon.modal.Saloon;
import com.saloon.payload.dto.SaloonDTO;
import com.saloon.payload.dto.UserDTO;

public interface SaloonService {

    Saloon createSaloon(SaloonDTO saloon, UserDTO user);

    //saloon owner and those user who want to update the saloon is same or not that's why userdto gives
    Saloon updateSaloon(SaloonDTO saloon, UserDTO user, Long saloonId) throws Exception;

    List<Saloon> getAllSaloons();

    Saloon getSaloonById(Long SaloonId) throws Exception;

    Saloon getSaloonByOwnerId(Long ownerId);

    List<Saloon> searchSaloonByCity(String city);


}
