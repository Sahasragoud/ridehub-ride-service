package com.ridehub.rideservice.coupon.repository;

import com.ridehub.rideservice.coupon.entity.Coupon;
import com.ridehub.rideservice.coupon.entity.CouponUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponUsageRepository
        extends JpaRepository<CouponUsage, Long> {

    boolean existsByRiderIdAndCoupon(
            Long riderId,
            Coupon coupon
    );

}