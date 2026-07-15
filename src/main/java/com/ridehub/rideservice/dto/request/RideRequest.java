package com.ridehub.rideservice.dto.request;

import com.ridehub.rideservice.enums.RideType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideRequest {

    @NotNull(message = "Pickup latitude is required.")
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private Double pickupLatitude;

    @NotNull(message = "Pickup longitude is required.")
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private Double pickupLongitude;

    @NotNull(message = "Drop latitude is required.")
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private Double dropLatitude;

    @NotNull(message = "Drop longitude is required.")
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private Double dropLongitude;

    @NotBlank(message = "Pickup address is required.")
    private String pickupAddress;

    @NotBlank(message = "Drop address is required.")
    private String dropAddress;

    @NotNull(message = "Ride type is required.")
    private RideType rideType;
}