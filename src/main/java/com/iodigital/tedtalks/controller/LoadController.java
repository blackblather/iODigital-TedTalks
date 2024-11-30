package com.iodigital.tedtalks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadController {
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello world";
    }
}
