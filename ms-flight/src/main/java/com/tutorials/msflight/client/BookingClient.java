package com.tutorials.msflight.client;


import com.tutorials.msflight.model.BookingRsModel;
import com.tutorials.msflight.model.ResponseModel;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ms-booking", url = "${client.url.booking}")
public interface BookingClient {

    @DeleteMapping
    ResponseEntity<ResponseModel<List<BookingRsModel>>> deleteBooking(@RequestParam(name = "flight-id") UUID flightId);
}
