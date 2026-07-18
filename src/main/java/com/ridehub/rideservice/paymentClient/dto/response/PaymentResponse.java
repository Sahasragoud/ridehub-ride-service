package com.ridehub.rideservice.paymentClient.dto.response;

import com.ridehub.rideservice.paymentClient.enums.PaymentMethod;
import com.ridehub.rideservice.paymentClient.enums.PaymentStatus;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentResponse {

    private Long id;

    private Long rideId;

    private Long payerId;

    private BigDecimal amount;

    private PaymentStatus status;

    private String transactionId;

    private String gateway;

    private PaymentMethod paymentMethod;

}