package com.ticketflow.customerservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {

    @Value("${app.message}")
    private String message;

    @GetMapping("/api/config/message")
    public String getMessage() {
        return message;
    }
}