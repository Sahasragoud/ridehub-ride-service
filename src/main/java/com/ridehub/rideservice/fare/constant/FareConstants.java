package com.ridehub.rideservice.fare.constant;

import java.math.BigDecimal;

public final class FareConstants {

    private FareConstants() {
    }

    public static final BigDecimal BASE_FARE =
            BigDecimal.valueOf(50);

    public static final BigDecimal PER_KM_RATE =
            BigDecimal.valueOf(15);

    public static final BigDecimal BOOKING_FEE =
            BigDecimal.valueOf(20);

    public static final BigDecimal GST_PERCENT =
            BigDecimal.valueOf(5);

    public static final BigDecimal MINIMUM_FARE =
            BigDecimal.valueOf(80);
}