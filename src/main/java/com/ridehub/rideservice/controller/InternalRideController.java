package com.ridehub.rideservice.controller;

import com.ridehub.rideservice.dto.request.UpdatePaymentStatusRequest;
import com.ridehub.rideservice.service.interfaces.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/rides")
@RequiredArgsConstructor
public class InternalRideController {

    private final RideService rideService;

    @PutMapping("/{rideId}/payment-status")
    public void updatePaymentStatus(
            @PathVariable Long rideId,
            @RequestBody UpdatePaymentStatusRequest request) {

        rideService.updatePaymentStatus(
                rideId,
                request.getPaymentStatus());
    }
}