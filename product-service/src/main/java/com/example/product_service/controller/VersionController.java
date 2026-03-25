package com.example.product_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController
{
    @GetMapping("/version")
    public String getVersion()
    {
        return "Product Service v1";
    }
}