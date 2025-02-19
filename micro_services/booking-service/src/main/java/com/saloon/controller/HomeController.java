package com.saloon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String HomeControllerHandler(){
        return "booking microservice for saloon booking services";
    }

}
