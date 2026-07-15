package com.ridehub.rideservice.fare;

public interface FareService {

    FareBreakdown calculateEstimatedFare(double distanceKm);

}