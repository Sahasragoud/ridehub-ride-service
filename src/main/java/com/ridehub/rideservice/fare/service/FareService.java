package com.ridehub.rideservice.fare.service;

import com.ridehub.rideservice.enums.RideType;
import com.ridehub.rideservice.fare.constant.FareBreakdown;

public interface FareService {

    FareBreakdown calculateEstimatedFare(
            double distanceKm,
            RideType rideType
    );
}