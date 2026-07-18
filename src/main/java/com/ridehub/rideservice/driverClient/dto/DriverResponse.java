package com.ridehub.rideservice.driverClient.dto;

import com.ridehub.rideservice.driverClient.enums.AvailabilityStatus;
import com.ridehub.rideservice.driverClient.enums.DriverStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverResponse {

    private Long id;

    private Long userId;

    private DriverStatus status;

    private AvailabilityStatus availability;
}