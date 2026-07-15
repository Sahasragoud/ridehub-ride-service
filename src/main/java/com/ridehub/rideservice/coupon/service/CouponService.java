package com.ridehub.rideservice.coupon.service;

import com.ridehub.rideservice.coupon.dto.request.CouponRequest;
import com.ridehub.rideservice.coupon.dto.response.CouponResponse;

import java.util.List;

public interface CouponService {

    CouponResponse createCoupon(CouponRequest request);

    List<CouponResponse> getAllCoupons();

    CouponResponse getCoupon(Long id);

    CouponResponse deactivateCoupon(Long id);

}