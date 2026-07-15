package com.ridehub.rideservice.coupon.service.impl;

import com.ridehub.rideservice.coupon.dto.request.CouponRequest;
import com.ridehub.rideservice.coupon.dto.response.CouponResponse;
import com.ridehub.rideservice.coupon.dto.response.CouponValidationResponse;
import com.ridehub.rideservice.coupon.entity.Coupon;
import com.ridehub.rideservice.coupon.enums.CouponType;
import com.ridehub.rideservice.coupon.repository.CouponRepository;
import com.ridehub.rideservice.coupon.service.CouponService;
import com.ridehub.rideservice.exception.BadRequestException;
import com.ridehub.rideservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;

    @Override
    public CouponResponse createCoupon(CouponRequest request) {

        Coupon coupon = Coupon.builder()
                .code(request.getCode().toUpperCase())
                .type(request.getType())
                .value(request.getValue())
                .minimumRideAmount(request.getMinimumRideAmount())
                .maximumDiscount(request.getMaximumDiscount())
                .expiryDate(request.getExpiryDate())
                .active(true)
                .build();

        return map(repository.save(coupon));
    }

    @Override
    public List<CouponResponse> getAllCoupons() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public CouponResponse getCoupon(Long id) {

        return map(repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coupon not found")));
    }

    @Override
    public CouponResponse deactivateCoupon(Long id) {

        Coupon coupon = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coupon not found"));

        coupon.setActive(false);

        return map(repository.save(coupon));
    }

    private CouponResponse map(Coupon coupon) {

        return CouponResponse.builder()
                .id(coupon.getId())
                .code(coupon.getCode())
                .type(coupon.getType())
                .value(coupon.getValue())
                .minimumRideAmount(coupon.getMinimumRideAmount())
                .maximumDiscount(coupon.getMaximumDiscount())
                .active(coupon.getActive())
                .expiryDate(coupon.getExpiryDate())
                .createdAt(coupon.getCreatedAt())
                .build();
    }

    @Override
    public CouponValidationResponse applyCoupon(
            String couponCode,
            BigDecimal rideFare) {

        Coupon coupon = repository.findByCode(couponCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coupon not found"));

        if (!coupon.getActive()) {
            throw new BadRequestException("Coupon is inactive.");
        }

        if (coupon.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Coupon has expired.");
        }

        if (rideFare.compareTo(coupon.getMinimumRideAmount()) < 0) {
            throw new BadRequestException(
                    "Minimum ride amount is ₹"
                            + coupon.getMinimumRideAmount());
        }

        BigDecimal discount;

        if (coupon.getType() == CouponType.FLAT) {

            discount = coupon.getValue();

        } else {

            discount = rideFare
                    .multiply(coupon.getValue())
                    .divide(BigDecimal.valueOf(100));
        }

        if (discount.compareTo(coupon.getMaximumDiscount()) > 0) {
            discount = coupon.getMaximumDiscount();
        }

        BigDecimal finalFare = rideFare.subtract(discount);

        if (finalFare.compareTo(BigDecimal.ZERO) < 0) {
            finalFare = BigDecimal.ZERO;
        }

        return CouponValidationResponse.builder()
                .valid(true)
                .message("Coupon applied successfully.")
                .discount(discount)
                .finalFare(finalFare)
                .build();
    }
}