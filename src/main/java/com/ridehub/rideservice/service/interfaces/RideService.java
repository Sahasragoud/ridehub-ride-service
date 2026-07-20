package com.ridehub.rideservice.service.interfaces;

import com.ridehub.rideservice.dto.request.RideRequest;
import com.ridehub.rideservice.dto.response.RideResponse;
import com.ridehub.rideservice.enums.PaymentStatus;

import java.util.List;

public interface RideService {

    RideResponse requestRide(Long riderId, RideRequest request);

    RideResponse getRideById(Long rideId);

    RideResponse cancelRide(Long riderId, Long rideId);

    RideResponse acceptRide(
            Long rideId,
            String token);

    List<RideResponse> getMyRides(Long riderId);

    RideResponse arriveAtPickup(
            Long rideId,
            String token);

    RideResponse startRide(Long rideId, String token);

    RideResponse completeRide(Long rideId, String token);

    RideResponse updatePaymentStatus(
            Long rideId,
            PaymentStatus paymentStatus
    );
}