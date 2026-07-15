package com.ridehub.rideservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/api/test/public")
    public String publicEndpoint() {
        return "Public endpoint is working!";
    }

    @GetMapping("/api/test/secure")
    public String secureEndpoint() {
        return "Secure endpoint accessed successfully!";
    }

    @GetMapping("/api/test/me")
    public Map<String, Object> currentUser(Authentication authentication) {

        return Map.of(
                "email", authentication.getName(),
                "authorities", authentication.getAuthorities()
        );
    }
}