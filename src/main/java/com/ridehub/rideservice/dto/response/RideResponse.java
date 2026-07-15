package com.ridehub.rideservice.dto.response;

import com.ridehub.rideservice.enums.PaymentStatus;
import com.ridehub.rideservice.enums.RideStatus;
import com.ridehub.rideservice.enums.RideType;
import com.ridehub.rideservice.fare.dto.FareBreakdownResponse;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class RideResponse {

    private Long id;

    private Long riderId;

    private Long driverId;

    private Double pickupLatitude;

    private Double pickupLongitude;

    private Double dropLatitude;

    private Double dropLongitude;

    private String pickupAddress;

    private String dropAddress;

    private RideStatus rideStatus;

    private RideType rideType;

    private PaymentStatus paymentStatus;

    private BigDecimal estimatedFare;

    private BigDecimal actualFare;

    private LocalDateTime requestedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    private FareBreakdownResponse fareBreakdown;
}