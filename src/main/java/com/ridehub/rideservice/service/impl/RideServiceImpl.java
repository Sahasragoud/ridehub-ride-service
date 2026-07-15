package com.ridehub.rideservice.service.impl;

import com.ridehub.rideservice.client.DriverClient;
import com.ridehub.rideservice.client.dto.DriverResponse;
import com.ridehub.rideservice.client.enums.AvailabilityStatus;
import com.ridehub.rideservice.client.enums.DriverStatus;
import com.ridehub.rideservice.dto.request.RideRequest;
import com.ridehub.rideservice.dto.response.RideResponse;
import com.ridehub.rideservice.entity.Ride;
import com.ridehub.rideservice.enums.PaymentStatus;
import com.ridehub.rideservice.enums.RideStatus;
import com.ridehub.rideservice.exception.BadRequestException;
import com.ridehub.rideservice.exception.ResourceNotFoundException;
import com.ridehub.rideservice.repository.RideRepository;
import com.ridehub.rideservice.service.interfaces.RideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final DriverClient driverClient;

    @Override
    public RideResponse requestRide(
            Long riderId,
            RideRequest request) {

        log.info("Ride request received from riderId: {}", riderId);

        Ride ride = Ride.builder()
                .riderId(riderId)
                .pickupLatitude(request.getPickupLatitude())
                .pickupLongitude(request.getPickupLongitude())
                .dropLatitude(request.getDropLatitude())
                .dropLongitude(request.getDropLongitude())
                .pickupAddress(request.getPickupAddress())
                .dropAddress(request.getDropAddress())
                .rideType(request.getRideType())
                .rideStatus(RideStatus.REQUESTED)
                .paymentStatus(PaymentStatus.PENDING)
                .estimatedFare(BigDecimal.ZERO)
                .build();

        Ride savedRide = rideRepository.save(ride);

        log.info("Ride created successfully. Ride ID: {}", savedRide.getId());

        return mapToResponse(savedRide);
    }

    @Override
    public RideResponse getRideById(Long rideId) {

        log.info("Fetching ride with id: {}", rideId);

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ride not found."));

        return mapToResponse(ride);
    }

    @Override
    public RideResponse cancelRide(
            Long riderId,
            Long rideId) {

        log.info("Ride cancellation requested. Ride ID: {}", rideId);

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ride not found."));

        if (!ride.getRiderId().equals(riderId)) {
            throw new BadRequestException(
                    "You can only cancel your own ride.");
        }

        if (ride.getRideStatus() == RideStatus.IN_PROGRESS
                || ride.getRideStatus() == RideStatus.COMPLETED
                || ride.getRideStatus() == RideStatus.CANCELLED) {

            throw new BadRequestException(
                    "Ride cannot be cancelled.");
        }

        ride.setRideStatus(RideStatus.CANCELLED);

        Ride updatedRide = rideRepository.save(ride);

        log.info("Ride cancelled successfully. Ride ID: {}",
                updatedRide.getId());

        return mapToResponse(updatedRide);
    }

    @Override
    public RideResponse acceptRide(Long rideId, String token) {

        log.info("Ride accept requested. Ride ID: {}", rideId);

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ride not found."));

        if (ride.getRideStatus() != RideStatus.REQUESTED) {

            throw new BadRequestException(
                    "Ride has already been accepted.");
        }

        DriverResponse driver =
                driverClient.getDriverProfile(token);

        if (driver.getStatus() != DriverStatus.APPROVED) {

            throw new BadRequestException(
                    "Driver is not approved.");
        }

        if (driver.getAvailability() != AvailabilityStatus.ONLINE) {

            throw new BadRequestException(
                    "Driver must be online to accept rides.");
        }

        ride.setDriverId(driver.getUserId());
        ride.setRideStatus(RideStatus.DRIVER_ASSIGNED);
        ride.setAcceptedAt(LocalDateTime.now());

        Ride savedRide = rideRepository.save(ride);

        return mapToResponse(savedRide);

    }

    private RideResponse mapToResponse(Ride ride) {

        return RideResponse.builder()
                .id(ride.getId())
                .riderId(ride.getRiderId())
                .driverId(ride.getDriverId())
                .pickupLatitude(ride.getPickupLatitude())
                .pickupLongitude(ride.getPickupLongitude())
                .dropLatitude(ride.getDropLatitude())
                .dropLongitude(ride.getDropLongitude())
                .pickupAddress(ride.getPickupAddress())
                .dropAddress(ride.getDropAddress())
                .rideStatus(ride.getRideStatus())
                .rideType(ride.getRideType())
                .paymentStatus(ride.getPaymentStatus())
                .estimatedFare(ride.getEstimatedFare())
                .actualFare(ride.getActualFare())
                .requestedAt(ride.getRequestedAt())
                .acceptedAt(ride.getAcceptedAt())
                .startedAt(ride.getStartedAt())
                .completedAt(ride.getCompletedAt())
                .build();
    }
}