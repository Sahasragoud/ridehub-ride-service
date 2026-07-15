package com.ridehub.rideservice.fare.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class FareBreakdownResponse {

    private BigDecimal baseFare;

    private BigDecimal distanceFare;

    private BigDecimal bookingFee;

    private BigDecimal gst;

    private BigDecimal totalFare;

}