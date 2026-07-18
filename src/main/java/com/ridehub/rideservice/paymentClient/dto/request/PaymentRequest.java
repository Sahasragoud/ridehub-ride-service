package com.ridehub.rideservice.paymentClient.dto.request;

import com.ridehub.rideservice.paymentClient.enums.Currency;
import com.ridehub.rideservice.paymentClient.enums.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PaymentRequest {

    private Long rideId;

    private Long payerId;

    private BigDecimal amount;

    private Currency currency;

    private PaymentMethod paymentMethod;

}
