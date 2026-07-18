package com.ridehub.rideservice.driverClient;

import com.ridehub.rideservice.driverClient.dto.DriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "driver-service",
        url = "${driver.service.url}"
)
public interface DriverClient {

    @GetMapping("/api/drivers/me")
    DriverResponse getDriverProfile(
            @RequestHeader("Authorization") String token
    );
}
