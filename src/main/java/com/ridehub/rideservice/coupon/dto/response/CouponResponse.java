package com.ridehub.rideservice.coupon.dto.response;

import com.ridehub.rideservice.coupon.enums.CouponType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class CouponResponse {

    private Long id;

    private String code;

    private CouponType type;

    private BigDecimal value;

    private BigDecimal minimumRideAmount;

    private BigDecimal maximumDiscount;

    private Boolean active;

    private LocalDateTime expiryDate;

    private LocalDateTime createdAt;

}