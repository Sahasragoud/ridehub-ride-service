package com.ridehub.rideservice.paymentClient;

import com.ridehub.rideservice.paymentClient.dto.request.PaymentRequest;
import com.ridehub.rideservice.paymentClient.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paymentservice")
public interface PaymentClient {

    @PostMapping("/api/internal/payments")
    PaymentResponse createPayment(
            @RequestBody PaymentRequest request
    );

}
