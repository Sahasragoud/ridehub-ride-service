package com.ridehub.rideservice.fare.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

@Service
public class SurgePricingServiceImpl
        implements SurgePricingService {

    @Override
    public BigDecimal getSurgeMultiplier() {

        LocalTime now = LocalTime.now();

        // Morning Rush
        if (now.isAfter(LocalTime.of(8, 0))
                && now.isBefore(LocalTime.of(10, 0))) {

            return BigDecimal.valueOf(1.5);
        }

        // Evening Rush
        if (now.isAfter(LocalTime.of(17, 0))
                && now.isBefore(LocalTime.of(20, 0))) {

            return BigDecimal.valueOf(1.8);
        }

        return BigDecimal.ONE;
    }
}