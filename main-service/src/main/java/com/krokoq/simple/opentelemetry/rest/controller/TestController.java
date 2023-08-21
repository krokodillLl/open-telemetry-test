package com.krokoq.simple.opentelemetry.rest.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @SneakyThrows
    @GetMapping("/quickly")
    public String getQuicklyResponse() {
        Thread.sleep(5);
        return "Quickly response from service 1";
    }

    @SneakyThrows
    @GetMapping("/normal")
    public String getNormalResponse() {
        Thread.sleep(500);
        return "Normal response from service 1";
    }

    @SneakyThrows
    @GetMapping("/slowly")
    public String getSlowlyResponse() {
        Thread.sleep(5000);
        return "Slowly response from service 1";
    }

    @GetMapping("/error")
    public String getErrorResponse() {
        throw new RuntimeException("Exception from service 1");
    }
}
