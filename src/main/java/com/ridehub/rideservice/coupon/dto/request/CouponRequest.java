package com.ridehub.rideservice.coupon.dto.request;

import com.ridehub.rideservice.coupon.enums.CouponType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CouponRequest {

    @NotBlank
    private String code;

    @NotNull
    private CouponType type;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal value;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal minimumRideAmount;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal maximumDiscount;

    @NotNull
    private LocalDateTime expiryDate;

}