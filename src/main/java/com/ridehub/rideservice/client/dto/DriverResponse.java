package com.ridehub.rideservice.client.dto;

import com.ridehub.rideservice.client.enums.AvailabilityStatus;
import com.ridehub.rideservice.client.enums.DriverStatus;
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