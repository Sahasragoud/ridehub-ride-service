package com.ridehub.rideservice.coupon.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CouponValidationResponse {

    private boolean valid;

    private String message;

    private BigDecimal discount;

    private BigDecimal finalFare;
}