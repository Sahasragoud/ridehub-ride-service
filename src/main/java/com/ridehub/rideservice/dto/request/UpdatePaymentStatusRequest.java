package com.ridehub.rideservice.dto.request;

import com.ridehub.rideservice.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdatePaymentStatusRequest {

    @NotNull(message = "Payment status is required.")
    private PaymentStatus paymentStatus;

}