package com.tutorials.msbooking.client;

import com.tutorials.msbooking.model.FlightModel;
import com.tutorials.msbooking.model.ResponseModel;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-flight", url = "${client.url.flight}")
public interface FlightClient {
    @GetMapping("/flights/{id}")
    ResponseEntity<ResponseModel<FlightModel>> getFlight(@PathVariable UUID id);

}
