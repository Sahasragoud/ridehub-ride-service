package com.ridehub.rideservice.controller;

import com.ridehub.rideservice.dto.request.RideRequest;
import com.ridehub.rideservice.dto.response.RideResponse;
import com.ridehub.rideservice.security.service.JwtService;
import com.ridehub.rideservice.service.interfaces.RideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<RideResponse> requestRide(
            @Valid @RequestBody RideRequest request,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        Long riderId = jwtService.extractUserId(token);

        RideResponse response =
                rideService.requestRide(riderId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<RideResponse> getRideById(
            @PathVariable Long rideId) {

        RideResponse response =
                rideService.getRideById(rideId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{rideId}/cancel")
    public ResponseEntity<RideResponse> cancelRide(
            @PathVariable Long rideId,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        Long riderId = jwtService.extractUserId(token);

        RideResponse response =
                rideService.cancelRide(riderId, rideId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{rideId}/accept")
    public ResponseEntity<RideResponse> acceptRide(
            @PathVariable Long rideId,
            @RequestHeader("Authorization") String authHeader) {

        RideResponse response = rideService.acceptRide(
                rideId,
                authHeader);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-rides")
    public ResponseEntity<List<RideResponse>> getMyRides(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        Long riderId = jwtService.extractUserId(token);

        List<RideResponse> response =
                rideService.getMyRides(riderId);

        return ResponseEntity.ok(response);
    }
}