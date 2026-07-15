package com.ridehub.rideservice.fare.constant;

import com.ridehub.rideservice.enums.RideType;

import java.math.BigDecimal;
import java.util.Map;

public final class RideTypeMultiplier {

    private RideTypeMultiplier() {}

    public static final Map<RideType, BigDecimal> MULTIPLIERS =
            Map.of(
                    RideType.STANDARD, BigDecimal.valueOf(1.0),
                    RideType.PREMIUM, BigDecimal.valueOf(1.5),
                    RideType.POOL, BigDecimal.valueOf(0.8)
            );
}
