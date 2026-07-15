package com.ridehub.rideservice.service.interfaces;

import com.ridehub.rideservice.dto.request.RideRequest;
import com.ridehub.rideservice.dto.response.RideResponse;

public interface RideService {

    RideResponse requestRide(Long riderId, RideRequest request);

    RideResponse getRideById(Long rideId);

    RideResponse cancelRide(Long riderId, Long rideId);

    RideResponse acceptRide(
            Long rideId,
            String token);
}