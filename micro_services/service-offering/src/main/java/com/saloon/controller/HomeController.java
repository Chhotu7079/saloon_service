package com.saloon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String HomeControllerHandler(){
        return "service offering microservice for saloon booking services";
    }

}
