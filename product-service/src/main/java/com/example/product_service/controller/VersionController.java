package com.example.product_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController
{
    @Value("${APP_VERSION:unknown}")
    private String appVersion;

    @GetMapping("/version")
    public String getVersion()
    {
        return "Product Service " + appVersion;
    }
}