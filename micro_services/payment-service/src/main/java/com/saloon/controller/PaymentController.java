package com.saloon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saloon.payload.BookingDTO;
import com.saloon.payload.UserDTO;
import com.saloon.payload.response.PaymentLinkResponse;
import com.saloon.service.PaymentService;
import com.saloon.domain.PaymentMethod;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/create")
  public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDTO booking, @RequestParam PaymentMethod paymentMethod) throws Exception{
    UserDTO user = new UserDTO();
    user.setFullName("Abhi");
    user.setEmail("abhi@gmail.com");
    user.setId(1L);

    PaymentLinkResponse res = paymentService.createOrder(user, booking, paymentMethod);
    return ResponseEntity.ok(res);
  }

}
