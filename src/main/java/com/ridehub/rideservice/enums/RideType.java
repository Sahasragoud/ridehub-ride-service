package com.ridehub.rideservice.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum RideType {

    STANDARD(BigDecimal.ONE),
    PREMIUM(BigDecimal.valueOf(1.5)),
    POOL(BigDecimal.valueOf(0.8));

    private final BigDecimal multiplier;

    RideType(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }
}