package com.ridehub.rideservice.fare.constant;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class FareBreakdown {

    private BigDecimal baseFare;

    private BigDecimal distanceFare;

    private BigDecimal bookingFee;

    private BigDecimal gst;

    private BigDecimal totalFare;
}