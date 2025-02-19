package com.saloon.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saloon.mapper.SaloonMapper;
import com.saloon.modal.Saloon;
import com.saloon.payload.dto.SaloonDTO;
import com.saloon.payload.dto.UserDTO;
import com.saloon.service.SaloonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/saloons")
@RequiredArgsConstructor
public class SaloonController {

    private final SaloonService saloonService;

    // http://localhost:5002/api/saloons
    @PostMapping
    public ResponseEntity<SaloonDTO> createSaloon(@RequestBody SaloonDTO saloonDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Saloon saloon = saloonService.createSaloon(saloonDTO, userDTO);
        SaloonDTO saloonDTO1 = SaloonMapper.mapToDTO(saloon);

        return ResponseEntity.ok(saloonDTO1);
    }

    // http://localhost:5002/api/saloons/{id}
    @PatchMapping("/{saloonId}")
    public ResponseEntity<SaloonDTO> updateSaloon(@PathVariable Long saloonId, @RequestBody SaloonDTO saloonDTO)
            throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Saloon saloon = saloonService.updateSaloon(saloonDTO, userDTO, saloonId);
        SaloonDTO saloonDTO1 = SaloonMapper.mapToDTO(saloon);

        return ResponseEntity.ok(saloonDTO1);
    }

    // http://localhost:5002/api/saloons
    @GetMapping
    public ResponseEntity<List<SaloonDTO>> getAllSaloons() {

        List<Saloon> saloons = saloonService.getAllSaloons();
        List<SaloonDTO> saloonDTOs = saloons.stream().map((saloon) -> {
            SaloonDTO saloonDTO = SaloonMapper.mapToDTO(saloon);
            return saloonDTO;
        }).toList();

        return ResponseEntity.ok(saloonDTOs);
    }

    // http://localhost:5002/api/saloons/{id}
    @GetMapping("/{saloonId}")
    public ResponseEntity<SaloonDTO> getSaloonById(@PathVariable Long saloonId) throws Exception {

        Saloon saloon = saloonService.getSaloonById(saloonId);
        SaloonDTO saloonDTO = SaloonMapper.mapToDTO(saloon);

        return ResponseEntity.ok(saloonDTO);
    }

    // http://localhost:5002/api/saloons/search?city=mumbai
    @GetMapping("/search")
    public ResponseEntity<List<SaloonDTO>> searchSaloons(@RequestParam("city") String city) {
       
        List<Saloon> saloons = saloonService.searchSaloonByCity(city);
        List<SaloonDTO> saloonDTOs = saloons.stream().map((saloon) -> {
            SaloonDTO saloonDTO = SaloonMapper.mapToDTO(saloon);
            return saloonDTO;
        }).toList();

        return ResponseEntity.ok(saloonDTOs);
    }

    //http://localhost:5002/api/saloons/{id}
    // @GetMapping("/{ownerId}")
    // public ResponseEntity<SaloonDTO> getSaloonByOwnerId(@PathVariable Long ownerId) throws Exception {
    //     UserDTO userDTO = new UserDTO();
    //     userDTO.setId(1L);

    //     Saloon saloon = saloonService.getSaloonByOwnerId(userDTO.getId());
    //     SaloonDTO saloonDTO = SaloonMapper.mapToDTO(saloon);

    //     return ResponseEntity.ok(saloonDTO);
    // }

}
