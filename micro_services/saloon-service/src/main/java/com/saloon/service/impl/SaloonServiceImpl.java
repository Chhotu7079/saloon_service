package com.saloon.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saloon.modal.Saloon;
import com.saloon.payload.dto.SaloonDTO;
import com.saloon.payload.dto.UserDTO;
import com.saloon.repository.SaloonRepository;
import com.saloon.service.SaloonService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SaloonServiceImpl implements SaloonService{

    private final SaloonRepository saloonRepository;

    @Override
    public Saloon createSaloon(SaloonDTO req, UserDTO user) {
        Saloon saloon = new Saloon();
        saloon.setName(req.getName());
        saloon.setAddress(req.getAddress());
        saloon.setEmail(req.getEmail());
        saloon.setCity(req.getCity());
        saloon.setImages(req.getImages());
        saloon.setOwnerId(user.getId());
        saloon.setOpenTime(req.getOpenTime());
        saloon.setCloseTime(req.getCloseTime());
        saloon.setPhoneNumber(req.getPhoneNumber());

        return saloonRepository.save(saloon);

    }

    @Override
    public Saloon updateSaloon(SaloonDTO saloon, UserDTO user, Long saloonId) throws Exception {
        Saloon existingSaloon = saloonRepository.findById(saloonId).orElse(null);
       //user whon want to trying to update the salooon is owner of this salonn ony owner of this saoon wi update the saloon
        if (existingSaloon != null && saloon.getOwnerId().equals(user.getId())) {
            existingSaloon.setCity(saloon.getCity());
            existingSaloon.setName(saloon.getName());
            existingSaloon.setAddress(saloon.getAddress());
            existingSaloon.setEmail(saloon.getEmail());
            existingSaloon.setImages(saloon.getImages());
            existingSaloon.setOpenTime(saloon.getOpenTime());
            existingSaloon.setCloseTime(saloon.getCloseTime());
            existingSaloon.setOwnerId(user.getId());
            existingSaloon.setPhoneNumber(saloon.getPhoneNumber());

            return saloonRepository.save(existingSaloon);
        }
        throw new Exception("saloon not exist");
    }

    @Override
    public List<Saloon> getAllSaloons() {
        return saloonRepository.findAll();
    }

    @Override
    public Saloon getSaloonById(Long SaloonId) throws Exception {
        Saloon saloon = saloonRepository.findById(SaloonId).orElse(null);
        if (saloon==null) {
            throw new Exception("saloon not exist");
        }
        return saloon;
    }

    @Override
    public Saloon getSaloonByOwnerId(Long ownerId) {
        return saloonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Saloon> searchSaloonByCity(String city) {
        return saloonRepository.searchSaloons(city);
    }

}
