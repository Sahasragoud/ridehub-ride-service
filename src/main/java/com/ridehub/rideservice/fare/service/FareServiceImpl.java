package com.ridehub.rideservice.fare.service;

import com.ridehub.rideservice.enums.RideType;
import com.ridehub.rideservice.fare.constant.FareBreakdown;
import com.ridehub.rideservice.fare.constant.FareConstants;
import com.ridehub.rideservice.fare.constant.RideTypeMultiplier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FareServiceImpl implements FareService {

    @Override
    public FareBreakdown calculateEstimatedFare(
            double distanceKm,
            RideType rideType){

        BigDecimal distanceFare =
                FareConstants.PER_KM_RATE
                        .multiply(BigDecimal.valueOf(distanceKm));

        BigDecimal subtotal =
                FareConstants.BASE_FARE
                        .add(distanceFare)
                        .add(FareConstants.BOOKING_FEE)
                        .multiply(rideType.getMultiplier());

        BigDecimal gst =
                subtotal.multiply(FareConstants.GST_PERCENT)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        BigDecimal total = subtotal.add(gst);

        if (total.compareTo(FareConstants.MINIMUM_FARE) < 0) {
            total = FareConstants.MINIMUM_FARE;
        }

        return FareBreakdown.builder()
                .baseFare(FareConstants.BASE_FARE)
                .distanceFare(distanceFare)
                .bookingFee(FareConstants.BOOKING_FEE)
                .gst(gst)
                .totalFare(total)
                .build();
    }
}