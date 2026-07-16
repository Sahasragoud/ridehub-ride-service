package com.ridehub.rideservice.coupon.controller;

import com.ridehub.rideservice.coupon.dto.request.CouponRequest;
import com.ridehub.rideservice.coupon.dto.response.CouponResponse;
import com.ridehub.rideservice.coupon.dto.response.CouponValidationResponse;
import com.ridehub.rideservice.coupon.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<CouponResponse> createCoupon(
            @Valid @RequestBody CouponRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(couponService.createCoupon(request));
    }

    @GetMapping
    public ResponseEntity<List<CouponResponse>> getAllCoupons() {

        return ResponseEntity.ok(
                couponService.getAllCoupons()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> getCoupon(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                couponService.getCoupon(id)
        );
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<CouponResponse> deactivateCoupon(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                couponService.deactivateCoupon(id)
        );
    }

    @PostMapping("/apply/{id}")
    public ResponseEntity<CouponValidationResponse> applyCoupon(

            @PathVariable Long id,

            @RequestParam String code,

            @RequestParam BigDecimal fare) {

        return ResponseEntity.ok(
                couponService.applyCoupon(id, code, fare)
        );
    }
}